package com.apps.recruitment.repo;

import com.apps.recruitment.dao.InterviewEntity;
import com.apps.recruitment.models.KeyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewEntity, Integer> {

    @Query("select p from InterviewEntity p where p.candidateEntity.id = :candidate_id")
    List<InterviewEntity> findByCandidateEntity(@Param("candidate_id") Integer id);

    @Query("select new com.apps.recruitment.models.KeyValue(i.result, COUNT(i)) " +
            "from InterviewEntity as i ,CandidateEntity as c where c.id = i.candidateEntity.id" +
            "       and  c.department = :department  group by i.result")
    List<KeyValue> countTotalInterviewResults(@Param("department") String department);
}
