package guiwu.dao;

import guiwu.domain.Apply;
import guiwu.domain.ApplyInfo;
import guiwu.domain.PersonalUser;

import java.util.List;

public interface ApplyDao
{
    List<Apply> getPersonalApply(int pid);
    List<Apply> getRecruitApply(int rid);
    List<ApplyInfo> getApplyInfo(int eid);
    List<ApplyInfo> getPersonalApplyInfo(int pid);
    List<ApplyInfo> getRecruitApplyInfo(int rid);
    Apply getPersonalApply(int pid, int rid);
    void updateStatus(int aid, String status);
    void addApply(PersonalUser personalUser, Apply apply);
    void addApply(int pid, Apply apply);
    void addApply(int pid, int rid, String status);
    void delApply(int aid);
}
