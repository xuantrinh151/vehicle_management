package com.trinhnx151.vehicle_management.dto.sdi.vehicle;

import com.sun.istack.NotNull;
import com.trinhnx151.vehicle_management.entities.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleCreateSdi {

    @NotNull
    @NotEmpty(message = "Biển số không được để trống")
    private String licensePlates;

    @NotNull
    @NotEmpty(message = "Hãng xe không được để trống")
    private String vehicleCompany;

    @NotNull
    @NotEmpty(message = "Loại xe không được để trống")
    private String typeVehicle;

    @NotNull
    @NotEmpty(message = "không được để trống")
    private Long apartmentId;

    public Vehicle toVehicle() {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(this, vehicle);
        vehicle.setStatus(Vehicle.Status.IN_USE);
        return vehicle;
    }
}
