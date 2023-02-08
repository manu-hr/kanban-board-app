//package com.niit.authentication.repos;
//
//import com.niit.authentication.model.User;
//import com.niit.authentication.repository.UserRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//@SpringBootTest
//public class UserAuthenticationRepositoryLayerTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    User user;
//
//    @BeforeEach
//    public void setUp()
//    {
//        user=new User("bjkb32b12b31b23b","jeetkrjha@gmail.com","123","USER_ROLE");
//    }
//    @AfterEach
//    public void tearDown()
//    {
//        user=null;
//    }
//    @Test
//    public void saveUserTest()
//    {
//        userRepository.save(user);
//        User user1=userRepository.findById(user.getUserId()).get();
//        assertEquals(user.getUserId(),user1.getUserId());
//    }
//    @Test
//    public void getAllUser()
//    {
//        User user1=new User("bjkb32b1bhb4b","jeetkrjha11@gmail.com","123","USER_ROLE");
//        userRepository.save(user1);
//        userRepository.save(user);
//        assertEquals(2,userRepository.findAll().size());
//    }
//
//    @Test
//    public void getById()
//    {
//        User user1=userRepository.save(user);
//        assertEquals(user.getUserId(),user1.getUserId());
//    }
//    @Test
//    public void deleteByIdTest()
//    {
//        User user1=userRepository.save(user);
//        userRepository.deleteById(user1.getUserId());
//        assertEquals(1,userRepository.findAll().size());
//    }
//    //user custom method test
//    @Test
//    public void findByEmailAndPassword()
//    {
//        String email=user.getEmail();
//        String password=user.getPassword();
//        userRepository.save(user);
//        User user1=userRepository.findByEmailAndPassword(email,password);
//        assertEquals(user1.getEmail(),user.getEmail());
//    }
//    @Test
//    public void updateUserTest()
//    {
//        User user1=userRepository.save(user);
//        user1.setEmail("manu.hr70@gmail.com");
//        userRepository.save(user1);
//        User user2=userRepository.findById(user1.getUserId()).get();
//        assertNotEquals(user.getEmail(),user2.getEmail());
//    }
//
//
//
//}
