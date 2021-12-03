package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.UserRegisterDto;
import com.geniessoft.backend.dto.UserUpdateDto;
import com.geniessoft.backend.model.*;
import com.geniessoft.backend.repository.UserRepository;
import com.geniessoft.backend.service.BookingService;
import com.geniessoft.backend.service.ContentService;
import com.geniessoft.backend.service.RoleService;
import com.geniessoft.backend.service.UserService;
import com.geniessoft.backend.utility.bucket.BucketName;
import com.geniessoft.backend.utility.bucket.FolderNames;
import com.geniessoft.backend.utility.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
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
        userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllByDeletedFalse();
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

    /*@Override
    public User findMostBookedUser() {
        Map<User,Integer> userCountMap = makeUserCountMap();
        User user = Collections.max(userCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        return user;
    }

    @Override
    public List<User> findBookedUsersByDescOrder() {
        Map<User,Integer> userCountMap = makeUserCountMap();
        userCountMap = userCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        List<User> users = new ArrayList<>(userCountMap.keySet());
        return users;
    }

    private Map<User,Integer> makeUserCountMap(){
        List<Booking> bookings = bookingService.findAllBookingsByUserOrder();
        Map<User,Integer> locationCountMap = new HashMap<>();
        for (int i = 0;i<bookings.size();i++) {

            User user = findUser(bookings.get(i).getUser().getUserId());
            if (i == 0){
                locationCountMap.put(user, 1);}
            if (i !=0){
                if (user.equals(findUser(bookings.get(i-1).getBookingLocation().getLocationId()))){
                    locationCountMap.merge(user, 1, Integer::sum);
                }
                else {
                    locationCountMap.put(user,1);
                }
            }
        }
        return locationCountMap;
    }*/

    @Override
    public void checkUserEmail(String emailAddress) {
        Optional<User> user = userRepository.findFirstByEmailAddressEquals(emailAddress);
        if(user.isPresent()){
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
}
