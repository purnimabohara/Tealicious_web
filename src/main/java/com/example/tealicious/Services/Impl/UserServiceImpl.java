package com.example.tealicious.Services.Impl;


import com.example.tealicious.Entity.Contact;
import com.example.tealicious.Entity.User;
import com.example.tealicious.Pojo.ContactPojo;
import com.example.tealicious.Pojo.UserPojo;
import com.example.tealicious.Repo.ContactRepo;
import com.example.tealicious.Repo.UserRepo;
import com.example.tealicious.Services.UserService;
import com.example.tealicious.config.PasswordEncoderUtil;
import com.example.tealicious.exception.AppException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public final UserRepo userRepo;
    public final ContactRepo contactRepo;
    private final ThreadPoolTaskExecutor taskExecutor;

    private final JavaMailSender mailSender;

    @Autowired
    @Qualifier("emailConfigBean")
    private Configuration emailConfig;

    @Override
    public String save(UserPojo userPojo) {
        User user = new User();
        if (userPojo.getId() != null) {
            user.setId(userPojo.getId());
        }
        user.setEmail(userPojo.getEmail());
        user.setFullname(userPojo.getFullname());
        user.setMobileNo(userPojo.getMobile_no());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userPojo.getPassword()));
        userRepo.save(user);
        return "created";
    }

    @Override
    public String update(UserPojo userPojo) {
        User user = new User();
        if (userPojo.getId() != null) {
            user.setId(userPojo.getId());
        }
        user.setEmail(userPojo.getEmail());
        user.setFullname(userPojo.getFullname());
        user.setMobileNo(userPojo.getMobile_no());
        userRepo.save(user);
        return "created";
    }

    @Override
    public User getById(Integer id) {
        return userRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public String submitMsg(ContactPojo contactPojo) {
        Contact contact=new Contact();
        contact.setFullname(contactPojo.getFullname());
        contact.setEmail(contactPojo.getEmail());
        contact.setSubject(contactPojo.getSubject());
        contact.setMessage(contactPojo.getMessage());
        contactRepo.save(contact);
        return "sent";
    }
    @Override
    public List<Contact> fetchAllContact() {
        return this.contactRepo.findAll();
    }



    @Override
    public User findByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));
        return user;
    }

    @Override
    public String updateResetPassword(String email) {
        User user = (User) userRepo.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Invalid User email"));
        String updated_password = generatePassword();
        try {
            userRepo.updatePassword(updated_password, email);
            return "CHANGED";
        } catch (Exception e){
            e.printStackTrace();
        }
        return "ds";
    }

    public String generatePassword() {
        int length = 8;
        String password = "";
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            int randomChar = (int)(r.nextInt(94) + 33);
            char c = (char)randomChar;
            password += c;
        }
        return password;
    }

    private void sendPassword(String email, String password ){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your new password is:");
        message.setText(password);
        mailSender.send(message);
    }
    @Override
    public void processPasswordResetRequest(String email){
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            String password = generatePassword();
            sendPassword(email, password);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodePassword = passwordEncoder.encode(password);
            user.setPassword(encodePassword);
            userRepo.save(user);
        }
    }
    @Override
    public void sendEmail() {
        try {
            Map<String, String> model = new HashMap<>();

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Template template = emailConfig.getTemplate("emailTemp.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            mimeMessageHelper.setTo("sendfrom@yopmail.com");
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject("Registration");
            mimeMessageHelper.setFrom("sendTo@yopmail.com");

            taskExecutor.execute(new Thread() {
                public void run() {
                    mailSender.send(message);
                }
            });
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<User> fetchAll() {
        return null;
    }
}