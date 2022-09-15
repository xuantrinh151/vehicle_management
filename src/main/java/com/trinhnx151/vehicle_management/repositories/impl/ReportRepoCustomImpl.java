package com.trinhnx151.vehicle_management.repositories.impl;

import com.trinhnx151.vehicle_management.dto.sdo.report.VehicleReportSdo;
import com.trinhnx151.vehicle_management.repositories.ReportRepoCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportRepoCustomImpl implements ReportRepoCustom {

    private final EntityManager em;

    @Override
    public Page<VehicleReportSdo> listMostViolations(Pageable pageable) {

        int pageSize = pageable.getPageSize();
        int firstResult = pageSize * (pageable.getPageNumber() - 1);

        String sqlCountAll = "SELECT COUNT(1) ";
        String sqlGetData = "SELECT v.license_plates,v.vehicle_company,v.type_vehicle,a.code,v.`status`,count(r.id)  ";
        StringBuilder sqlConditional = new StringBuilder();
        sqlConditional.append("FROM vehicle v  ");
        sqlConditional.append("INNER JOIN record r ON v.id = r.vehicle_id ");
        sqlConditional.append("INNER JOIN apartment a ON a.id = v.apartment_id ");
        sqlConditional.append("GROUP BY r.vehicle_id ");
        sqlConditional.append("ORDER BY count(r.id) desc ");
        Query query = em.createNativeQuery(sqlGetData + sqlConditional);
        query.setMaxResults(pageSize);
        query.setFirstResult(firstResult);


        List<Object[]> queryResult = query.getResultList();
        List<VehicleReportSdo> listData = new ArrayList<>();
        for (Object[] object : queryResult) {
            VehicleReportSdo vehicleReportSdo = new VehicleReportSdo();
            vehicleReportSdo.setLicensePlates((String) object[0]);
            vehicleReportSdo.setVehicleCompany((String) object[1]);
            vehicleReportSdo.setTypeVehicle((String) object[2]);
            vehicleReportSdo.setApartmentCode((String) object[3]);
            vehicleReportSdo.setStatus((Integer) object[4]);
            vehicleReportSdo.setTotal(((BigInteger) object[5]).intValue());
            listData.add(vehicleReportSdo);
        }
        Query queryCountAll = em.createNativeQuery(sqlCountAll + sqlConditional);
        int countAll = queryCountAll.getFirstResult();
        return new PageImpl<>(listData, pageable, countAll);
    }
}
