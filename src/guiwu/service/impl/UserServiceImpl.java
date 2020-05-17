package guiwu.service.impl;

import guiwu.dao.UserDao;
import guiwu.dao.ExpDao;
import guiwu.dao.impl.UserDaoImpl;
import guiwu.dao.impl.ExpDaoImpl;
import guiwu.domain.*;
import guiwu.service.UserService;
import guiwu.util.MailUtils;
import guiwu.util.UuidUtil;

import java.util.List;


public class UserServiceImpl implements UserService
{

    private UserDao userDao = new UserDaoImpl();
    private ExpDao expDao = new ExpDaoImpl();

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @Override
    public void register(PersonalUser user) throws Exception
    {
        //根据用户名查询用户对象
        PersonalUser u = userDao.findPersonalUser(user.getUsername());
        if(u != null){
            throw new Exception("用户名已存在");
        }
        //保存用户信息
        user.setCode(UuidUtil.getUuid()); //2.1设置激活码，唯一字符串
        user.setStatus("N");//2.2设置激活状态
        userDao.savePersonalUser(user);

        //激活邮件发送，邮件正文
        String content="<a href='http://localhost:8080/activeUserServlet?code="+user.getCode()+"'>点击激活【YeGuiWu-Blog】</a>";
        System.out.println("发送邮件：" + user.getEmail());
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        System.out.println("发送成功：" + user.getEmail());
    }

    @Override
    public void register(EnterpriseUser user) throws Exception
    {

    }

    @Override
    public void register(AdminUser user) throws Exception
    {

    }

    /**
     * 注册用户
     *
     * @param code
     * @return successful is or not
     */
    @Override
    public boolean activePersonalUser(String code)
    {
        //1.根据激活码查询用户对象
        PersonalUser user = userDao.findPersonalUserByCode(code);
        if(user != null)
        {
            //2.调用dao的修改激活状态的方法
            userDao.updateStatus(user);
            return true;
        }
        else
        {
            return false;
        }
    }
    @Override
    public boolean activeEnterpriseUser(String code)
    {
        //1.根据激活码查询用户对象
        EnterpriseUser user = userDao.findEnterpriseUserByCode(code);
        if(user != null)
        {
            //2.调用dao的修改激活状态的方法
            userDao.updateStatus(user);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 注册用户
     *
     * @param user
     * @return user
     */
    @Override
    public PersonalUser login(PersonalUser user) throws Exception
    {
        return userDao.findPersonalUser(user.getUsername(),user.getPassword());
    }
    @Override
    public  EnterpriseUser login(EnterpriseUser user) throws Exception
    {
        //return userDao.findEnterpriseUser(user.getUsername(),user.getPassword());
        return null;
    }
    @Override
    public AdminUser login(AdminUser user) throws Exception
    {
        //return userDao.findPersonalUser(user.getUsername(),user.getPassword());
        return null;
    }


    /**
     * 更新用户
     * @param user
     * @return
     */

    @Override
    public void update(PersonalUser user)
    {
        userDao.updateInfo(user);
    }
    @Override
    public void update(EnterpriseUser user)
    {
        userDao.updateInfo(user);
    }
    @Override
    public void update(AdminUser user)
    {
        userDao.updateInfo(user);
    }

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
