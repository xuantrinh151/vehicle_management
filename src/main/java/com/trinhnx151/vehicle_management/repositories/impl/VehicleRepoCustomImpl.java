package com.trinhnx151.vehicle_management.repositories.impl;

import com.trinhnx151.vehicle_management.dto.sdi.vehicle.VehicleSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdo.vehicle.VehicleSearchSdo;
import com.trinhnx151.vehicle_management.repositories.VehicleRepoCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class VehicleRepoCustomImpl implements VehicleRepoCustom {
    private final EntityManager em;

    @Override
    public Page<VehicleSearchSdo> search(VehicleSearchSdi request, Pageable pageable) {
        String keyword = request.getKeyword().toUpperCase();
        int pageSize = pageable.getPageSize();
        int firstResult = pageSize * (pageable.getPageNumber() - 1);

        Map<String, Object> queryParams = new HashMap<>();

        String sqlCountAll = "SELECT COUNT(1) ";
        String sqlGetData = "SELECT V.id,V.apartment_id,V.license_plates,V.vehicle_company,V.type_vehicle,V.`status` ";
        StringBuilder sqlConditional = new StringBuilder();
        sqlConditional.append("FROM vehicle V ");
        sqlConditional.append("INNER JOIN apartment A ON A.id = V.apartment_id ");
        sqlConditional.append("WHERE V.`status` <> 2 ");
        if (!keyword.isEmpty()) {
            sqlConditional.append("AND ( UPPER(V.license_plates) LIKE :keyword OR UPPER(V.vehicle_company) LIKE :keyword OR UPPER(V.type_vehicle) LIKE :keyword OR UPPER(A.code) LIKE :keyword) ");
            queryParams.put("keyword", "%" + keyword + "%");
        }
        Query query = em.createNativeQuery(sqlGetData + sqlConditional);
        query.setMaxResults(pageSize);
        query.setFirstResult(firstResult);
        queryParams.forEach(query::setParameter);

        List<Object[]> queryResult = query.getResultList();
        List<VehicleSearchSdo> listData = new ArrayList<>();
        for (Object[] object : queryResult) {
            VehicleSearchSdo vehicleSearchSdo = new VehicleSearchSdo();
            vehicleSearchSdo.setId(((BigInteger) object[0]).longValue());
            vehicleSearchSdo.setApartmentId(((BigInteger) object[1]).longValue());
            vehicleSearchSdo.setLicensePlates((String) object[2]);
            vehicleSearchSdo.setVehicleCompany((String) object[3]);
            vehicleSearchSdo.setTypeVehicle((String) object[4]);
            vehicleSearchSdo.setStatus((Integer) object[5]);
            listData.add(vehicleSearchSdo);
        }
        Query queryCountAll = em.createNativeQuery(sqlCountAll + sqlConditional);
        int countAll = queryCountAll.getFirstResult();
        return new PageImpl<>(listData, pageable, countAll);
    }
}
