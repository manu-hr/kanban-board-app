//package com.niit.authentication.service;
//
//import com.niit.authentication.exception.UserNotFoundException;
//import com.niit.authentication.model.User;
//import com.niit.authentication.repository.UserRepository;
//import com.niit.authentication.services.UserServiceImpl;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class UserAuthenticationServiceLayerTest {
//
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    User user;
//    @BeforeEach
//    public void setUp()
//    {
//      user=new User("bjkb32b12b31b23b","jeetkrjha@gmail.com","123","USER_ROLE");
//    }
//    @AfterEach
//    public void tearDown()
//    {
//        user=null;
//    }
////    User saveUser(User user);
//
//    @Test
//    public void saveUserTest()
//    {
//        when(userRepository.save(user)).thenReturn(user);
//        assertEquals(user,userService.saveUser(user));
//    }
//
//    @Test
//    public void editUserTest() throws UserNotFoundException {
//      User user1=userRepository.save(user);
//      user1.setEmail("ram@gmail.com");
//      user1.setUserId("vb1hj3vbh1312");
//      user1.setUserRole("USER_ROLE");
//      user1.setPassword("1234");
//      when(userRepository.save(user1)).thenReturn(user1);
//      assertEquals(user.getEmail(),userService.editUser(user1).getEmail());
//
//    }
//
//
//
//
////    User findByEmailAndPassword(String email , String password) throws UserNotFoundException;
//
//    @Test
//    public void findByEmailAndPasswordTest() throws UserNotFoundException {
//        when(userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword())).thenReturn(user);
//        assertEquals(user,userService.findByEmailAndPassword(user.getEmail(),user.getPassword()));
//    }
//
////    Boolean deleteUser(String id) throws UserNotFoundException;
//
//    @Test
//    public void deleteUserTest()
//    {
//        userRepository.save(user);
//        userRepository.deleteById(user.getUserId());
//        Optional optional=userRepository.findById(user.getUserId());
//        assertEquals(Optional.empty(),optional);
//    }
//
//}
//
