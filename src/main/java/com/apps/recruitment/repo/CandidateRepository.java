package com.apps.recruitment.repo;

import com.apps.recruitment.dao.CandidateEntity;
import com.apps.recruitment.models.KeyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidateRepository extends JpaRepository<CandidateEntity, Integer> {

    @Query("select new com.apps.recruitment.models.KeyValue( cand.department ,COUNT(cand)) from CandidateEntity as cand" +
            " where cand.statusCode = :statusCode group by cand.department")
    List<KeyValue> countTotalAcceptOfDepartment(@Param("statusCode") String statusCode);

}
