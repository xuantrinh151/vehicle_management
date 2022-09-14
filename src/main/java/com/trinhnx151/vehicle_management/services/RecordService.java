package com.trinhnx151.vehicle_management.services;

import com.trinhnx151.vehicle_management.dto.sdi.record.RecordCreateSdi;
import com.trinhnx151.vehicle_management.dto.sdi.record.RecordSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdi.record.RecordUpdateSdi;
import com.trinhnx151.vehicle_management.dto.sdo.record.RecordCreateSdo;
import com.trinhnx151.vehicle_management.dto.sdo.record.RecordSearchSdo;
import com.trinhnx151.vehicle_management.dto.sdo.record.RecordUpdateSdo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordService {
    Page<RecordSearchSdo> search(RecordSearchSdi request, Pageable pageable);

    RecordCreateSdo create(RecordCreateSdi request);

    RecordUpdateSdo update(RecordUpdateSdi request);

    Boolean deleteById(Long id);

}
