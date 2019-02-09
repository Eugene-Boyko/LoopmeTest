package com.loopme.controller;


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
        return new User("someName", "someEmail", UserRole.PUBLISHER);
    }

}
