package com.example.tealicious.Services;

import com.example.tealicious.Entity.Contact;
import com.example.tealicious.Entity.User;
import com.example.tealicious.Pojo.ContactPojo;
import com.example.tealicious.Pojo.UserPojo;

import java.util.List;

public interface UserService {


    String save(UserPojo userPojo);

    String update(UserPojo userPojo);

    User getById(Integer id);

//    contact Page
String submitMsg(ContactPojo contactPojo);
    List<Contact> fetchAllContact();
    List<User> fetchAllUser();
    void deleteCustomer(Integer id);
    void deleteContact(Integer id);

    User findByEmail(String email);

    String updateResetPassword(String email);

    void processPasswordResetRequest(String email);

    void sendEmail();


}
