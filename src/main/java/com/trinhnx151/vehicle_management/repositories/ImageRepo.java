package com.trinhnx151.vehicle_management.repositories;

import com.trinhnx151.vehicle_management.entities.Image;
import com.trinhnx151.vehicle_management.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ImageRepo extends JpaRepository<Image,Long> {
    ArrayList<Image> findAllByRecordId(Long id);

    @Transactional
    @Modifying
    @Query(
            value = "DELETE FROM IMAGE WHERE record_id = :id",
            nativeQuery = true
    )
    void deleteByRecordId(Long id);
}
