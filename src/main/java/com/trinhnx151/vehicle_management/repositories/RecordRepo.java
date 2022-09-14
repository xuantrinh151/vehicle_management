package com.trinhnx151.vehicle_management.repositories;

import com.trinhnx151.vehicle_management.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface RecordRepo extends JpaRepository<Record,Long>,RecordRepoCustom {

}
