package guiwu.domain;

import java.io.Serializable;

public class BlacklistInfo implements Serializable
{
    private int bid;
    private int pid;
    private int eid;
    private String username;
    private String name;

    public BlacklistInfo()
    {
    }

    public BlacklistInfo(int bid, int pid, int eid, String username, String name)
    {
        this.bid = bid;
        this.pid = pid;
        this.eid = eid;
        this.username = username;
        this.name = name;
    }

    public int getBid()
    {
        return bid;
    }

    public void setBid(int bid)
    {
        this.bid = bid;
    }

    public int getPid()
    {
        return pid;
    }

    public void setPid(int pid)
    {
        this.pid = pid;
    }

    public int getEid()
    {
        return eid;
    }

    public void setEid(int eid)
    {
        this.eid = eid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "BlacklistInfo{" + "bid=" + bid + ", pid=" + pid + ", eid=" + eid + ", username='" + username + '\'' + ", name='" + name + '\'' + '}';
    }
}
