package com.trinhnx151.vehicle_management.dto.sdi.record;

import com.sun.istack.NotNull;
import com.trinhnx151.vehicle_management.dto.sdi.Image.ImageUploadSdi;
import com.trinhnx151.vehicle_management.dto.sdi.vehicle.VehicleCreateSdi;
import com.trinhnx151.vehicle_management.entities.Record;
import com.trinhnx151.vehicle_management.entities.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordCreateSdi {


    private VehicleCreateSdi vehicle;

    @NotNull
    @NotEmpty(message = "Vị trí vi phạm không được để trống")
    private String violationLocation;

    @NotNull
    @NotEmpty(message = "Nội dung vi phạm không được để trống")
    private String contentViolation;

    private ArrayList<ImageUploadSdi> imageList;

    private Boolean pull;

    private Boolean paymentStatus;

    public Record toRecord() {
        Record record = new Record();
        BeanUtils.copyProperties(this, record);
        record.setStatus(Vehicle.Status.IN_USE);
        return record;
    }
}
