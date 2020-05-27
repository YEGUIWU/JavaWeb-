package guiwu.dao.impl;

import guiwu.dao.ApplyDao;
import guiwu.dao.RecruitDao;
import guiwu.dao.UserDao;
import guiwu.domain.Apply;
import guiwu.domain.ApplyInfo;
import guiwu.domain.PersonalUser;
import guiwu.util.JDBCUtils;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ApplyDaoImpl implements ApplyDao
{
    private final static String applyTableName = "tab_apply";
    final private static int kAidIndex = 1;
    final private static int kPidIndex = 2;
    final private static int kRidIndex = 3;
    final private static int kEidIndex = 4;

    final private ReadWriteLock lock = new ReentrantReadWriteLock();//搞个读写锁：读多写少

    private Apply toApply(ResultSet resultSet) throws SQLException
    {
        return new Apply(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getString(4),
                resultSet.getString(5)
        );
    }

    private ApplyInfo toApplyInfo(ResultSet resultSet) throws SQLException
    {
        return new ApplyInfo(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getInt(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8),
                resultSet.getString(9)
        );
    }

    private List<Apply> getApply(int id, int idIndex)
    {
        List<Apply> applies = new ArrayList<Apply>();
        try
        {
            ResultSet resultSet = JDBCUtils.getAll(applyTableName, lock.readLock());
            while (resultSet.next())
            {
                if (id == resultSet.getInt(idIndex))
                {
                    applies.add(toApply(resultSet));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return applies;
    }

    private List<ApplyInfo> getApplyInfo(int id, int idIndex)
    {
        List<ApplyInfo> applyInfos = new ArrayList<ApplyInfo>();
        try
        {
            String sql = "select ta.aid, ta.pid, ta.rid, tr.eid, ta.time, ta.status, tr.title, tpu.name, teu.name from " + applyTableName + " ta " +
                    "join " + UserDaoImpl.personalUserTableName +  " tpu on ta.pid = tpu.pid " +
                    "join " + RecruitDaoImpl.recruitTableName + " tr on tr.rid = ta.rid " +
                    "join " + UserDaoImpl.enterpriseUserTableName +" teu on tr.eid = teu.eid " +
                    "order by ta.time desc";
//            System.out.println(sql);
            ResultSet resultSet = JDBCUtils.getResultSet(sql, lock.readLock());
            while (resultSet.next())
            {
                if (id == resultSet.getInt(idIndex))
                {
                    applyInfos.add(toApplyInfo(resultSet));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return applyInfos;
    }

    @Override
    public List<Apply> getPersonalApply(int pid)
    {
        return getApply(pid, kPidIndex);
    }

    @Override
    public List<Apply> getRecruitApply(int rid)
    {
        return getApply(rid, kRidIndex);
    }

    @Override
    public List<ApplyInfo> getPersonalApplyInfo(int pid)
    {
        return getApplyInfo(pid, kPidIndex);
    }

    @Override
    public List<ApplyInfo> getRecruitApplyInfo(int rid)
    {
        return getApplyInfo(rid, kRidIndex);
    }
    @Override
    public List<ApplyInfo> getApplyInfo(int eid)
    {
        return getApplyInfo(eid, kEidIndex);
    }

    @Override
    public void updateStatus(int aid, String status)
    {
//        String sql = " update " + applyTableName + " set status = ? where aid=?";
//        try
//        {
//            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
//            pstmt.setString(1, status);
//            pstmt.setInt(2, aid);
//            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
        JDBCUtils.updateOneById(applyTableName, "status", status, "aid", aid, lock.writeLock());
    }

    @Override
    public void addApply(PersonalUser personalUser, Apply apply)
    {
        addApply(personalUser.getPid(), apply);
    }
    @Override
    public void addApply(int pid, Apply apply)
    {
        addApply(pid, apply.getRid(), apply.getStatus());
    }
    @Override
    public void addApply(int pid, int rid, String status)
    {
        //1.定义sql
        String sql = "insert into " + applyTableName +
                " (pid,rid,status) values(?,?,?)";
        System.out.println(sql);
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);

            pstmt.setInt(1, pid);
            pstmt.setInt(2, rid);
            pstmt.setString(3, status);

            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void delApply(int aid)
    {
        //1.定义sql
        String sql = "delete from  " + applyTableName + " where aid = ?";
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setInt(1, aid);
            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Apply getPersonalApply(int pid, int rid)
    {
        String sql = "select * from " + applyTableName + " where pid = " + pid + " and rid = " + rid;
        //System.out.println(sql);
        try
        {
            ResultSet resultSet = JDBCUtils.getResultSet(sql, lock.readLock());
            if (resultSet.next())
            {
                //System.out.println("hello" + resultSet.getInt(1));
                return toApply(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
