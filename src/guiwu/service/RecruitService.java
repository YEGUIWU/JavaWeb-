package guiwu.service;
import guiwu.domain.*;

import java.util.List;
import java.util.Set;

public interface RecruitService
{
    List<Recruit> getRecruit(EnterpriseUser enterpriseUser);
    List<Recruit> getRecruit(int eid);
    Recruit getARecruit(int rid);
    RecruitInfo getARecruitInfo(int rid, String status);
    RecruitInfo getARecruitInfo(int rid);
    List<RecruitBrief> getAllRecruitBrief();
    List<RecruitBrief> getRecruitBrief(int start, int pageSize);
    List<RecruitBrief> getTheLatestRecruitBrief(int start, int pageSize);
    List<RecruitBrief> getTheHottestRecruitBrief(int start, int pageSize);
    List<RecruitBrief> getSomeRecruitBrief(List<Integer> rids);
    List<RecruitBrief> getSomeRecruitBrief(Set<String> rids);
    List<RecruitBrief> searchBecruitBrief(String searchStr);
    int getTotalCount();
    int getTotatlCountOfStatus(String status);
    void addRecruit(EnterpriseUser enterpriseUser, Recruit recruit);
    void addRecruit(int eid, Recruit recruit);
    void addRecruit(int eid, String title, String position, String salary,
                    String description, String requirement,
                    String priority, String welfare);
    void delRecruit(int rid);
    void updateStatus(int rid, String status);
    void issueRecruit(int rid);
}
