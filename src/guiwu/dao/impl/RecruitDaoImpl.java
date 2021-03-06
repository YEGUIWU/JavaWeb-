package guiwu.dao.impl;

import guiwu.dao.RecruitDao;
import guiwu.domain.*;

import java.util.ArrayList;
import java.util.List;

import guiwu.util.JDBCUtils;

import java.sql.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RecruitDaoImpl implements RecruitDao
{
    final public static String recruitTableName = "tab_recruit";
//    final public static String enterpriseUserTableName = "tab_enterprise_user";

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

    private RecruitInfo toRecruitInfo(ResultSet resultSet) throws  SQLException
    {
        return new RecruitInfo(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8),
                resultSet.getString(9),
                resultSet.getString(10)
        );
    }

    private RecruitBrief toRecruitBrief(ResultSet resultSet) throws  SQLException
    {
        return new RecruitBrief(
            resultSet.getInt(1),
            resultSet.getInt(2),
            resultSet.getString(3),
            resultSet.getString(4),
            resultSet.getString(5),
            resultSet.getString(6),
            resultSet.getString(7),
            resultSet.getString(8)
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
        JDBCUtils.delById(recruitTableName, "rid", rid, lock.writeLock());
    }


    @Override
    public void updateStatus(int rid, String status)
    {
        JDBCUtils.updateOneById(recruitTableName, "status", status, "rid", rid, lock.writeLock());
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

    @Override
    public Recruit getARecruit(int rid)
    {
        String sql = "select * from " + recruitTableName + " where rid = " + rid;
        //System.out.println(sql);
        try
        {
            ResultSet resultSet = JDBCUtils.getResultSet(sql, lock.readLock());
            resultSet.next();
            //System.out.println("hello" + resultSet.getInt(1));
            return toRecruit(resultSet);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private  RecruitInfo getARecruitInfo(String sql)
    {
        try
        {
            ResultSet resultSet = JDBCUtils.getResultSet(sql, lock.readLock());
            if (resultSet.next())
            {
                //System.out.println("hello" + resultSet.getInt(1));
                return toRecruitInfo(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RecruitInfo getARecruitInfo(int rid, String status)
    {
        String sql = "select tr.title, tr.position, tr.salary, tr.description, tr.requirement, tr.priority, tr.welfare, tr.issue, tr.status, teu.brief " +
                "from " + recruitTableName +" tr " +
                "join " + UserDaoImpl.enterpriseUserTableName + " teu on tr.eid = teu.eid " +
//                "where tr.rid = " + rid;
                "where tr.status = '" +  status +"' and tr.rid = " + rid;
        return getARecruitInfo(sql);
    }

    @Override
    public RecruitInfo getARecruitInfo(int rid)
    {
        String sql = "select tr.title, tr.position, tr.salary, tr.description, tr.requirement, tr.priority, tr.welfare, tr.issue, tr.status, teu.brief " +
                "from " + recruitTableName +" tr " +
                "join " + UserDaoImpl.enterpriseUserTableName + " teu on tr.eid = teu.eid " +
                "where tr.rid = " + rid;
        return getARecruitInfo(sql);
    }

    /**
     * 查询总记录数
     */
    @Override
    public int getTotalCount()
    {
        try
        {
            return JDBCUtils.getCount(recruitTableName, lock.readLock());
        }
        catch (SQLException e)
        {
            return 0;
        }
    }

    @Override
    public int getTotalCountOfStatus(String status)
    {
        try
        {
            return JDBCUtils.getCountOfAField(recruitTableName, "status", status, lock.readLock());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<RecruitBrief> getRecruitBrief(String sql)
    {
        List<RecruitBrief> recruitList = new ArrayList<>();
        try
        {
            ResultSet resultSet = JDBCUtils.getResultSet(sql, lock.readLock());

            while (resultSet.next())
            {
                recruitList.add(toRecruitBrief(resultSet));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return recruitList;
    }

    @Override
    public List<RecruitBrief> getAllRecruitBrief()
    {
        String sql = "select tr.rid, teu.eid, teu.name, teu.logo, tr.title, tr.issue, tr.position, tr.salary " +
                "from " + recruitTableName +" tr " +
                "join " + UserDaoImpl.enterpriseUserTableName + " teu on tr.eid = teu.eid " +
                "where tr.status = '已发布'";
        return getRecruitBrief(sql);
    }

    @Override
    public List<RecruitBrief> getRecruitBrief(int start, int pageSize)
    {
        String sql = "select tr.rid, teu.eid, teu.name, teu.logo, tr.title, tr.issue, tr.position, tr.salary " +
                "from " + recruitTableName +" tr, "+ UserDaoImpl.enterpriseUserTableName + " teu " +
                "where tr.eid = teu.eid and tr.status = '已发布' " +
                "limit " + start + "," + pageSize;
        return getRecruitBrief(sql);
    }

    @Override
    public List<RecruitBrief> getTheLatestRecruitBrief(int start, int pageSize)
    {
        String sql = "select tr.rid, teu.eid, teu.name, teu.logo, tr.title, tr.issue, tr.position, tr.salary " +
                "from " + recruitTableName +" tr, "+ UserDaoImpl.enterpriseUserTableName + " teu " +
                "where tr.eid = teu.eid and tr.status = '已发布' " +
//                "order by tr.rid desc, tr.issue desc "  +
                "order by tr.rid desc "  +
                "limit " + start + "," + pageSize;
        System.out.println(sql);
        return getRecruitBrief(sql);
    }

    @Override
    public List<RecruitBrief> getTheHottestRecruitBrief(int start, int pageSize)
    {
//        String sql = "select tr.rid, teu.name, tr.title, tr.position, tr.salary " +
//                "from " + recruitTableName +" tr " +
//                "join " + enterpriseUserTableName + " teu on tr.eid = teu.eid " +
//                "where tr.status = '已发布' " +
//                "limit " + start + "," + pageSize;
//        return getRecruitBrief(start, pageSize);
        return null;
    }


    @Override
    public List<RecruitBrief> searchBecruitBrief(String searchStr)
    {
        String sql = "select tr.rid, teu.eid, teu.name, teu.logo, tr.title, tr.issue, tr.position, tr.salary " +
                "from " + recruitTableName +" tr " +
                "join " + UserDaoImpl.enterpriseUserTableName + " teu on tr.eid = teu.eid " +
                "where tr.status = '已发布' " +
                "order by tr.rid desc, tr.issue desc " ;
        List<RecruitBrief> recruitList = new ArrayList<>();
        try
        {
            ResultSet resultSet = JDBCUtils.getResultSet(sql, lock.readLock());
            while (resultSet.next())
            {
                if ( (resultSet.getString(3).equals(searchStr)) || (resultSet.getString(5).indexOf(searchStr) !=-1) )
                {
                    recruitList.add(toRecruitBrief(resultSet));
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
    public List<RecruitBrief> getSomeRecruitBrief(List<Integer> rids)
    {
        if (rids.size() > 0)
        {
            String sql = "select tr.rid, teu.eid, teu.name, teu.logo, tr.title, tr.issue, tr.position, tr.salary " +
                    "from " + recruitTableName +" tr " +
                    "join " + UserDaoImpl.enterpriseUserTableName + " teu on tr.eid = teu.eid " +
                    "where tr.status = '已发布' and (tr.rid=" + rids.get(0);
            for (int ib = 1, ie = rids.size(); ib < ie; ++ib)
            {
                sql += " or tr.rid = " + rids.get(ib);
            }
            sql += ")";
            System.out.println(sql);
            return getRecruitBrief(sql);
        }
        return null;
    }

    private RecruitMIBrief toRecruitMIBrief(ResultSet resultSet) throws SQLException
    {
        return new RecruitMIBrief(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6)
        );
    }

    @Override
    public List<RecruitMIBrief> getRecruitMIBrief(String sql)
    {
        List<RecruitMIBrief> recruitList = new ArrayList<>();
        try
        {
            ResultSet resultSet = JDBCUtils.getResultSet(sql, lock.readLock());

            while (resultSet.next())
            {
                recruitList.add(toRecruitMIBrief(resultSet));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return recruitList;
    }
    @Override
    public List<RecruitMIBrief> getRecruitMIBrief(int begin,int size)
    {
        String sql = "select tr.rid, tr.eid, teu.username, teu.name, tr.issue, tr.title " +
//                "from tab_recruit tr " +
                "from " + recruitTableName + " tr " +
//                "join tab_enterprise_user teu on tr.eid = teu.eid " +
                "join " + UserDaoImpl.enterpriseUserTableName + " teu on tr.eid = teu.eid " +
                "where tr.status = \"已发布\" " +
                "limit " + begin + "," + size;
        return getRecruitMIBrief(sql);
    }
}
