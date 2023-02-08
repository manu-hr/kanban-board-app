package com.niit.kanbanboardapp.proxy;


import com.niit.kanbanboardapp.domain.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-auth-app",url = "user-auth-app:8888")
public interface UserDataProxy {

    @PostMapping("/authentication/register")
    public ResponseEntity<?> sendDataToUserDto(@RequestBody UserDTO userDTO);


    @DeleteMapping("/authentication/delete")
    public ResponseEntity<?> deleteUser(String userId);

    @PutMapping("/authentication/edit")
    public ResponseEntity<?> editUserDataInAuthApp(UserDTO userDTO);

}
