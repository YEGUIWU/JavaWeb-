package guiwu.service.impl;

import guiwu.dao.RecruitDao;
import guiwu.dao.impl.RecruitDaoImpl;
import guiwu.domain.EnterpriseUser;
import guiwu.domain.Recruit;
import guiwu.service.RecruitService;

import java.util.List;

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

}
