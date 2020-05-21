package guiwu.domain;

import java.io.Serializable;

public class ApplyInfo implements Serializable
{
    private int aid;
    private int pid;
    private int rid;
    private int eid;
    private String time;
    private String status;
    private String title;
    private String name;

    public  ApplyInfo()
    {

    }
    public ApplyInfo(int aid, int pid, int rid, int eid, String time, String status, String title, String name)
    {
        this.aid = aid;
        this.pid = pid;
        this.rid = rid;
        this.eid = eid;
        this.time = time;
        this.status = status;
        this.title = title;
        this.name = name;
    }

    public int getAid()
    {
        return aid;
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

    public int getRid()
    {
        return rid;
    }

    public void setRid(int rid)
    {
        this.rid = rid;
    }

    public int getEid()
    {
        return eid;
    }

    public void setEid(int eid)
    {
        this.eid = eid;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
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
        return "ApplyInfo{" + "aid=" + aid + ", pid=" + pid + ", rid=" + rid + ", eid=" + eid + ", time='" + time + '\'' + ", status='" + status + '\'' + ", title='" + title + '\'' + ", name='" + name + '\'' + '}';
    }
}
