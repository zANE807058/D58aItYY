// 代码生成时间: 2025-09-07 15:10:22
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.JobBuilder.*;
import org.quartz.TriggerBuilder.*;
import org.quartz.CronScheduleBuilder.*;
import org.quartz.TriggerKey;
import org.quartz.JobKey;
import org.quartz.impl.matchers.GroupMatcher;
import java.util.Set;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

public class ScheduledTaskScheduler {
    
    private final static String JOB_GROUP = "myJobGroup";
    private final static String TRIGGER_GROUP = "myTriggerGroup";
    
    private Scheduler scheduler;
    
    public ScheduledTaskScheduler() {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException e) {
            throw new RuntimeException("Failed to get scheduler: " + e.getMessage(), e);
        }
    }
    
    // Schedule a job to run at a specific time
    public void scheduleJob(JobDetail jobDetail, Trigger trigger) {
        try {
            scheduler.scheduleJob(jobDetail, trigger);
            System.out.println("Job scheduled successfully");
        } catch (SchedulerException e) {
            throw new RuntimeException("Failed to schedule job: " + e.getMessage(), e);
        }
    }
    
    // Start all jobs in the specified groups
    public void startJobsInGroups(String jobGroup, String triggerGroup) {
        try {
            scheduler.start();
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroup));
            for (JobKey jobKey : jobKeys) {
                TriggerKey triggerKey = new TriggerKey(jobKey.getName(), triggerGroup);
                scheduler.resumeTrigger(triggerKey);
            }
            System.out.println("Jobs in group 