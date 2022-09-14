package com.trinhnx151.vehicle_management.dto.sdi.apartment;

import com.sun.istack.NotNull;
import com.trinhnx151.vehicle_management.entities.Apartment;
import com.trinhnx151.vehicle_management.entities.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentCreateSdi {
    @NotNull
    @NotEmpty(message = "Mã căn hộ không được để trống")
    private String code;

    @NotNull
    @NotEmpty(message = "Tên chủ hộ không được để trống")
    private String hostName;

    private Timestamp dob;

    @NotNull
    @NotEmpty(message = "Điện thoại không được để trống")
    private String phone;

    @NotNull
    @NotEmpty(message = "Email không được để trống")
    @Email(message = "Sai định dạng email")
    private String email;

    public Apartment toApartment() {
        Apartment apartment = new Apartment();
        BeanUtils.copyProperties(this, apartment);
        apartment.setStatus(Vehicle.Status.IN_USE);
        apartment.setTotalUnpaidFines(0);
        return apartment;
    }
}
