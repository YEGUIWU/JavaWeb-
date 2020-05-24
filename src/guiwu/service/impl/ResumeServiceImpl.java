package guiwu.service.impl;

import guiwu.dao.ExpDao;
import guiwu.dao.UserDao;
import guiwu.dao.impl.ExpDaoImpl;
import guiwu.dao.impl.UserDaoImpl;
import guiwu.domain.EnterpriseInfo;
import guiwu.domain.Exp;
import guiwu.domain.PersonalUser;
import guiwu.domain.Resume;
import guiwu.service.ResumeService;

import java.util.List;

public class ResumeServiceImpl implements ResumeService
{
    private ExpDao expDao = new ExpDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    @Override
    public Resume getResume(int pid)
    {
        Resume resume = new Resume();
        resume.setWorkExpList(expDao.getWorkExp(pid));
        resume.setProjectExpList(expDao.getProjectExp(pid));
        resume.setPersonalInfo(userDao.findPersonalUser(pid));
        return  resume;
    }

    @Override
    public EnterpriseInfo getEnterpriseInfo(int eid)
    {
        return new EnterpriseInfo(userDao.findEnterpriseUser(eid));
    }
}
