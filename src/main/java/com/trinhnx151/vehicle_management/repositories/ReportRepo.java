package com.trinhnx151.vehicle_management.repositories;

import com.trinhnx151.vehicle_management.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepo extends JpaRepository<Vehicle,Long>,ReportRepoCustom{
}
