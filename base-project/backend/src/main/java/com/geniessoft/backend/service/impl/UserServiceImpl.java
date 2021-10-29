package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.model.Content;
import com.geniessoft.backend.model.Role;
import com.geniessoft.backend.model.Roles;
import com.geniessoft.backend.model.User;
import com.geniessoft.backend.repository.UserRepository;
import com.geniessoft.backend.service.ContentService;
import com.geniessoft.backend.service.RoleService;
import com.geniessoft.backend.service.UserService;
import com.geniessoft.backend.utility.bucket.BucketName;
import com.geniessoft.backend.utility.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.MediaType.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CompanyMapper mapper;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final FileStoreService fileStoreService;
    private final ContentService contentService;

    @Override
    public User saveUser(UserRegisterDto userRegisterDto) {

        User user = mapper.userRegDtoToUser(userRegisterDto);
        checkUserEmail(user.getEmailAddress());

        Role role = roleService.findRoleByName(Roles.ROLE_USER);
        user.setRole(role);
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
    }

    @Override
    public User findUser(int userId) {

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

        if(file.isEmpty()){
            throw new EntityNotFoundException("File is empty");
        }

        if(!Arrays.asList(
                IMAGE_JPEG_VALUE,
                IMAGE_PNG_VALUE,
                IMAGE_GIF_VALUE).
                contains(file.getContentType())){
            throw new EntityNotFoundException("File is not image");
        }

        User user = findUser(userId);

        Map<String,String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length",String.valueOf(file.getSize()));

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(),user.getUserId());
        String fileName = String.format("%s-%s", user.getUserId(), file.getOriginalFilename());

        try {

            Content userProfileImage = new Content();
            userProfileImage.setContentName(file.getOriginalFilename());
            userProfileImage.setContentPath(fileName);
            userProfileImage.setContentType(file.getContentType());
            userProfileImage.setUploadDate(new Date());
            userProfileImage = contentService.saveContent(userProfileImage);
            user.setUserProfileImage(userProfileImage);

            fileStoreService.upload(path,fileName,Optional.of(metadata),file.getInputStream());
            System.out.println("Resim Yüklenmiş olabilir.");
        }
        catch (IOException e){
            throw new IllegalStateException(e);
        }
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
}
