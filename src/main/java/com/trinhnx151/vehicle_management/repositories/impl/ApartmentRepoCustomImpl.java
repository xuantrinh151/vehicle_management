package com.trinhnx151.vehicle_management.repositories.impl;

import com.trinhnx151.vehicle_management.dto.sdi.apartment.ApartmentSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentSearchSdo;
import com.trinhnx151.vehicle_management.repositories.ApartmentRepoCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ApartmentRepoCustomImpl implements ApartmentRepoCustom {

    private final EntityManager em;

    @Override
    public Page<ApartmentSearchSdo> search(ApartmentSearchSdi request, Pageable pageable) {
        String keyword = request.getKeyword().toUpperCase();
        int pageSize = pageable.getPageSize();
        int firstResult = pageSize * (pageable.getPageNumber() - 1);
        Map<String, Object> queryParams = new HashMap<>();
        String sqlCountAll = "SELECT COUNT(1) ";
        String sqlGetData = "SELECT a.id,a.code,a.host_name, a.dob,a.email,a.phone,a.total_unpaid_fines,a.`status` ";
        StringBuilder sqlConditional = new StringBuilder();
        sqlConditional.append("FROM apartment a ");
        sqlConditional.append("WHERE a.`status` <> 2 ");
        if (!keyword.isEmpty()) {
            sqlConditional.append("AND ( UPPER(a.code) LIKE :keyword OR UPPER(a.host_name) LIKE :keyword OR a.phone LIKE :keyword) ");
            queryParams.put("keyword", "%" + keyword + "%");
        }
        Query query = em.createNativeQuery(sqlGetData + sqlConditional);
        query.setMaxResults(pageSize);
        query.setFirstResult(firstResult);
        queryParams.forEach(query::setParameter);

        List<Object[]> queryResult = query.getResultList();
        List<ApartmentSearchSdo> listData = new ArrayList<>();
        for (Object[] object : queryResult) {
            ApartmentSearchSdo apartmentSearchSdo = new ApartmentSearchSdo();
            apartmentSearchSdo.setId(((BigInteger) object[0]).longValue());
            apartmentSearchSdo.setCode((String) object[1]);
            apartmentSearchSdo.setHostName((String) object[2]);
            apartmentSearchSdo.setDob((Timestamp) object[3]);
            apartmentSearchSdo.setEmail((String) object[4]);
            apartmentSearchSdo.setPhone((String) object[5]);
            apartmentSearchSdo.setTotalUnpaidFines((Double) object[6]);
            apartmentSearchSdo.setStatus((Integer) object[7]);
            listData.add(apartmentSearchSdo);
        }
        Query queryCountAll = em.createNativeQuery(sqlCountAll + sqlConditional);
        int countAll = queryCountAll.getFirstResult();
        return new PageImpl<>(listData, pageable, countAll);
    }


}
