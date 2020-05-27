package guiwu.dao.impl;

import com.sun.deploy.util.BlackList;
import guiwu.dao.BlacklistDao;
import guiwu.domain.Blacklist;
import guiwu.domain.BlacklistInfo;
import guiwu.domain.ComplainInfo;
import guiwu.domain.ResultInfo;
import guiwu.util.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BlacklistDaoImpl implements BlacklistDao
{
    public static final String blackListTableName = "tab_blacklist";

    private BlacklistInfo toBlacklistInfo(ResultSet resultSet) throws SQLException
    {
        return new BlacklistInfo(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getString(4),
                resultSet.getString(5)
        );
    }

    private Blacklist toBlacklist(ResultSet resultSet) throws SQLException
    {
        return new Blacklist(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3)
        );
    }

    final private ReadWriteLock lock = new ReentrantReadWriteLock();//搞个读写锁：读多写少

    @Override
    public List<BlacklistInfo> get(int eid)
    {
        String sql = "select tb.bid, tb.pid, tb.eid, tpu.username, tpu.name " +
                "from "+blackListTableName+" tb " +
                "join "+ UserDaoImpl.personalUserTableName +" tpu on tb.pid = tpu.pid " +
                "where eid = " + eid;
        List<BlacklistInfo> blacklist = new ArrayList<>();
        try
        {
            ResultSet resultSet = JDBCUtils.getResultSet(sql, lock.readLock());

            while (resultSet.next())
            {
                blacklist.add(toBlacklistInfo(resultSet));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return blacklist;
    }

    //insert into tab_blacklist(pid, eid) value(1,1);
    @Override
    public void add(int pid, int eid)
    {
        //1.定义sql
        String sql = "insert into " + blackListTableName +
                " (pid,eid) values(?,?)";
        System.out.println(sql);
        try
        {
            PreparedStatement pstmt = JDBCUtils.getDataSource().getConnection().prepareStatement(sql);
            pstmt.setInt(1, pid);
            pstmt.setInt(2, eid);
            JDBCUtils.executeUpdate(pstmt, lock.writeLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void del(int bid)
    {
        JDBCUtils.delById(blackListTableName, "bid", bid, lock.writeLock());
    }


    @Override
    public Blacklist find(int eid, int pid)
    {
        String sql = "select * from " + blackListTableName + " where eid = " + eid + " and pid = " + pid;
        try
        {
            ResultSet resultSet = JDBCUtils.getResultSet(sql, lock.readLock());
            if (resultSet.next())
            {
                return toBlacklist(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
