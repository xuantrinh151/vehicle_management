package com.trinhnx151.vehicle_management.controllers;


import com.trinhnx151.vehicle_management.dto.sdi.record.RecordSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdo.record.RecordSearchSdo;
import com.trinhnx151.vehicle_management.services.RecordService;
import com.trinhnx151.vehicle_management.services.StorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecordController.class)
public class RecordControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RecordService recordService;

    @MockBean
    private StorageService storageService;

    List<RecordSearchSdo> records = new ArrayList<>();

    @BeforeEach
    void setUp() {
        RecordSearchSdo r1 = RecordSearchSdo.builder()
                .id(3L)
                .licensePlates("89E-99999")
                .violationLocation("lan xe 01")
                .contentViolation("de sai cho")
                .pull(true).fineAmount(2000.0)
                .paymentStatus(false)
                .status(1)
                .build();
        RecordSearchSdo r2 = RecordSearchSdo.builder()
                .id(4L)
                .licensePlates("29H-66666")
                .violationLocation("lan xe 02")
                .contentViolation("de sai cho")
                .pull(false).fineAmount(500.0)
                .paymentStatus(true)
                .status(1)
                .build();
        records.add(r1);
        records.add(r2);
    }

    @DisplayName("search record with content is 'de sai cho'")
    @Test
    void testSearch() throws Exception {
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

        Page<RecordSearchSdo> page = new PageImpl<>(records, pageable, 28);

        given(recordService.search(RecordSearchSdi.builder().keyword("de sai cho").build(), pageable)).willReturn(page);

        mvc.perform(get("/api/v1/record/search").contentType(MediaType.APPLICATION_JSON)) // Thực hiện GET REQUEST
                .andExpect(status().isOk())
                .andDo(print());
    }


    @DisplayName("create record ")
    @Test
    void testCrete() throws Exception {
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

        Page<RecordSearchSdo> page = new PageImpl<>(records, pageable, 28);

        given(recordService.search(RecordSearchSdi.builder().keyword("de sai cho").build(), pageable)).willReturn(page);

        mvc.perform(get("/api/v1/record/search").contentType(MediaType.APPLICATION_JSON)) // Thực hiện GET REQUEST
                .andExpect(status().isOk());
    }
}
