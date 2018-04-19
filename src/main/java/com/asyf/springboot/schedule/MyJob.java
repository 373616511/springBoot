package com.asyf.springboot.schedule;

import org.quartz.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 假设定时任务的时间间隔为 2 秒，但 job 执行时间是 6 秒。
 * 当设置 @DisallowConcurrentExecution 以后程序会等任务执行完毕后再去执行，否则会在 2 秒时再启动新的线程执行。
 */
@DisallowConcurrentExecution
public class MyJob implements Job {

    public static JobKey jobKey = null;
    public static TriggerKey triggerKey = null;
    private static int i = 0;

    public MyJob() {
        System.err.println("=========初始化");
        i++;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.err.println(i + "---" + "scheduleTest开始定时执行" + new Date() + "下次执行时间：" + jobExecutionContext.getNextFireTime() + Thread.currentThread().getName());
        if (i < 4) {
            try {
                Thread.currentThread().sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       }
    }
}
