package guiwu.dao.impl;

import guiwu.dao.ComplainDao;
import guiwu.domain.ComplainInfo;
import guiwu.domain.ComplainMIBrief;
import guiwu.util.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ComplainDaoImpl implements ComplainDao
{
    public static final String complainTableName = "tab_complain";
    public static final int kCidIndex = 1;
    public static final int kPidIndex = 2;
    public static final int kRidIndex = 3;
    public static final int kInfoStatusIndex = 7;
    final private ReadWriteLock lock = new ReentrantReadWriteLock();//搞个读写锁：读多写少

    private ComplainInfo toComplainInfo(ResultSet resultSet)throws SQLException
    {
        return new ComplainInfo(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8),
                resultSet.getString(9),
                resultSet.getString(10)
        );
    }

    private List<ComplainInfo> getComplainInfo(String sql)
    {
        List<ComplainInfo> complainList = new ArrayList<>();
        try
        {
            ResultSet resultSet = JDBCUtils.getResultSet(sql, lock.readLock());

            while (resultSet.next())
            {
                complainList.add(toComplainInfo(resultSet));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return complainList;
    }

    @Override
    public List<ComplainInfo> getComplainInfoByPid(int pid)
    {
        String sql ="select tc.cid, tc.pid, tc.rid, tc.title, tc.content, tc.time, tc.status, tc.result, teu.name, tr.title " +
                "from " + complainTableName + " tc " +
                "join "+ RecruitDaoImpl.recruitTableName +" tr on tc.rid = tr.rid " +
                "join "+ UserDaoImpl.enterpriseUserTableName +" teu on teu.eid = tr.eid " +
                "where pid = " + pid;
        System.out.println(sql);
        return getComplainInfo(sql);
    }
    @Override
    public List<ComplainInfo> getComplainInfoByStatus(String status)
    {
        String sql ="select tc.cid, tc.pid, tc.rid, tc.title, tc.content, tc.time, tc.status, tc.result, teu.name, tr.title " +
                "from " + complainTableName + " tc" +
                "join "+ RecruitDaoImpl.recruitTableName +" tr on tc.rid = tr.rid " +
                "join "+ UserDaoImpl.enterpriseUserTableName +" teu on teu.eid = tr.eid " +
                "where status = '" + status + "'";
        return getComplainInfo(sql);
    }
    @Override
    public ComplainInfo getComplainInfo(int cid)
    {
        String sql ="select tc.cid, tc.pid, tc.rid, tc.title, tc.content, tc.time, tc.status, tc.result, teu.name, tr.title " +
                "from " + complainTableName + " tc " +
                "join "+ RecruitDaoImpl.recruitTableName +" tr on tc.rid = tr.rid " +
                "join "+ UserDaoImpl.enterpriseUserTableName +" teu on teu.eid = tr.eid " +
                "where cid = " + cid;
        try
        {
            ResultSet resultSet = JDBCUtils.getResultSet(sql, lock.readLock());
            if (resultSet.next())
            {
                return toComplainInfo(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void addComplain(int pid, int rid, String title, String content)
    {
        //1.定义sql
        String sql = "insert into " + complainTableName +
                " (pid,rid,title,content) values(?,?,?,?)";
        System.out.println(sql);
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);

            pstmt.setInt(1, pid);
            pstmt.setInt(2, rid);
            pstmt.setString(3, title);
            pstmt.setString(4, content);

            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void updateStatus(int cid, String status)
    {
//        String sql = " update " + complainTableName + " set status = ? where cid=?";
//        try
//        {
//            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
//            pstmt.setString(1, status);
//            pstmt.setInt(2, cid);
//            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
        JDBCUtils.updateOneById(complainTableName, "status", status, "cid", cid, lock.writeLock());
    }
    @Override
    public void updateResult(int cid, String result)
    {
//        String sql = " update " + complainTableName + " set result = ? where cid=?";
//        try
//        {
//            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
//            pstmt.setString(1, result);
//            pstmt.setInt(2, cid);
//            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
        JDBCUtils.updateOneById(complainTableName, "result", result, "cid", cid, lock.writeLock());
    }
    @Override
    public void delComplain(int cid)
    {
        //1.定义sql
//        String sql = "delete from  " + complainTableName + " where cid = ?";
//        try
//        {
//            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
//            pstmt.setInt(1, cid);
//            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
        JDBCUtils.delById(complainTableName, "cid", cid, lock.writeLock());
    }


    private ComplainMIBrief toComplainMIBrief(ResultSet resultSet) throws SQLException
    {
        return new ComplainMIBrief(
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

    private List<ComplainMIBrief> getComplainMIBrief(String sql)
    {
        List<ComplainMIBrief> complainList = new ArrayList<>();
        try
        {
            ResultSet resultSet = JDBCUtils.getResultSet(sql, lock.readLock());

            while (resultSet.next())
            {
                complainList.add(toComplainMIBrief(resultSet));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return complainList;
    }

    @Override
    public List<ComplainMIBrief> getComplainMIBrief(int begin, int size)
    {
        String sql = "select tc.cid, tc.pid, tc.rid, teu.eid, tc.time, tpu.username, teu.username, tr.title, tc.status\n" +
//                "from tab_complain tc\n" +
                "from " + complainTableName + " tc\n" +
//                "join tab_personal_user tpu on tc.pid = tpu.pid\n" +
                "join " + UserDaoImpl.personalUserTableName + " tpu on tc.pid = tpu.pid\n" +
//                "join tab_recruit tr on tc.rid = tr.eid \n" +
                "join " + RecruitDaoImpl.recruitTableName + " tr on tc.rid = tr.rid \n" +
//                "join tab_enterprise_user teu on tr.eid = teu.eid\n" +
                "join " + UserDaoImpl.enterpriseUserTableName + " teu on tr.eid = teu.eid\n" +
                "where tc.status = '待处理'\n" +
                "limit " + begin + "," + size;
        return getComplainMIBrief(sql);
    }

    @Override
    public void updateStatusAndResult(int cid, String status, String result)
    {
        String sql =
                " update " + complainTableName +
                " set status=?, result=?" +
                " where cid=?";
//        System.out.println(sql);
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setString(2, result);
            pstmt.setInt(3, cid);

            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
//        System.out.println("finish");
    }


    @Override
    public int getTotalCountOfStatus(String status)
    {
        try
        {
            return JDBCUtils.getCountOfAField(complainTableName, "status", status, lock.readLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

}
