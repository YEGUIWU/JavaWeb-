package guiwu.dao.impl;

import guiwu.dao.RecruitDao;
import guiwu.domain.EnterpriseUser;
import guiwu.domain.Recruit;

import java.util.ArrayList;
import java.util.List;

import guiwu.util.JDBCUtils;

import java.sql.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RecruitDaoImpl implements RecruitDao
{
    final public static String recruitTableName = "tab_recruit";
    final public static int kEidIndex = 2;

    final private ReadWriteLock lock = new ReentrantReadWriteLock();//搞个读写锁：读多写少

    private Recruit toRecruit(ResultSet resultSet) throws SQLException
    {
        return new Recruit(
            resultSet.getInt(1),
            resultSet.getInt(2),
            resultSet.getString(3),
            resultSet.getString(4),
            resultSet.getString(5),
            resultSet.getString(6),
            resultSet.getString(7),
            resultSet.getString(8),
            resultSet.getString(9),
            resultSet.getString(10),
            resultSet.getString(11)
        );
    }

    @Override
    public List<Recruit> getRecruit(EnterpriseUser enterpriseUser)
    {
        return getRecruit(enterpriseUser.getEid());
    }

    @Override
    public List<Recruit> getRecruit(int eid)
    {
        List<Recruit> recruitList = new ArrayList<Recruit>();
        try
        {
            ResultSet resultSet = JDBCUtils.getAll(recruitTableName, lock.readLock());

            while (resultSet.next())
            {
                //System.out.println(PersonalUser.kPidIndex + " " + pid + " " + resultSet.getString("2"));
                if (eid == resultSet.getInt(kEidIndex))
                {
                    recruitList.add(toRecruit(resultSet));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return recruitList;
    }

    @Override
    public void addRecruit(EnterpriseUser enterpriseUser, Recruit recruit)
    {
        addRecruit(enterpriseUser.getEid(), recruit);
    }

    @Override
    public void addRecruit(int eid, Recruit recruit)
    {
        addRecruit(
                eid, recruit.getTitle(), recruit.getPosition(), recruit.getSalary(),
                recruit.getDescription(), recruit.getRequirement(),
                recruit.getPriority(), recruit.getWelfare()
        );
    }

    @Override
    public void addRecruit(int eid, String title, String position, String salary,
                    String description, String requirement,
                    String priority, String welfare)
    {
        //1.定义sql
        String sql = "insert into " + recruitTableName +
                " (eid,title,position,salary,description,requirement,priority,welfare) values(?,?,?,?,?,?,?,?)";
        System.out.println(sql);
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);

            pstmt.setInt(1, eid);
            pstmt.setString(2, title);
            pstmt.setString(3, position);
            pstmt.setString(4, salary);
            pstmt.setString(5, description);
            pstmt.setString(6, requirement);
            pstmt.setString(7, priority);
            pstmt.setString(8, welfare);

            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delRecruit(int rid)
    {
        //1.定义sql
        String sql = "delete from  " + recruitTableName + " where rid = ?";
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setInt(1, rid);
            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void updateStatus(int rid, String status)
    {
        String sql = " update " + recruitTableName + " set status = ? where rid=?";
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, rid);
            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void issueRecruit(int rid)
    {
        String sql = "update " + recruitTableName + " set issue = date(now()) where rid = ?";
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setInt(1, rid);
            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
