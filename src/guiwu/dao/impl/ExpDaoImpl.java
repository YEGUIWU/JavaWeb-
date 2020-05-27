package guiwu.dao.impl;

import com.sun.org.apache.regexp.internal.RE;
import guiwu.dao.ExpDao;
import guiwu.domain.PersonalUser;
import guiwu.domain.Exp;

import java.util.ArrayList;
import java.util.List;

import guiwu.util.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;

import java.sql.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ExpDaoImpl implements ExpDao
{
    final private static int kExpIdIndex = 1;
    final private static int kPidIndex = 2;

    final private static String workExpTableName = "tab_work_exp";
    final private static String projectExpTableName = "tab_project_exp";

    final private ReadWriteLock workExpLock = new ReentrantReadWriteLock();//搞个读写锁：读多写少
    final private ReadWriteLock projectExpLock = new ReentrantReadWriteLock();//搞个读写锁：读多写少

    private Exp toExp(ResultSet resultSet) throws SQLException
    {
        return new Exp(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4)
        );
    }

    private List<Exp> getExp(int pid, String tableName, ReadWriteLock lock)
    {
        List<Exp> expList = new ArrayList<Exp>();
        try
        {
            ResultSet resultSet = JDBCUtils.getAll(tableName, lock.readLock());

            while (resultSet.next())
            {
                //System.out.println(PersonalUser.kPidIndex + " " + pid + " " + resultSet.getString("2"));
                if (pid == resultSet.getInt(kPidIndex))
                {
                    expList.add(toExp(resultSet));
                }
            }
            //System.out.println(expList.size());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return expList;
    }

    private void addExp(ReadWriteLock lock, String tableName, int pid, String title, String content)
    {
        //1.定义sql
        String sql = "insert into " + tableName + " (pid,title,content) values(?,?,?)";
        System.out.println(sql);
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setInt(1, pid);
            pstmt.setString(2, title);
            pstmt.setString(3, content);
            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void delExp(ReadWriteLock lock, String tableName, int expId)
    {
        //1.定义sql
//        String sql = "delete from  " + tableName + " where exp_id = ?";
//        try
//        {
//            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
//            pstmt.setInt(1, expId);
//            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }

        JDBCUtils.delById(tableName, "exp_id", expId, lock.writeLock());
    }

    //-----------------------------------------------------------------------
    @Override
    public List<Exp> getWorkExp(PersonalUser user)
    {
        return getWorkExp(user.getPid());
    }
    @Override
    public List<Exp> getWorkExp(int pid)
    {
        return getExp(pid, workExpTableName, workExpLock);
    }
    //-----------------------------------------------------------------------
    @Override
    public List<Exp> getProjectExp(PersonalUser user)
    {
        return getProjectExp(user.getPid());
    }
    @Override
    public List<Exp> getProjectExp(int pid)
    {
        return getExp(pid, projectExpTableName, projectExpLock);
    }


    //---------------------------------------------------------------
    @Override
    public void addWorkExp(PersonalUser personalUser, Exp workExp)
    {
        addProjectExp(personalUser.getPid(), workExp);
    }
    @Override
    public void addWorkExp(int pid, Exp workExp)
    {
        addWorkExp(pid, workExp.getTitle(), workExp.getContent());
    }
    @Override
    public void addWorkExp(int pid, String title, String content)
    {
        addExp(workExpLock, workExpTableName, pid, title, content);
    }
    //---------------------------------------------------------------
    @Override
    public void addProjectExp(PersonalUser personalUser, Exp exp)
    {
        addProjectExp(personalUser.getPid(), exp);
    }
    @Override
    public void addProjectExp(int pid, Exp exp)
    {
        addProjectExp(pid, exp.getTitle(), exp.getContent());
    }
    @Override
    public void addProjectExp(int pid, String title, String content)
    {
        addExp(projectExpLock, projectExpTableName, pid, title, content);
    }

    //------------------------------------------------------------------------
    @Override
    public void delWorkExp(int expId)
    {
        delExp(workExpLock, workExpTableName, expId);
    }
    @Override
    public void delProjectExp(int expId)
    {
        delExp(projectExpLock, projectExpTableName, expId);
    }

}
