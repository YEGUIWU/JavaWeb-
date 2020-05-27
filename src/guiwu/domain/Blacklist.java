package guiwu.domain;

import java.io.Serializable;

public class Blacklist implements Serializable
{
    int bid;
    int pid;
    int eid;

    public Blacklist(int bid, int pid, int eid)
    {
        this.bid = bid;
        this.pid = pid;
        this.eid = eid;
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

    @Override
    public String toString()
    {
        return "Blacklist{" + "bid=" + bid + ", pid=" + pid + ", eid=" + eid + '}';
    }
}
