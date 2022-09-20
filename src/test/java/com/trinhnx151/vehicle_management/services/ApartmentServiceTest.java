package com.trinhnx151.vehicle_management.services;

import com.trinhnx151.vehicle_management.entities.Apartment;
import com.trinhnx151.vehicle_management.repositories.ApartmentRepo;
import com.trinhnx151.vehicle_management.services.impl.ApartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApartmentServiceTest {
    @Mock
    static
    ApartmentRepo apartmentRepo;

    @TestConfiguration
    public static class ApartmentServiceTestConfiguration {
        @Bean
        ApartmentServiceImpl apartmentService() {
            return new ApartmentServiceImpl(apartmentRepo);
        }
    }

    @InjectMocks
    private ApartmentServiceImpl apartmentService;

    @BeforeEach
    public void setUp() {
        Optional<Apartment> apartment = Optional.of(Apartment.builder().code("CHA").email("trinhnx151@gmail.com").hostName("nguyen van a").phone("032123455").build());
        when(apartmentRepo.findById(1L)).thenReturn(apartment);
    }


    @Test
    void testApartment() {
        assertEquals("032123455", apartmentService.findById(1L).getPhone());
    }
}
