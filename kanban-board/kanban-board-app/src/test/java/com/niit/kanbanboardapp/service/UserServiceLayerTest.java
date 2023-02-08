//package com.niit.kanbanboardapp.service;
//
//import com.niit.kanbanboardapp.domain.User;
//import com.niit.kanbanboardapp.domain.UserDTO;
//import com.niit.kanbanboardapp.domain.UserSignupData;
//import com.niit.kanbanboardapp.proxy.UserDataProxy;
//import com.niit.kanbanboardapp.repository.UserRepository;
//import com.niit.kanbanboardapp.service.user.UserService;
//import com.niit.kanbanboardapp.service.user.UserServiceImpl;
//import org.apache.coyote.Response;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.platform.engine.TestExecutionResult;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.reactivestreams.Publisher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import javax.net.ssl.SSLEngineResult;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceLayerTest {
//
//    User user;
//
//    UserDTO userDTO;
//
//    UserSignupData userSignupData;
//    @Mock
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserDataProxy userDataProxy;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @BeforeEach
//<<<<<<< HEAD
////    public void setUp()
//=======
//////    public void setUp()
//>>>>>>> dev/jeet
//    {
//        userSignupData=new UserSignupData("jh1vb3h1j2v3","jeet","7061349526","jeetkrejha@gmail.com","USER_ROLE");
//        user=new User(userSignupData.getUserId(), userSignupData.getUserName(),
//                userSignupData.getPhoneNumber(), userSignupData.getEmail(), "USER_ROLE");
//        UserDTO userDTO =new UserDTO(userSignupData.getUserId(), userSignupData.getEmail(),
//                userSignupData.getPassword(),"USER_ROLE");
//    }
//    @AfterEach
//    public void tearDown()
//    {
//        user=null;
//    }
//
//    @Test
//    public void editProfileTest()
//    {
//        ResponseEntity<?> response = new ResponseEntity<>(user,HttpStatus.OK);
////        when(userDataProxy.editUserDataInAuthApp(userDTO)).thenReturn();
//
//    }
//
//
//
//
//
//
//}
