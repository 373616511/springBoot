package com.asyf.springboot.controller;

import com.asyf.springboot.dao.UserDao;
import com.asyf.springboot.entity.User;
import com.asyf.springboot.schedule.MyJob;
import com.asyf.springboot.service.UserService;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    Scheduler scheduler;
    @Autowired
    private JobDetail jobDetail;
    @Autowired
    private Trigger trigger;

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("time", new Date());
        //model.addAttribute("message", userDao.findUserByName("张三"));
        logger.debug("查询");
        User user = userService.selectByPrimaryKey("1");
        request.getSession().setAttribute("user", user);
        model.addAttribute("user", user);
        model.addAttribute("message2", user.toString() + "===");
        //Jedis jedis = new Jedis("localhost");
        // jedis.append("aa", ApplicationContextHolder.getApplicationContext().toString());
        return "index";
    }

    @RequestMapping(value = "/shutdownJob")
    @ResponseBody
    public String shutdownJob() {
        String result = "关闭成功";
        try {
            scheduler.standby();
        } catch (SchedulerException e) {
            e.printStackTrace();
            result = "关闭失败";
        }
        return scheduler.toString() + "-------" + result + "-----key=" + MyJob.triggerKey;
    }

    @RequestMapping(value = "/startJob")
    @ResponseBody
    public String startJob() {
        String result = "启动成功";
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
            result = "启动失败";
        }
        return scheduler.toString() + "-------" + result + "-----key=" + MyJob.triggerKey;
    }

    @RequestMapping(value = "/test")
    public String test() {
        logger.debug("test---------------");
        return "demo";
    }

    @RequestMapping(value = "/submit")
    @ResponseBody
    public String sbmit(HttpServletRequest request) {
        String result = null;
        result = request.getParameter("editor1");
        User user = new User();
        user.setId("1");
        user.setText(result);
        userService.update(user);
        return result;
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

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadImage(String CKEditorFuncNum, @RequestParam("upload") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        String name = "";
        String ckeditorStorageImagePath = "D:\\upload\\";
        String ckeditorAccessImageUrl = "http://127.0.0.1:8080/upload/";
        logger.error("path:" + ckeditorStorageImagePath);
        if (!file.isEmpty()) {
            try {
                response.setContentType("text/html; charset=UTF-8");
                response.setHeader("Cache-Control", "no-cache");
                //解决跨域问题
                //Refused to display 'http://localhost:8080/upload/mgmt/img?CKEditor=practice_content&CKEditorFuncNum=1&langCode=zh-cn' in a frame because it set 'X-Frame-Options' to 'DENY'.
                response.setHeader("X-Frame-Options", "SAMEORIGIN");
                PrintWriter out = response.getWriter();

                String fileClientName = file.getOriginalFilename();
                String pathName = ckeditorStorageImagePath + fileClientName;
                File newfile = new File(pathName);
                if (!newfile.getParentFile().exists()) {
                    newfile.getParentFile().mkdirs();
                }
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newfile));
                stream.write(bytes);
                stream.close();

                // 组装返回url，以便于ckeditor定位图片
                String fileUrl = ckeditorAccessImageUrl + File.separator + newfile.getName();
                logger.error("文件路径：" + fileUrl);
                // 将上传的图片的url返回给ckeditor
                //String callback = request.getParameter("CKEditorFuncNum");
                String script = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + "1" + ", '" + fileUrl + "','');</script>";

                out.println(script);
                out.flush();
                out.close();
            } catch (Exception e) {
                logger.info("You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            logger.info("You failed to upload " + name + " because the file was empty.");
        }
        //return "SUCCESS";
    }
}
