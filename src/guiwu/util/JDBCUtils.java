package guiwu.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/*
	1. 声明静态数据源成员变量
	2. 创建连接池对象
	3. 定义公有的得到数据源的方法
	4. 定义得到连接对象的方法
	5. 定义关闭资源的方法
 */
public class JDBCUtils {
	// 1.	声明静态数据源成员变量
	private static DataSource ds;

	// 2. 创建连接池对象
	static {
		// 加载配置文件中的数据
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
		Properties pp = new Properties();

		try
        {
			pp.load(is);
			// 创建连接池，使用配置文件中的参数
			ds = DruidDataSourceFactory.createDataSource(pp);
		}
		catch (IOException e)
        {
			e.printStackTrace();
		}
		catch (Exception e)
        {
			e.printStackTrace();
		}
	}


	@Test
	public void teset()throws SQLException
	{
        try
        {
            // 加载配置文件中的数据
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties pp = new Properties();
            pp.load(is);
            System.out.println(pp.getProperty("username"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}

	// 3. 定义公有的得到数据源的方法
	public static DataSource getDataSource()
	{
		return ds;
	}

	// 4. 定义得到连接对象的方法
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	// 5.定义关闭资源的方法
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}

	// 6.重载关闭方法
	public static void close(Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}


	//--------------------------------------------------------------------------------
//    public static ResultSet getResultSet(String sql) throws SQLException
//    {
//        return ds.getConnection().createStatement().executeQuery(sql);
//    }
//    public static ResultSet getAll(String tableName) throws SQLException
//    {
//        return getResultSet("select * from " + tableName);
//    }
    public static ResultSet getResultSet(String sql,  Lock lock) throws SQLException
    {
        lock.lock();
//        Connection connection = ds.getConnection();
//        Statement statement = connection.createStatement();
        ResultSet resultSet = ds.getConnection().createStatement().executeQuery(sql);
        lock.unlock();
        return  resultSet;
    }
    public static int getCount(String tableName, Lock lock) throws SQLException
	{
		String sql = "select count(*) from " + tableName;
		lock.lock();
		ResultSet resultSet = ds.getConnection().createStatement().executeQuery(sql);
		lock.unlock();
		if (resultSet.next())
		{
			return resultSet.getInt(1);
		}
		return 0;
	}
    public static ResultSet getAll(String tableName,  Lock lock) throws SQLException
    {
        return getResultSet("select * from " + tableName, lock);
    }
    public static void executeUpdate(PreparedStatement pstmt) throws SQLException
    {
        pstmt.executeUpdate();
    }
    public static void executeUpdate(PreparedStatement pstmt, Lock lock) throws SQLException
    {
        //---------------------
        //上个锁
        lock.lock();
        pstmt.executeUpdate();
        lock.unlock();
        //---------------------
    }
}
