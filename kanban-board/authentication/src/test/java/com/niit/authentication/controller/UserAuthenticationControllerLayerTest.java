//package com.niit.authentication.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.niit.authentication.model.User;
//import com.niit.authentication.services.UserService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class UserAuthenticationControllerLayerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private UserController userController;
//
//    User user;
//
//    @BeforeEach
//    public void setUp()
//    {
//        user=new User("hjh21jvj1v23v","hamsarambe@gmail.com","1123","USER_ROLE");
//
//    }
//    @AfterEach
//    public void tearDown()
//    {
//        user=null;
//    }
//
//
//
//    @Test
//    public void registerUserTest() throws Exception {
//        when(userService.saveUser(user)).thenReturn(user);
//        mockMvc.perform(post("/authentication/register").contentType(MediaType.APPLICATION_JSON)
//                .content(jsontoString(user)))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void loginUserTest() throws Exception {
//        when(userService.findByEmailAndPassword(user.getEmail(),user.getPassword())).thenReturn(user);
//        mockMvc.perform(post("/authentication/login").contentType(MediaType.APPLICATION_JSON)
//                        .content(jsontoString(user)))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//    @Test
//    public void editUserTest() throws Exception {
//        when(userService.editUser(user)).thenReturn(user);
//        mockMvc.perform(put("/authentication/edit").contentType(MediaType.APPLICATION_JSON)
//                        .content(jsontoString(user)))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//    @Test
//    public void deleteUserTest() throws Exception {
//        when(userService.deleteUser(user.getUserId())).thenReturn(true);
//        mockMvc.perform(delete("/authentication/delete").contentType(MediaType.APPLICATION_JSON)
//                        .content(jsontoString(user)))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    //Method for convert json data to string format
//    private String jsontoString(final Object obj) {
//        String result;
//        try {
//            ObjectMapper objectMapper=new ObjectMapper();
//            String jsonContent = objectMapper.writeValueAsString(obj);
//            result = jsonContent;
//        }
//        catch (JsonProcessingException ex){
//            result="error while converting to string";
//        }
//        return result;
//    }
//
//
//
//}
