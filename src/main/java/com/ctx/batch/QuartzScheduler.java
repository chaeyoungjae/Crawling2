package com.ctx.batch;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chaester on 2017-07-07.
 */
@Configuration
public class QuartzScheduler {
    @Bean
    public MethodInvokingJobDetailFactoryBean jobDetailFactoryBeanCate(){
        MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
        obj.setTargetBeanName("appService");
        obj.setTargetMethod("crawlingCategory");
        return obj;
    }
    @Bean
    public MethodInvokingJobDetailFactoryBean jobDetailFactoryBeanItem(){
        MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
        obj.setTargetBeanName("appService");
        obj.setTargetMethod("crawlingItem");
        return obj;
    }
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(cronTriggerFactoryBean1().getObject(), cronTriggerFactoryBean2().getObject());
        return scheduler;
    }
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean1() {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetailFactoryBeanCate().getObject());
        factoryBean.setStartDelay(3000);
        factoryBean.setName("category");
        factoryBean.setGroup("crawling");
        factoryBean.setCronExpression("0 0 22 ? * *");
        return factoryBean;
    }
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean2() {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetailFactoryBeanItem().getObject());
        factoryBean.setStartDelay(3000);
        factoryBean.setName("item");
        factoryBean.setGroup("crawling");
        factoryBean.setCronExpression("0 0 23 ? * *");
        return factoryBean;
    }
}
