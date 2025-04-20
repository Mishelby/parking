package org.example.sberparking.config;

import jakarta.persistence.EntityManagerFactory;
import org.example.sberparking.domain.ParkingEntity.ParkingEntity;
import org.example.sberparking.domain.ParkingEntity.ParkingReportDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class BatchConfig {

//    @Bean
//    public FlatFileItemWriter<ParkingReportDto> parkingCsvWriter() {
//        return new FlatFileItemWriterBuilder<ParkingReportDto>()
//                .name("parkingReportWriter")
//                .resource(new FileSystemResource("parking_report.csv"))
//                .delimited()
//                .delimiter(",")
//                .names("carNumber", "carType", "checkInTime", "exitTime")
//                .headerCallback(writer -> writer.write("carNumber,carType,checkInTime,exitTime"))
//                .build();
//    }
//
//    @Bean
//    public FlatFileItemReader<ParkingReportDto> reader() {
//        return new FlatFileItemReaderBuilder<ParkingReportDto>()
//                .name("parkingEntityReader")
//                .resource(new ClassPathResource("parking_report.csv"))
//                .delimited()
//                .names("carNumber", "carType", "checkInTime", "exitTime")
//                .targetType(ParkingReportDto.class)
//                .build();
//    }
//
//    @Bean
//    public ItemProcessor<ParkingEntity, List<ParkingReportDto>> flatteningProcessor() {
//        return parkingEntity -> {
//            List<CarInfoEntity> carsInfo = parkingEntity.getCarInfoEntity();
//
//            return carsInfo.stream()
//                    .map(carInfo -> {
//                        var parkingInfoEntity = carInfo.getParkingEntity();
//                        return new ParkingReportDto(
//                                carInfo.getCarNumber(),
//                                carInfo.getCarType().getDescription(),
//                                parkingInfoEntity.getParkingTime(),
//                                parkingInfoEntity.getParkingEndTime()
//                        );
//                    }).toList();
//        };
//    }

//    @Bean
//    public FlatteningItemProcessor<ParkingEntity, ParkingReportDto> processor(
//            ItemProcessor<ParkingEntity, List<ParkingReportDto>> parkingEntityProcessor
//    ) {
//        FlatteningItemProcessor<ParkingEntity, ParkingReportDto> flatteningProcessor =
//                new FlatteningItemProcessor<>();
//        flatteningProcessor.setDelegate(parkingEntityProcessor);
//        return flatteningProcessor;
//    }
//
//
//    @Bean
//    public JpaPagingItemReader<ParkingEntity> parkingEntityReader(EntityManagerFactory emf) {
//        JpaPagingItemReader<ParkingEntity> reader = new JpaPagingItemReader<>();
//        reader.setQueryString("SELECT p FROM ParkingEntity p");
//        reader.setEntityManagerFactory(emf);
//        reader.setPageSize(10);
//        reader.setName("parkingJpaReader");
//        return reader;
//    }
//
//    @Bean
//    public Step exportParkingStep(
//            JobRepository jobRepository,
//            PlatformTransactionManager transactionManager,
//            JpaPagingItemReader<ParkingEntity> parkingEntityReader,
//            FlatteningItemProcessor<ParkingEntity, ParkingReportDto> processor,
//            FlatFileItemWriter<ParkingReportDto> writer
//    ) {
//        return new StepBuilder("exportParkingStep", jobRepository)
//                .<ParkingEntity, ParkingReportDto>chunk(10, transactionManager)
//                .reader(parkingEntityReader)
//                .processor(processor)
//                .writer(writer)
//                .build();
//    }


//    @Bean
//    public Job exportParkingRepostJob(JobRepository jobRepository, Step exportParkingStep) {
//        return new JobBuilder("exportParkingStep", jobRepository)
//                .start(exportParkingStep)
//                .build();
//    }
}
