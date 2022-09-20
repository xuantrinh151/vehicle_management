package com.trinhnx151.vehicle_management.controllers;

import com.trinhnx151.vehicle_management.services.ApartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(ApartmentController.class)
public class ApartmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private ApartmentService apartmentService;

    @Test
    void testSearch(){

    }
}
