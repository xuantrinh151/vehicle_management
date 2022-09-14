package com.trinhnx151.vehicle_management.repositories;

import com.trinhnx151.vehicle_management.entities.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApartmentRepo extends JpaRepository<Apartment,Long>,ApartmentRepoCustom {
    @Query(
            value = "SELECT * FROM APARTMENT WHERE STATUS <> 2 AND ID = :apartmentId",
            nativeQuery = true
    )
    Optional<Apartment> findById(Long apartmentId);


    @Query(
            value = "SELECT * FROM APARTMENT WHERE STATUS <> 2 AND UPPER(CODE) = :apartmentCode AND ID <> :ignoreId",
            nativeQuery = true
    )
    Optional<Apartment> findByCode(String apartmentCode,Long ignoreId);

    @Query(
            value = "SELECT * FROM APARTMENT WHERE STATUS <> 2 AND UPPER(CODE) = :apartmentCode",
            nativeQuery = true
    )
    Optional<Apartment> findByCode(String apartmentCode);

    @Query(
            value = "SELECT * FROM APARTMENT WHERE ID = :id",
            nativeQuery = true
    )
    Optional<Apartment> findByIdWithAllStatus(Long id);

    @Query(
            value = "SELECT SUM(r.fine_amount) AS total FROM apartment a \n" +
                    "INNER JOIN vehicle v ON a.id = v.apartment_id\n" +
                    "INNER JOIN record r ON r.vehicle_id = v.id \n" +
                    "WHERE a.`status` <> 2 AND r.payment_status = 0 AND  a.id = :id",
            nativeQuery = true
    )
    Double getTotalUnpaidFines(Long id);

}
