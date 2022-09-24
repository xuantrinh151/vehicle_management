package com.trinhnx151.vehicle_management.repositories;

import com.trinhnx151.vehicle_management.entities.Apartment;
import com.trinhnx151.vehicle_management.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VehicleRepo extends JpaRepository<Vehicle,Long>,VehicleRepoCustom {

    @Query(
            value = "SELECT * FROM vehicle WHERE STATUS <> 2 AND ID = :id",
            nativeQuery = true
    )
    Optional<Vehicle> findById(@Param("id") Long id);

    @Query(
            value = "SELECT * FROM vehicle WHERE STATUS <> 2 AND UPPER(license_plates) = :licensePlates",
            nativeQuery = true
    )
    Optional<Vehicle> findByLicensePlates(@Param("licensePlates") String licensePlates);

    @Query(
            value = "SELECT * FROM vehicle WHERE STATUS <> 2 AND UPPER(license_plates) = :licensePlates AND ID <> :ignoreId",
            nativeQuery = true
    )
    Optional<Vehicle> findByLicensePlates(@Param("licensePlates") String licensePlates,@Param("ignoreId") Long ignoreId);

    @Query(
            value = "SELECT * FROM vehicle WHERE ID = :id",
            nativeQuery = true
    )
    Optional<Vehicle> findByIdWithAllStatus(@Param("id") Long id);
}
