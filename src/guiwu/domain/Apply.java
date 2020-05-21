package guiwu.domain;

import java.io.Serializable;

public class Apply implements Serializable
{
    private int aid;
    private int pid;
    private int rid;
    private String time;
    private String status;

    public Apply()
    {

    }

    public Apply(int aid, int pid, int rid, String time, String status)
    {
        this.aid = aid;
        this.pid = pid;
        this.rid = rid;
        this.time = time;
        this.status = status;
    }

    public int getRid()
    {
        return rid;
    }

    public void setRid(int rid)
    {
        this.rid = rid;
    }

    public int getAid()
    {
        return aid;
    }


    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
    public void setAid(int aid)
    {
        this.aid = aid;
    }

    public int getPid()
    {
        return pid;
    }

    public void setPid(int pid)
    {
        this.pid = pid;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "Apply{" + "aid=" + aid + ", pid=" + pid + ", rid=" + rid + ", time='" + time + '\'' + ", status='" + status + '\'' + '}';
    }
}
