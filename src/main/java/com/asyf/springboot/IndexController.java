package com.asyf.springboot;

import com.asyf.springboot.util.ApplicationContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.Date;

@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("time", new Date());
        //model.addAttribute("message", userDao.findUserByName("张三"));
        logger.debug("查询");
        User user = userDao.selectByPrimaryKey("1");
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
}
