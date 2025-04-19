//package org.example.sberparking;
//
//import org.example.sberparking.domain.CarEntity.CarEntity;
//import org.example.sberparking.domain.CarEntity.CreateCarDto;
//import org.example.sberparking.domain.CarEntity.EntryCarDto;
//import org.example.sberparking.domain.CarInfoEntity.CarInfoEntity;
//import org.example.sberparking.domain.ParkingEntity.ParkingEntity;
//import org.example.sberparking.enums.CarType;
//import org.example.sberparking.mapper.CarMapper;
//import org.example.sberparking.mapper.ParkingMapper;
//import org.example.sberparking.repository.CarInfoRepository;
//import org.example.sberparking.repository.CarRepository;
//import org.example.sberparking.repository.ParkingRepository;
//import org.example.sberparking.service.CarService;
//import org.example.sberparking.service.ParkingService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.Clock;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.when;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ExtendWith(MockitoExtension.class)
//class ParkingServiceTest {
//    @Mock
//    private CarRepository carRepository;
//
//    @Mock
//    private CarMapper carMapper;
//
//    @Mock
//    private ParkingRepository parkingRepository;
//
//    @Mock
//    private CarInfoRepository carInfoRepository;
//
//    @Mock
//    private ParkingMapper parkingMapper;
//
//    @InjectMocks
//    private ParkingService parkingService;
//    @InjectMocks
//    private CarService carService;
//
//    private EntryCarDto entryCarDto;
//    private CreateCarDto createCarDto;
//
//    @BeforeEach
//    void setUp() {
//        entryCarDto = new EntryCarDto(
//                "Number",
//                CarType.PASSENGER
//        );
//        createCarDto = new CreateCarDto(
//                "Number",
//                CarType.PASSENGER.getDescription()
//        );
//    }
//
////    @Test
////    void shouldReturnSuccessEntryParkingCar() {
////        var clock = Clock.fixed(Instant.parse("2025-04-19T12:00:00Z"), ZoneId.of("UTC"));
////        var now = LocalDateTime.now(clock);
////
////        var carEntity = new CarEntity();
////        carEntity.setCarType(CarType.fromDescription(createCarDto.type()));
////        carEntity.setNumber(createCarDto.number());
////
////        when(carMapper.toEntity(createCarDto)).thenReturn(carEntity);
////        doReturn(carEntity).when(carRepository).save(any(CarEntity.class));
////        carService.createCar(createCarDto);
////
////        var parkingEntity = new ParkingEntity();
////        parkingEntity.setCarInfoEntity(new CarInfoEntity(carEntity.getNumber(), carEntity.getCarType()));
////
////        when(parkingMapper.toEntity(parkingEntity.getCarInfoEntity())).thenReturn(parkingEntity);
////        doReturn(parkingEntity).when(carInfoRepository).save(any(CarInfoEntity.class));
////        doReturn(parkingEntity).when(parkingRepository).save(any(ParkingEntity.class));
////
////        String parkingEntryTime = parkingService.entryCar(entryCarDto);
////
////        assertThat(parkingEntryTime).isEqualTo(now.toString());
////    }
//
//}
