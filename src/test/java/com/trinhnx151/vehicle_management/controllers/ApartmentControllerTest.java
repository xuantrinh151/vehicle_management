package com.trinhnx151.vehicle_management.controllers;

import com.trinhnx151.vehicle_management.services.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ApartmentController.class)
@RequiredArgsConstructor
public class ApartmentControllerTest {
    private final MockMvc mvc;

    @MockBean
    private ApartmentService apartmentService;


}
