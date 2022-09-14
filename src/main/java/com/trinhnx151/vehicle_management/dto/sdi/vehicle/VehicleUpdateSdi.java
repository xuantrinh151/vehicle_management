package com.trinhnx151.vehicle_management.dto.sdi.vehicle;

import com.sun.istack.NotNull;
import com.trinhnx151.vehicle_management.entities.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleUpdateSdi {

    private Long id;

    @NotNull
    @NotEmpty(message = "Biển số không được để trống")
    private String licensePlates;

    @NotNull
    @NotEmpty(message = "Hãng xe không được để trống")
    private String vehicleCompany;

    @NotNull
    @NotEmpty(message = "Loại xe không được để trống")
    private String typeVehicle;

    private String apartmentCode;

    @NotNull
    @Min(value = 0,message = "Trạng thái gồm: 0-Không hoạt động, 1-Đang hoạt động, 2-Đã xóa")
    @Max(value = 2,message = "Trạng thái gồm: 0-Không hoạt động, 1-Đang hoạt động, 2-Đã xóa")
    private Integer status;

    public Vehicle toVehicle() {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(this, vehicle);
        return vehicle;
    }
}
