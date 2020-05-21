package guiwu.dao;

import guiwu.domain.EnterpriseUser;
import guiwu.domain.Recruit;

import java.util.List;

public interface RecruitDao
{
    List<Recruit> getRecruit(EnterpriseUser enterpriseUser);
    List<Recruit> getRecruit(int eid);
    void addRecruit(EnterpriseUser enterpriseUser, Recruit recruit);
    void addRecruit(int eid, Recruit recruit);
    void addRecruit(int eid, String title, String position, String salary,
                    String description, String requirement,
                    String priority, String welfare);
    void delRecruit(int rid);
    void updateStatus(int rid, String status);
    void issueRecruit(int rid);
}