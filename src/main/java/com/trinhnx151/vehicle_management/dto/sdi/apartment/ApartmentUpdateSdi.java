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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentUpdateSdi {
    private Long id;

    @NotNull
    @NotEmpty(message = "Mã căn hộ không được để trống")
    private String code;

    @NotNull
    @NotEmpty(message = "Tên chủ xe không được để trống")
    private String hostName;

    private Timestamp dob;

    @NotNull
    @NotEmpty(message = "Số điện thoại không được để trống")
    private String phone;

    @NotNull
    @NotEmpty(message = "Email không được để trống")
    @Email(message = "Sai định dạng email")
    private String email;

    @NotNull
    @Min(value = 0,message = "Số tiền phải lớn hoặc bằng 0")
    private double totalUnpaidFines;

    @NotNull
    @Min(value = 0,message = "Trạng thái gồm: 0-Không hoạt động, 1-Đang hoạt động, 2-Đã xóa")
    @Max(value = 2,message = "Trạng thái gồm: 0-Không hoạt động, 1-Đang hoạt động, 2-Đã xóa")
    private Integer status;

    public Apartment toApartment() {
        Apartment apartment = new Apartment();
        BeanUtils.copyProperties(this, apartment);
        return apartment;
    }
}
