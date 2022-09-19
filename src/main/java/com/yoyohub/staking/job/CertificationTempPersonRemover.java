package com.yoyohub.staking.job;

import com.yoyohub.staking.repository.TempPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CertificationTempPersonRemover {

    @Autowired private TempPersonRepository tempPersonRepo;

    @Scheduled(fixedDelay = 1000 * 60)
    public void remove() {
        int deletedCnt = tempPersonRepo.deleteOld();
        if (deletedCnt > 0)
            System.out.println("[데이터삭제] 본인인증 임시 데이터 : " + deletedCnt + " 건");
    }
}
