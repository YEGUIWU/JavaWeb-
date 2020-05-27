package guiwu.dao.impl;

import guiwu.dao.UserDao;
import guiwu.domain.*;
import guiwu.util.JDBCUtils;

import java.sql.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UserDaoImpl implements UserDao
{
    final public static String personalUserTableName = "tab_personal_user";
    final public static String enterpriseUserTableName = "tab_enterprise_user";
    final public static String adminUserTableName = "tab_admin_user";
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
        return new EnterpriseUser(
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
                resultSet.getString(10)
        );
    }

    AdminUser toAdminUser(ResultSet resultSet) throws SQLException
    {
        return null;
    }



    private ResultSet findUser(String username, String password, ReadWriteLock lock, String tableName, int userIndex, int passwordIndex) throws Exception
    {
        boolean hasUsername = false;
        ResultSet user = null;
        String sql = "select * from " + tableName + " where username = '" + username + "'"; //and password = '" + password +"'";
        //System.out.println(sql);
        //System.out.println(sql);
        try
        {
            //ResultSet resultSet = JDBCUtils.getAll(tableName, lock.readLock());
            ResultSet resultSet = JDBCUtils.getResultSet(sql, lock.readLock());
            while(resultSet.next())
            {
                System.out.println(resultSet.getString(userIndex) + ": " + resultSet.getString(passwordIndex));
                if (resultSet.getString(userIndex).equals(username))
                {
                    hasUsername = true;
                    if (resultSet.getString(passwordIndex).equals(password))
                    {
                        user = resultSet;
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

    //--------------------------------------------------------------------------------
    //                            find user by username
    //--------------------------------------------------------------------------------

    @Override
    public PersonalUser findPersonalUser(int pid)
    {
        PersonalUser personalUser = null;
        String sql = "select * from " + personalUserTableName + " where pid = " + pid;
        try
        {
            ResultSet resultSet = JDBCUtils.getResultSet(sql, personalUserLock.readLock());
            while (resultSet.next())
            {
                if (pid == resultSet.getInt(PersonalUser.kPidIndex))
                {
                    personalUser = toPersonalUser(resultSet);
                    break;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return personalUser;
    }
    @Override
    public PersonalUser findPersonalUser(String username)
    {
        PersonalUser user = null;
        String sql = "select * from " + personalUserTableName + " where username = '" + username + "'";
        try
        {
            //ResultSet resultSet = JDBCUtils.getAll(personalUserTableName, personalUserLock.readLock());
            ResultSet resultSet = JDBCUtils.getResultSet(sql, personalUserLock.readLock());
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
        String sql = "select * from " + enterpriseUserTableName + " where username = '" + username +"'";
        try
        {
            //enterpriseUserLock.readLock().lock();
            //ResultSet resultSet = JDBCUtils.getAll(enterpriseUserTableName, enterpriseUserLock.readLock());
            ResultSet resultSet = JDBCUtils.getResultSet(sql, enterpriseUserLock.readLock());
            while (resultSet.next())
            {
                if (resultSet.getString(EnterpriseUser.kUsernameIndex).equals(username))
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
    public EnterpriseUser findEnterpriseUser(int eid)
    {
        EnterpriseUser enterpriseUser = null;
        String sql = "select * from " + enterpriseUserTableName + " where eid = " + eid;
        try
        {
            ResultSet resultSet = JDBCUtils.getResultSet(sql, enterpriseUserLock.readLock());
            while (resultSet.next())
            {
                if (eid == resultSet.getInt(PersonalUser.kPidIndex))
                {
                    enterpriseUser = toEnterpriseUser(resultSet);
                    break;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return enterpriseUser;
    }



    @Override
    public AdminUser findAdminUser(String username)
    {
        AdminUser user = null;
        String sql = "select * from " + adminUserTableName + " where username = '" + username + "'";
        try
        {
            //ResultSet resultSet = JDBCUtils.getAll(adminUserTableName, adminUserLock.readLock());
            ResultSet resultSet = JDBCUtils.getResultSet(sql, adminUserLock.readLock());
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

            JDBCUtils.executeUpdate(pstmt, personalUserLock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void saveEnterprise(EnterpriseUser user)
    {
        String sql = "insert into " + enterpriseUserTableName + " (username,password,email,status,code) values(?,?,?,?,?)";
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getStatus());
            pstmt.setString(5, user.getCode());
            JDBCUtils.executeUpdate(pstmt, enterpriseUserLock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAdmin(AdminUser user)
    {
        String sql = "insert into " + adminUserTableName + " (username,password,name) values(?,?,?)";
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            JDBCUtils.executeUpdate(pstmt, adminUserLock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //--------------------------------------------------------------------------------
    //                               find user by code
    //--------------------------------------------------------------------------------
    @Override
    public PersonalUser findPersonalUserByCode(String code)
    {
        PersonalUser user = null;
        try
        {
            ResultSet resultSet = JDBCUtils.getAll(personalUserTableName, personalUserLock.readLock());
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
        EnterpriseUser user = null;
        try
        {
            ResultSet resultSet = JDBCUtils.getAll(enterpriseUserTableName, enterpriseUserLock.readLock());
            while(resultSet.next())
            {
                if (resultSet.getString(EnterpriseUser.kCodeIndex).equals(code))
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




    //--------------------------------------------------------------------------------
    //                               update status
    //--------------------------------------------------------------------------------
    @Override
    public void updateStatus(PersonalUser personalUser)
    {
        String sql = " update " + personalUserTableName + " set status = 'Y' where pid=?";
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setInt(1, personalUser.getPid());

            JDBCUtils.executeUpdate(pstmt, personalUserLock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatus(EnterpriseUser enterpriseUser)
    {
        String sql = " update " + enterpriseUserTableName + " set status = 'Y' where eid=?";
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setInt(1, enterpriseUser.getEid());

            JDBCUtils.executeUpdate(pstmt, enterpriseUserLock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }



    //--------------------------------------------------------------------------------
    //                               update info
    //--------------------------------------------------------------------------------
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

            JDBCUtils.executeUpdate(pstmt, personalUserLock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void updateInfo(EnterpriseUser enterpriseUser)
    {
        String sql = " update " + enterpriseUserTableName +
                " set password=?,name=?,email=?,size=?,location=?,logo=?,brief=?" +
                " where username=?";
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setString(1, enterpriseUser.getPassword());
            pstmt.setString(2, enterpriseUser.getName());
            pstmt.setString(3, enterpriseUser.getEmail());
            pstmt.setString(4, enterpriseUser.getSize());
            pstmt.setString(5, enterpriseUser.getLocation());
            pstmt.setString(6, enterpriseUser.getLogo());
            pstmt.setString(7, enterpriseUser.getBrief());
            pstmt.setString(8, enterpriseUser.getUsername());
            //pstmt.setString(6, enterpriseUser.getBrief());

            JDBCUtils.executeUpdate(pstmt, personalUserLock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void updateInfo(AdminUser adminUser)
    {

    }

    //--------------------------------------------------------------------------------
    //                               find user
    //--------------------------------------------------------------------------------
    @Override
    public PersonalUser findPersonalUser(String username, String password) throws Exception
    {
        return toPersonalUser(findUser(username, password,
                personalUserLock, personalUserTableName,
                PersonalUser.kUsernameIndex, PersonalUser.kPasswordIndex));
    }
    @Override
    public EnterpriseUser findEnterpriseUser(String username, String password) throws Exception
    {
//        System.out.println(username + " " + password);
//        ResultSet resultSet = findUser(username, password,
//                enterpriseUserLock, enterpriseUserTableName,
//                EnterpriseUser.kUsernameIndex, EnterpriseUser.kPasswordIndex);
//        if (resultSet == null)
//        {
//            System.out.println("找不到");
//        }
//        else
//        {
//            System.out.println("找到了");
//        }
//        System.out.println(resultSet.getInt(1) + resultSet.getString(2));
//        return toEnterpriseUser(resultSet);
        return toEnterpriseUser(findUser(username, password,
                enterpriseUserLock, enterpriseUserTableName,
                EnterpriseUser.kUsernameIndex, EnterpriseUser.kPasswordIndex));
    }
    @Override
    public AdminUser findAdminUser(String username, String password) throws Exception
    {
        return toAdminUser(findUser(username, password,
                adminUserLock, adminUserTableName,
                AdminUser.kUsernameIndex, AdminUser.kPasswordIndex));
    }


} // class UserDaoImpl
