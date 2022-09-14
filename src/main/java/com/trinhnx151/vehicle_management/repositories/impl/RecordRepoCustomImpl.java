package com.trinhnx151.vehicle_management.repositories.impl;

import com.trinhnx151.vehicle_management.dto.sdi.record.RecordSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdo.record.RecordSearchSdo;
import com.trinhnx151.vehicle_management.repositories.RecordRepoCustom;
import com.trinhnx151.vehicle_management.services.ImageService;
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
public class RecordRepoCustomImpl implements RecordRepoCustom {

    private final EntityManager em;
    private final ImageService imageService;

    @Override
    public Page<RecordSearchSdo> search(RecordSearchSdi request, Pageable pageable) {
        String keyword = request.getKeyword().toUpperCase();
        String paymentStatus = request.getPaymentStatus();
        String pull = request.getPull();
        int pageSize = pageable.getPageSize();
        int firstResult = pageSize * (pageable.getPageNumber() - 1);

        Map<String, Object> queryParams = new HashMap<>();

        String sqlCountAll = "SELECT COUNT(1) ";
        String sqlGetData = "SELECT r.id,v.license_plates,r.content_violation,r.violation_location,r.pull,r.fine_amount,r.payment_status,r.`status` ";
        StringBuilder sqlConditional = new StringBuilder();
        sqlConditional.append("FROM record r  ");
        sqlConditional.append("INNER JOIN vehicle v ON r.vehicle_id = v.id ");
        sqlConditional.append("WHERE r.`status` <> 2  ");
        if (!keyword.isEmpty()) {
            sqlConditional.append("AND ( UPPER(v.license_plates) LIKE :keyword OR UPPER(r.content_violation) LIKE :keyword OR UPPER(r.violation_location) LIKE :keyword) ");
            queryParams.put("keyword", "%" + keyword + "%");
        }
        if (paymentStatus != null) {
            if (paymentStatus.equalsIgnoreCase("true") || paymentStatus.equalsIgnoreCase("false")) {
                sqlConditional.append("AND r.payment_status = :paymentStatus ");
                queryParams.put("paymentStatus", paymentStatus);
            }
        }
        if (pull != null) {
            if (pull.equalsIgnoreCase("true") || pull.equalsIgnoreCase("false")) {
                sqlConditional.append("AND r.pull = :pull ");
                queryParams.put("pull", pull);
            }
        }
        Query query = em.createNativeQuery(sqlGetData + sqlConditional);
        query.setMaxResults(pageSize);
        query.setFirstResult(firstResult);
        queryParams.forEach(query::setParameter);

        List<Object[]> queryResult = query.getResultList();
        List<RecordSearchSdo> listData = new ArrayList<>();
        for (Object[] object : queryResult) {
            RecordSearchSdo recordSearchSdo = new RecordSearchSdo();
            recordSearchSdo.setId(((BigInteger) object[0]).longValue());
            recordSearchSdo.setLicensePlates((String) object[1]);
            recordSearchSdo.setContentViolation((String) object[2]);
            recordSearchSdo.setViolationLocation((String) object[3]);
            recordSearchSdo.setImageList(imageService.getImageByRecordId(((BigInteger) object[0]).longValue()));
            recordSearchSdo.setPull((Boolean) object[4]);
            recordSearchSdo.setFineAmount((Double) object[5]);
            recordSearchSdo.setPaymentStatus((Boolean) object[6]);
            recordSearchSdo.setStatus((Integer) object[7]);
            listData.add(recordSearchSdo);
        }
        Query queryCountAll = em.createNativeQuery(sqlCountAll + sqlConditional);
        int countAll = queryCountAll.getFirstResult();
        return new PageImpl<>(listData, pageable, countAll);
    }
}
