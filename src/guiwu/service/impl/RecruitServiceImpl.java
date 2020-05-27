package guiwu.service.impl;

import guiwu.dao.RecruitDao;
import guiwu.dao.impl.RecruitDaoImpl;
import guiwu.domain.EnterpriseUser;
import guiwu.domain.Recruit;
import guiwu.domain.RecruitBrief;
import guiwu.domain.RecruitInfo;
import guiwu.service.RecruitService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecruitServiceImpl implements RecruitService
{
    private RecruitDao recruitDao = new RecruitDaoImpl();
    @Override
    public List<Recruit> getRecruit(EnterpriseUser enterpriseUser)
    {
        return recruitDao.getRecruit(enterpriseUser);
    }
    @Override
    public List<Recruit> getRecruit(int eid)
    {
        return  recruitDao.getRecruit(eid);
    }
    @Override
    public void addRecruit(EnterpriseUser enterpriseUser, Recruit recruit)
    {
        recruitDao.addRecruit(enterpriseUser, recruit);
    }
    @Override
    public void addRecruit(int eid, Recruit recruit)
    {
        recruitDao.addRecruit(eid, recruit);
    }
    @Override
    public void addRecruit(int eid, String title, String position, String salary,
                           String description, String requirement,
                           String priority, String welfare)
    {
        recruitDao.addRecruit(eid, title, position, salary, description, requirement, priority, welfare);
    }

    @Override
    public void delRecruit(int rid)
    {
        recruitDao.delRecruit(rid);
    }

    @Override
    public void updateStatus(int rid, String status)
    {
        recruitDao.updateStatus(rid, status);
    }

    @Override
    public void issueRecruit(int rid)
    {
        recruitDao.issueRecruit(rid);
    }

    @Override
    public Recruit getARecruit(int rid)
    {
        return recruitDao.getARecruit(rid);
    }

    @Override
    public RecruitInfo getARecruitInfo(int rid, String status)
    {
        return recruitDao.getARecruitInfo(rid, status);
    }
    @Override
    public RecruitInfo getARecruitInfo(int rid)
    {
        return recruitDao.getARecruitInfo(rid);
    }
    @Override
    public List<RecruitBrief> getAllRecruitBrief()
    {
        return recruitDao.getAllRecruitBrief();
    }
    @Override
    public List<RecruitBrief> getRecruitBrief(int start, int pageSize)
    {
        return recruitDao.getRecruitBrief(start, pageSize);
    }
    @Override
    public List<RecruitBrief> getTheLatestRecruitBrief(int start, int pageSize)
    {
        return recruitDao.getTheLatestRecruitBrief(start, pageSize);
    }
    @Override
    public List<RecruitBrief> getTheHottestRecruitBrief(int start, int pageSize)
    {
        return recruitDao.getTheHottestRecruitBrief(start, pageSize);
    }
    @Override
    public int getTotalCount()
    {
        return recruitDao.getTotalCount();
    }

    @Override
    public int getTotatlCountOfStatus(String status)
    {
        return recruitDao.getTotalCountByStatus(status);
    }


    @Override
    public List<RecruitBrief> searchBecruitBrief(String searchStr)
    {
        return recruitDao.searchBecruitBrief(searchStr);
    }

    @Override
    public List<RecruitBrief> getSomeRecruitBrief(List<Integer> rids)
    {
        return recruitDao.getSomeRecruitBrief(rids);
    }
    @Override
    public List<RecruitBrief> getSomeRecruitBrief(Set<String> rids)
    {
        List<Integer> list = new ArrayList<>();
        for (String rid : rids)
        {
            list.add(Integer.parseInt(rid));
        }
        return recruitDao.getSomeRecruitBrief(list);
    }
}
