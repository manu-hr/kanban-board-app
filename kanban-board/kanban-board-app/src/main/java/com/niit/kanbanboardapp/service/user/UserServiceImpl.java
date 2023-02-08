package com.niit.kanbanboardapp.service.user;

import com.niit.kanbanboardapp.domain.EmailDTO;
import com.niit.kanbanboardapp.domain.User;
import com.niit.kanbanboardapp.domain.UserDTO;
import com.niit.kanbanboardapp.domain.UserSignupData;
import com.niit.kanbanboardapp.exception.DocumentAlreadyExistException;
import com.niit.kanbanboardapp.exception.DocumentNotFoundException;
import com.niit.kanbanboardapp.proxy.UserDataProxy;
import com.niit.kanbanboardapp.rabbitmq.MailProducer;
import com.niit.kanbanboardapp.repository.UserRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDataProxy userDataProxy;

    @Autowired
    private MailProducer mailProducer;

    @Override
    public User editProfile(UserSignupData userSignupData, String userId) throws DocumentNotFoundException {
        try {
            System.out.println(userSignupData);
            UserDTO userDTO = new UserDTO(userId, userSignupData.getEmail(),userSignupData.getPassword(),"ROLE_USER");
            ResponseEntity<?> response = userDataProxy.editUserDataInAuthApp(userDTO);
            if(response.getStatusCode() == HttpStatus.OK) {
                Optional<User> isUser = userRepository.findById(userId);
                if(isUser.isPresent()){
                    Binary tempUserProfileData = isUser.get().getProfilePic();
                    User userDetails = new User(userId,userSignupData.getUserName(),userSignupData.getPhoneNumber(),userSignupData.getEmail(),"USER_ROLE", tempUserProfileData);
                   return userRepository.save(userDetails);
                }
                throw new DocumentNotFoundException();
            }
            return null;
        }catch (Exception e) {
            throw new DocumentNotFoundException();
        }

    }

    @Override
    public User viewProfile(String userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User register(UserSignupData userSignupData) throws DocumentAlreadyExistException {
        String id=new ObjectId().toString();
        try {
            UserDTO userDTO = new UserDTO(id, userSignupData.getEmail(),userSignupData.getPassword(),"ROLE_USER");
            ResponseEntity<?> response = userDataProxy.sendDataToUserDto(userDTO);
            System.out.println(response);
            if(response.getStatusCode() == HttpStatus.OK) {
                User userDetails = new User(id, userSignupData.getUserName(),userSignupData.getPhoneNumber(),userSignupData.getEmail(),"USER_ROLE", null);
                User user= userRepository.insert(userDetails);
                EmailDTO emailDTO=new EmailDTO(user.getEmail(),"Registered Successfully","Registration");
                mailProducer.sendMailDtoToQueue(emailDTO);
                return user;
            }
            return null;
        }catch (Exception e) {
            System.out.println(e.getMessage().toString().substring(1,4).equals("409"));
            if(e.getMessage().toString().substring(1,4).equals("409")) {
                throw new DocumentAlreadyExistException();
            }
            throw e;
        }
    }


    @Override
    public boolean deleteProfile(String userId) throws DocumentNotFoundException {
        try {
            ResponseEntity<?> authAppResponse = userDataProxy.deleteUser(userId);
            if(authAppResponse.getStatusCode() == HttpStatus.OK) {
                Optional<User> isUser = userRepository.findById(userId);
                if(isUser.isPresent()){
                    userRepository.deleteById(userId);
                    return true;
                }
                return false;
            }
            return false;
        }catch (Exception e) {
            throw new DocumentNotFoundException();

        }
    }

    @Override
    public List<User> getAllUsersByIds(List<String> userIds) {
        Iterable<User> users = userRepository.findAllById(userIds);
        List<User> result = new ArrayList<>();
        users.forEach(result::add);
        return result;
    }

    @Override
    public User addProfilePic(String userId, MultipartFile image) throws IOException, DocumentNotFoundException {
        Optional<User> tempUserObj = userRepository.findById(userId);
        if(tempUserObj.isPresent()) {
            User tempUser = tempUserObj.get();
            tempUser.setProfilePic(
                    new Binary(BsonBinarySubType.BINARY, image.getBytes())
            );
            return userRepository.save(tempUser);
        }
        throw new DocumentNotFoundException();
    }
}

