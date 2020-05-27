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
        String content="<a href='http://localhost:8080/user/active?code="+user.getCode()+"&type=personal'>点击激活【YeGuiWu-Blog】</a>";
        System.out.println("发送邮件：" + user.getEmail());
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        System.out.println("发送成功：" + user.getEmail());
    }

    @Override
    public void register(EnterpriseUser user) throws Exception
    {
        //根据用户名查询用户对象
        EnterpriseUser u = userDao.findEnterpriseUser(user.getUsername());
        if(u != null){
            throw new Exception("用户名已存在");
        }
        //保存用户信息
        user.setCode(UuidUtil.getUuid()); //2.1设置激活码，唯一字符串
        user.setStatus("N");//2.2设置激活状态
        userDao.saveEnterprise(user);

        //激活邮件发送，邮件正文
        String content="<a href='http://localhost:8080/user/active?code="+user.getCode()+"&type=enterprise'>点我激活【YeGuiWu-招聘网】</a>";
        System.out.println("发送邮件：" + user.getEmail());
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        System.out.println("发送成功：" + user.getEmail());
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
    {        //1.根据激活码查询用户对象
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
        return userDao.findEnterpriseUser(user.getUsername(),user.getPassword());
    }
    @Override
    public AdminUser login(AdminUser user) throws Exception
    {
        return userDao.findAdminUser(user.getUsername(),user.getPassword());
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


}
