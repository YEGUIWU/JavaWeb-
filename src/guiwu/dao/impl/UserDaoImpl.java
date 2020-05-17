package guiwu.dao.impl;

import guiwu.dao.UserDao;
import guiwu.domain.*;
import guiwu.util.JDBCUtils;

import java.sql.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UserDaoImpl implements UserDao
{
    final private static String personalUserTableName = "tab_personal_user";
    final private static String enterpriseUserTableName = "tab_enterprise_user";
    final private static String adminUserTableName = "tab_admin_user";
    final private ReadWriteLock personalUserLock = new ReentrantReadWriteLock();//搞个读写锁：读多写少
    final private ReadWriteLock enterpriseUserLock = new ReentrantReadWriteLock();//搞个读写锁：读多写少
    final private ReadWriteLock adminUserLock = new ReentrantReadWriteLock();//搞个读写锁：读多写少

    /**
     * @param resultSet
     * @return PersonalUser Object
     * @Breif 数据接记录转个人用户对象
     * @throws SQLException
     */
    PersonalUser toPersonalUser(ResultSet resultSet) throws SQLException
    {
        return new PersonalUser(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8),
                resultSet.getString(9),
                resultSet.getString(10),
                resultSet.getString(11),
                resultSet.getString(12),
                resultSet.getString(13),
                resultSet.getString(14));
    }

    EnterpriseUser toEnterpriseUser(ResultSet resultSet) throws SQLException
    {
        return null;
    }

    AdminUser toAdminUser(ResultSet resultSet) throws SQLException
    {
        return null;
    }

    ResultSet getResultSet(String sql) throws SQLException
    {
        Statement stmt = JDBCUtils.getDataSource().getConnection().createStatement();
        return stmt.executeQuery(sql);
    }

    ResultSet getAll(String tableName) throws SQLException
    {
        return getResultSet("select * from " + tableName);
    }

    //--------------------------------------------------------------------------------
    //                            find user by username
    //--------------------------------------------------------------------------------
    @Override
    public PersonalUser findPersonalUser(String username)
    {
        PersonalUser user = null;
        try
        {
            //-----------------------------
            //加个读锁
            personalUserLock.readLock().lock();
            ResultSet resultSet = getAll(personalUserTableName);
            personalUserLock.readLock().unlock();
            //-----------------------------
            while (resultSet.next())
            {
                if (resultSet.getString(PersonalUser.kUsernameIndex).equals(username))
                {
                    user = toPersonalUser(resultSet);
                    break;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public EnterpriseUser findEnterpriseUser(String username)
    {
        EnterpriseUser user = null;
        try
        {
            //-----------------------------
            //加个读锁
            enterpriseUserLock.readLock().lock();
            ResultSet resultSet = getAll(enterpriseUserTableName);
            enterpriseUserLock.readLock().unlock();
            //-----------------------------
            while (resultSet.next())
            {
                if (resultSet.getString(2).equals(username))
                {
                    user = toEnterpriseUser(resultSet);
                    break;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public AdminUser findAdminUser(String username)
    {
        AdminUser user = null;
        try
        {
            //-----------------------------
            //加个读锁
            adminUserLock.readLock().lock();
            ResultSet resultSet = getAll(adminUserTableName);
            adminUserLock.readLock().unlock();
            //-----------------------------
            while (resultSet.next())
            {
                if (resultSet.getString(2).equals(username))
                {
                    user = toAdminUser(resultSet);
                    break;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }

    //--------------------------------------------------------------------------------
    //                               save user
    //--------------------------------------------------------------------------------
    @Override
    public void savePersonalUser(PersonalUser user)
    {
        //1.定义sql
        String sql = "insert into " + personalUserTableName + " (username,password,email,status,code) values(?,?,?,?,?)";
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getStatus());
            pstmt.setString(5, user.getCode());
            //---------------------
            //上个写锁
            personalUserLock.writeLock().lock();
            pstmt.executeUpdate();
            personalUserLock.writeLock().unlock();
            //---------------------
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void saveEnterprise(EnterpriseUser enterprise)
    {

    }

    @Override
    public void saveAdmin(AdminUser admin)
    {

    }

    //--------------------------------------------------------------------------------
    //                               save user
    //--------------------------------------------------------------------------------
    @Override
    public PersonalUser findPersonalUserByCode(String code)
    {
        PersonalUser user = null;
        try
        {
            ResultSet resultSet = getAll(personalUserTableName);
            while(resultSet.next())
            {
                if (resultSet.getString(PersonalUser.kCodeIndex).equals(code))
                {
                    user = toPersonalUser(resultSet);
                    break;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public EnterpriseUser findEnterpriseUserByCode(String code)
    {
        return null;
    }


    @Override
    public AdminUser findAdminUserByCode(String code)
    {
        return null;
    }



    //--------------------------------------------------------------------------------
    //                               save user
    //--------------------------------------------------------------------------------
    @Override
    public void updateStatus(PersonalUser personalUser)
    {
        String sql = " update tab_user set status = 'Y' where pid=?";
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setInt(1, personalUser.getPid());
            //---------------------
            //上个写锁
            personalUserLock.writeLock().lock();
            pstmt.executeUpdate();
            personalUserLock.writeLock().unlock();
            //---------------------
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatus(EnterpriseUser enterpriseUser)
    {

    }

    @Override
    public void updateStatus(AdminUser adminUser)
    {

    }



    @Override
    public void updateInfo(PersonalUser personalUser)
    {
        String sql = " update " + personalUserTableName +
                " set password=?,name=?,birthday=?,sex=?,telephone=?,email=?,school=?,education=?" +
                " where username=?";
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setString(1, personalUser.getPassword());
            pstmt.setString(2, personalUser.getName());
            pstmt.setString(3, personalUser.getBirthday());
            pstmt.setString(4, personalUser.getSex());
            pstmt.setString(5, personalUser.getTelephone());
            pstmt.setString(6, personalUser.getEmail());
            pstmt.setString(7, personalUser.getSchool());
            pstmt.setString(8, personalUser.getEducation());
            pstmt.setString(9, personalUser.getUsername());
            //---------------------
            //上个写锁
            personalUserLock.writeLock().lock();
            pstmt.executeUpdate();
            personalUserLock.writeLock().unlock();
            //---------------------
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void updateInfo(EnterpriseUser enterpriseUser)
    {

    }
    @Override
    public void updateInfo(AdminUser adminUser)
    {

    }


    @Override
    public PersonalUser findPersonalUser(String username, String password) throws Exception
    {
        PersonalUser user = null;
        boolean hasUsername = false;
        try
        {
            //-----------------------------
            //加个读锁
            personalUserLock.readLock().lock();
            ResultSet resultSet = getAll(personalUserTableName);
            personalUserLock.readLock().unlock();
            //-----------------------------
            while(resultSet.next())
            {
                if (resultSet.getString(PersonalUser.kUsernameIndex).equals(username))
                {
                    hasUsername = true;
                    if (resultSet.getString(PersonalUser.kPasswordIndex).equals(password))
                    {
                        user = toPersonalUser(resultSet);
                    }
                    break;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        if (user == null)
        {
            if (hasUsername)
            {
                throw new Exception("密码错误");
            }
            else
            {
                throw new Exception("账号不存在");
            }
        }
        return user;
    }
    @Override
    public EnterpriseUser findEnterpriseUser(String username, String password) throws Exception
    {
        return null;
    }
    @Override
    public AdminUser findAdminUser(String username, String password) throws Exception
    {
        return null;
    }


} // class UserDaoImpl
