package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.model.*;
import com.geniessoft.backend.oauth2Security.TokenProvider;
import com.geniessoft.backend.repository.UserRepository;
import com.geniessoft.backend.service.ContentService;
import com.geniessoft.backend.service.RoleService;
import com.geniessoft.backend.service.UserService;
import com.geniessoft.backend.utility.bucket.BucketName;
import com.geniessoft.backend.utility.bucket.FolderNames;
import com.geniessoft.backend.utility.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CompanyMapper mapper;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final FileStoreService fileStoreService;
    private final ContentService contentService;
    private final TokenProvider tokenProvider;

    private PasswordEncoder encoder;
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User saveUser(UserRegisterDto userRegisterDto) {

        User user = mapper.userRegDtoToUser(userRegisterDto);
        checkUserEmail(user.getEmailAddress());

        Role role = roleService.findRoleByName(Roles.ROLE_USER);
        user.setRole(role);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }

    @Override
    public User updateUser(UserUpdateDto userUpdateDto) {

        User user = findUser(userUpdateDto.getUserId());
        if(!user.getEmailAddress().equals(userUpdateDto.getEmailAddress())){
            checkUserEmail(userUpdateDto.getEmailAddress());
        }
        mapper.updateUser(user,userUpdateDto);
        userRepository.save(user);

        return user;
    }

    @Override
    public void deleteUser(int userId) {
        User user = findUser(userId);
        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override
    public User findUser(long userId) {

        Optional<User> user = userRepository.findByUserId(userId);
        return customUserFind(user);
    }

    @Override
    public User findFirstByEmailAddressEquals(String emailAddress) {
        Optional<User> user = userRepository.findFirstByEmailAddressEquals(emailAddress);
        return customUserFind(user);
    }

    @Override
    public void checkUserEmail(String emailAddress) {
        Optional<User> user = userRepository.findFirstByEmailAddressEquals(emailAddress);
        if(!user.isEmpty()){
            throw new EntityExistsException("This email address is used.");
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void uploadUserProfileImage(int userId, MultipartFile file) {

        // If there is user profile image then previous image deleted.
        User user = findUser(userId);
        if(user.getUserProfileImage() != null){
            deleteUserProfileImage(user);
        }

        Map<String,String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length",String.valueOf(file.getSize()));

        String path = String.format("%s/%s", BucketName.BUCKET_NAME.getBucketName(), FolderNames.user_profile_images);
        String fileName = String.format("%s-%s", user.getUserId(), file.getOriginalFilename());

        try {

            Content userProfileImage = contentService.saveContent(fileName,path,file.getContentType());
            user.setUserProfileImage(userProfileImage);
            fileStoreService.upload(path,fileName,Optional.of(metadata),file.getInputStream());
            System.out.println("Image is uploaded");
        }
        catch (IOException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteUserProfileImage(User user) {
        Content content = user.getUserProfileImage();
        contentService.deleteContent(content.getContentId());
        fileStoreService.delete(content.getContentPath(),content.getContentName());
    }

    private User customUserFind(Optional<User> user){
        if(user.isEmpty()){
            throw new EntityNotFoundException("User not found.");
        }
        else if(user.get().isDeleted()){
            throw new EntityNotFoundException("No further information is to be supplied");
        }
        else {
            return user.get();
        }
    }

    @Override
    public byte[] getUserProfileImage(int userId) {
        User user = findUser(userId);
        return fileStoreService.download(
                user.getUserProfileImage().getContentPath(),
                user.getUserProfileImage().getContentName());
    }

    @Override
    public User findByEmailAddress(String emailAddress) {
        return userRepository.findFirstByEmailAddressEquals(emailAddress).get();
    }

    @Override
    public JwtAuthenticationResponse loginUser(String username, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        JwtAuthenticationResponse response =
                new JwtAuthenticationResponse(tokenProvider.createToken(authentication));
        response.setPrincipal(authentication.getPrincipal());
        return response;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserWithEmail(String email) {
        return userRepository.findFirstByEmailAddressEquals(email);
    }

    @Override
    public String getUserProfileImageUrl(long userId) {
        User user = findUser(userId);
        return fileStoreService.getPreSignedDownloadUrl(
                user.getUserProfileImage().getContentPath(),
                user.getUserProfileImage().getContentName());
    }
}
