package com.trinhnx151.vehicle_management.services;

import com.trinhnx151.vehicle_management.dto.sdi.apartment.ApartmentCreateSdi;
import com.trinhnx151.vehicle_management.dto.sdi.apartment.ApartmentSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdi.apartment.ApartmentUpdateSdi;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentCreateSdo;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentSearchSdo;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentSelfSdo;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentUpdateSdo;
import com.trinhnx151.vehicle_management.repositories.ApartmentRepo;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@RequiredArgsConstructor
public class ApartmentServiceTest {
    @TestConfiguration
    public static class ApartmentServiceTestConfiguration {

        @Bean
        ApartmentService apartmentService() {
            return new ApartmentService() {
                @Override
                public Page<ApartmentSearchSdo> search(ApartmentSearchSdi request, Pageable pageable) {
                    return null;
                }

                @Override
                public ApartmentSelfSdo findById(Long id) {
                    return null;
                }

                @Override
                public ApartmentCreateSdo create(ApartmentCreateSdi request) {
                    return null;
                }

                @Override
                public ApartmentUpdateSdo update(ApartmentUpdateSdi request) {
                    return null;
                }

                @Override
                public Boolean deleteById(Long id) {
                    return null;
                }
            };
        }
    }

    @MockBean
    ApartmentRepo apartmentRepo;

    private final ApartmentService apartmentService;

    @Before
    public void setUp(){
        ApartmentSearchSdi apartmentSearchSdi = ApartmentSearchSdi.builder().keyword("van").build();
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 1;
            }

            @Override
            public int getPageSize() {
                return 3;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int pageNumber) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
    }

    @Test
    public void test(){
    }
}
