package com.yoyohub.staking.repository;

import com.yoyohub.staking.entity.TempPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TempPersonRepository extends JpaRepository<TempPerson, String> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM temp_person WHERE created_date < DATE_SUB(NOW(), INTERVAL 5 MINUTE)", nativeQuery = true)
    public int deleteOld();
}
