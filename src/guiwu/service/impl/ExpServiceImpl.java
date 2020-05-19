package guiwu.service.impl;

import guiwu.dao.ExpDao;
import guiwu.dao.impl.ExpDaoImpl;
import guiwu.domain.Exp;
import guiwu.domain.PersonalUser;
import guiwu.service.ExpService;

import java.util.List;

public class ExpServiceImpl implements ExpService
{

    private ExpDao expDao = new ExpDaoImpl();
    /**
     * 更新用户
     * @param user
     * @return
     */

    @Override
    public List<Exp> getWorkExp(PersonalUser user)
    {
        return expDao.getWorkExp(user);
    }

    @Override
    public List<Exp> getWorkExp(int pid)
    {
        return expDao.getWorkExp(pid);
    }

    @Override
    public void addWorkExp(PersonalUser personalUser, Exp workExp)
    {
        expDao.addWorkExp(personalUser, workExp);
    }

    @Override
    public void addWorkExp(int pid, Exp workExp)
    {
        expDao.addWorkExp(pid, workExp);
    }

    @Override
    public void addWorkExp(int pid,String title, String content)
    {
        expDao.addWorkExp(pid, title, content);
    }

    @Override
    public void delWorkExp(int expId)
    {
        expDao.delWorkExp(expId);
    }



    /**
     * 项目经验
     */
    @Override
    public List<Exp> getProjectExp(PersonalUser user)
    {
        return expDao.getProjectExp(user);
    }
    @Override
    public List<Exp> getProjectExp(int pid)
    {
        return expDao.getProjectExp(pid);
    }
    @Override
    public void addProjectExp(PersonalUser personalUser, Exp workExp)
    {
        expDao.addProjectExp(personalUser, workExp);
    }
    @Override
    public void addProjectExp(int pid, Exp workExp)
    {
        expDao.addProjectExp(pid, workExp);
    }
    @Override
    public void addProjectExp(int pid, String title, String content)
    {
        expDao.addProjectExp(pid, title, content);
    }

    @Override
    public void delProjectExp(int expId)
    {
        expDao.delProjectExp(expId);
    }
}
