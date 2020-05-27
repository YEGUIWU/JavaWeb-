package guiwu.service.impl;

import guiwu.dao.ApplyDao;
import guiwu.dao.BlacklistDao;
import guiwu.dao.RecruitDao;
import guiwu.dao.UserDao;
import guiwu.dao.impl.ApplyDaoImpl;
import guiwu.dao.impl.BlacklistDaoImpl;
import guiwu.dao.impl.RecruitDaoImpl;
import guiwu.dao.impl.UserDaoImpl;
import guiwu.domain.*;
import guiwu.service.ApplyService;

import java.util.List;

public class ApplyServiceImpl implements ApplyService
{
    ApplyDao applyDao = new ApplyDaoImpl();
    BlacklistDao blacklistDao = new BlacklistDaoImpl();
    RecruitDao recruitDao = new RecruitDaoImpl();
    @Override
    public List<Apply> getPersonalApply(int pid)
    {
        return applyDao.getPersonalApply(pid);
    }
    @Override
    public List<Apply> getRecruitApply(int rid)
    {
        return  applyDao.getRecruitApply(rid);
    }

    @Override
    public List<ApplyInfo> getPersonalApplyInfo(int pid)
    {
        return applyDao.getPersonalApplyInfo(pid);
    }
    @Override
    public List<ApplyInfo> getRecruitApplyInfo(int rid)
    {
        return  applyDao.getRecruitApplyInfo(rid);
    }
    @Override
    public List<ApplyInfo> getApplyInfo(int eid)
    {
        return applyDao.getApplyInfo(eid);
    }
    @Override
    public void updateStatus(int aid, String status)
    {
        applyDao.updateStatus(aid, status);
    }
    @Override
    public void addApply(PersonalUser personalUser, Apply apply)
    {
        applyDao.addApply(personalUser, apply);
    }
    @Override
    public void addApply(int pid, Apply apply)
    {
        applyDao.addApply(pid, apply);
    }
    @Override
    public void addApply(int pid, int rid, String status)
    {
        applyDao.addApply(pid, rid, status);
    }
    @Override
    public void delApply(int aid)
    {
        applyDao.delApply(aid);
    }

    @Override
    public Apply getPersonalApply(int pid, int rid)
    {
        return applyDao.getPersonalApply(pid, rid);
    }

    @Override
    public void apply(int pid, int rid)
    {
        Recruit recruit = recruitDao.getARecruit(rid);
        if (recruit != null)
        {
            Blacklist blacklist = blacklistDao.find(recruit.getEid(), pid);
            if (blacklist == null)
            {
                applyDao.addApply(pid, rid, "待接受");
            }
        }
    }
}
