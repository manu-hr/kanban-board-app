package com.niit.kanbanboardapp.service.user;

import com.niit.kanbanboardapp.domain.User;
import com.niit.kanbanboardapp.domain.UserSignupData;
import com.niit.kanbanboardapp.exception.DocumentAlreadyExistException;
import com.niit.kanbanboardapp.exception.DocumentNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    public abstract User editProfile(UserSignupData userSignupData, String userId)throws DocumentNotFoundException;
    public abstract User viewProfile(String userId);
    public abstract User register(UserSignupData userSignupData)throws DocumentAlreadyExistException;
    public abstract boolean deleteProfile(String userId)throws DocumentNotFoundException;
    public abstract List<User> getAllUsersByIds(List<String> userIds);
    User addProfilePic(String userId, MultipartFile image ) throws IOException, DocumentNotFoundException;

}
