package guiwu.dao;

import guiwu.domain.*;

import java.util.List;

public interface RecruitDao
{
    /**
     * 查询总记录数
     */
    int getTotalCount();
    int getTotalCountOfStatus(String status);

    List<Recruit> getRecruit(EnterpriseUser enterpriseUser);
    List<Recruit> getRecruit(int eid);
    Recruit getARecruit(int rid);

    void addRecruit(EnterpriseUser enterpriseUser, Recruit recruit);
    void addRecruit(int eid, Recruit recruit);
    void addRecruit(int eid, String title, String position, String salary,
                    String description, String requirement,
                    String priority, String welfare);
    void delRecruit(int rid);
    void updateStatus(int rid, String status);
    void issueRecruit(int rid);

    RecruitInfo getARecruitInfo(int rid, String status);
    RecruitInfo getARecruitInfo(int rid);

    List<RecruitBrief> getRecruitBrief(String sql);
    List<RecruitBrief> getAllRecruitBrief();
    List<RecruitBrief> getSomeRecruitBrief(List<Integer> rids);
    List<RecruitBrief> getRecruitBrief(int start, int pageSize);
    List<RecruitBrief> getTheLatestRecruitBrief(int start, int pageSize);
    List<RecruitBrief> getTheHottestRecruitBrief(int start, int pageSize);
    List<RecruitBrief> searchBecruitBrief(String searchStr);

    List<RecruitMIBrief> getRecruitMIBrief(String sql);
    List<RecruitMIBrief> getRecruitMIBrief(int begin,int size);

}