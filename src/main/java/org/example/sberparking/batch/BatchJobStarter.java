package org.example.sberparking.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BatchJobStarter{
    private final JobLauncher jobLauncher;
    private final Job exportParkingRepostJob;

    @Autowired
    public BatchJobStarter(JobLauncher jobLauncher, Job exportParkingRepostJob) {
        this.jobLauncher = jobLauncher;
        this.exportParkingRepostJob = exportParkingRepostJob;
    }

    @Scheduled(cron = "0 * * * * *")
    public void run() throws Exception {
        log.info("Starting BatchJobStarter");
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(exportParkingRepostJob, jobParameters);
    }
}
