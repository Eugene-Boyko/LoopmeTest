package com.loopme.web;

import com.loopme.model.User;
import com.loopme.model.UserRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PublisherController {

    @RequestMapping("/getPublisher")
    @ResponseBody
    public User getPublisher() {
        User user = new User();
        user.setEmail("someEmail");
        user.setName("someName");
        user.setRole(UserRole.PUBLISHER);
        return user;
    }

}
