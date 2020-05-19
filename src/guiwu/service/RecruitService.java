package guiwu.service;
import guiwu.domain.*;

import java.util.List;

public interface RecruitService
{
    List<Recruit> getRecruit(EnterpriseUser enterpriseUser);
    List<Recruit> getRecruit(int eid);
    void addRecruit(EnterpriseUser enterpriseUser, Recruit recruit);
    void addRecruit(int eid, Recruit recruit);
    void addRecruit(int eid, String title, String position, String salary,
                    String description, String requirement,
                    String priority, String welfare);
    void delRecruit(int rid);
}
