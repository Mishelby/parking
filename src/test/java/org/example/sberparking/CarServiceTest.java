//package org.example.sberparking;
//
//import org.example.sberparking.domain.CarEntity.CarEntity;
//import org.example.sberparking.domain.CarEntity.CreateCarDto;
//import org.example.sberparking.enums.CarType;
//import org.example.sberparking.mapper.CarMapper;
//import org.example.sberparking.repository.CarRepository;
//import org.example.sberparking.service.CarService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class CarServiceTest {
//    @Mock
//    private CarRepository carRepository;
//
//    @Mock
//    private CarMapper carMapper;
//
//    @InjectMocks
//    private CarService carService;
//
//    private CreateCarDto createCarDto;
//
//    @BeforeEach
//    void setUp() {
//        createCarDto = new CreateCarDto(
//                "Number",
//                CarType.PASSENGER.getDescription()
//        );
//    }
//
//    @Test
//    void shouldSaveCarSuccessfully() {
//        CarEntity carEntity = new CarEntity();
//        carEntity.setCarType(CarType.fromDescription(createCarDto.type()));
//        carEntity.setNumber(createCarDto.number());
//
//        when(carMapper.toEntity(createCarDto)).thenReturn(carEntity);
//        doReturn(carEntity).when(carRepository).save(any(CarEntity.class));
//        String savedCarNumber = carService.createCar(createCarDto);
//
//        assertThat(savedCarNumber).isEqualTo(carEntity.getNumber());
//        assertThat(carEntity.getCarType()).isEqualTo(CarType.fromDescription(createCarDto.type()));
//        verify(carRepository, times(1)).save(any(CarEntity.class));
//    }
//}
