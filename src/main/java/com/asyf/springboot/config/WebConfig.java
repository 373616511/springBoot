package com.asyf.springboot.config;

import com.asyf.springboot.listener.SessionFilter;
import com.asyf.springboot.schedule.MyJob;
import org.quartz.*;
import org.quartz.core.QuartzScheduler;
import org.quartz.impl.QuartzServer;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.MutableTrigger;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean filterRegist() {
        System.err.println("注册filterRegist-----");
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new SessionFilter());
        frBean.addUrlPatterns("/*");
        return frBean;
    }

    @Bean(name = "jobDetail")
    public JobDetail jobDetail() {
        // MyJob为需要执行的任务
        JobBuilder jobBuilder = JobBuilder.newJob(MyJob.class);
        jobBuilder.withIdentity("job1", "group1");
        JobDetail jobDetail = jobBuilder.build();
        JobKey key = jobDetail.getKey();
        MyJob.jobKey = key;
        return jobDetail;
    }

    @Bean(name = "trigger")
    public Trigger jobTrigger(JobDetail jobDetail) {
        /*SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        simpleScheduleBuilder.withIntervalInSeconds(2);
        simpleScheduleBuilder.repeatForever();
        TriggerBuilder<Trigger> triggerTriggerBuilder = TriggerBuilder.newTrigger();
        triggerTriggerBuilder.withIdentity("MyTrigger");
        triggerTriggerBuilder.withSchedule(simpleScheduleBuilder);
        Trigger trigger = triggerTriggerBuilder.build();
        */
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
        TriggerBuilder<Trigger> triggerTriggerBuilder = TriggerBuilder.newTrigger();
        triggerTriggerBuilder.withIdentity("MyTrigger");
        triggerTriggerBuilder.withSchedule(cronScheduleBuilder);
        Trigger trigger = triggerTriggerBuilder.build();
        TriggerKey key = trigger.getKey();
        MyJob.triggerKey = key;
        return trigger;
    }

    @Bean
    public Scheduler scheduler(JobDetail jobDetail, Trigger trigger) {
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            System.err.println("初始化的scheduler=" + scheduler);
            // 交由Scheduler安排触发
            //☆☆☆☆☆☆☆若要执行多个Job，多定义几个JobDetail
            //☆☆☆☆☆☆☆若要使用多个trigger，多定义
            //若控制开闭启动的需求不同，多定义几个scheduler
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return scheduler;
    }
}
