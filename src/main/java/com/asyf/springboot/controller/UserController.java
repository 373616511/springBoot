package com.asyf.springboot.controller;

import com.asyf.springboot.dao.UserDao;
import com.asyf.springboot.entity.User;
import com.asyf.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("time", new Date());
        //model.addAttribute("message", userDao.findUserByName("张三"));
        logger.debug("查询");
        User user = userService.selectByPrimaryKey("1");
        model.addAttribute("message2", user.toString() + "===");
        //Jedis jedis = new Jedis("localhost");
        // jedis.append("aa", ApplicationContextHolder.getApplicationContext().toString());
        return "index";
    }

    @RequestMapping(value = "/test")
    public String test() {
        logger.debug("test---------------");
        return "demo";
    }

    @RequestMapping(value = "/transactionTest")
    @ResponseBody
    public String transactionTest() {
        User user = new User();
        user.setId("1");
        user.setName(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        userService.updateUserById(user);
        return "事物测试";
    }
}
