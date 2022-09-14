package com.trinhnx151.vehicle_management.dto.sdi.record;

import com.sun.istack.NotNull;
import com.trinhnx151.vehicle_management.dto.sdi.Image.ImageUploadSdi;
import com.trinhnx151.vehicle_management.entities.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordUpdateSdi {

    private Long id;

    @NotNull
    @NotEmpty(message = "Vị trí vi phạm không được để trống")
    private String violationLocation;

    @NotNull
    @NotEmpty(message = "Nội dung vi phạm không được để trống")
    private String contentViolation;

    private ArrayList<ImageUploadSdi> imageList;

    private Long vehicleId;

    private Boolean pull;

    private Boolean paymentStatus;

    @NotNull
    @Min(value = 0, message = "Trạng thái gồm: 0-Không hoạt động, 1-Đang hoạt động, 2-Đã xóa")
    @Max(value = 2, message = "Trạng thái gồm: 0-Không hoạt động, 1-Đang hoạt động, 2-Đã xóa")
    private Integer status;

    public Record toRecord() {
        Record record = new Record();
        BeanUtils.copyProperties(this, record);
        return record;
    }
}
