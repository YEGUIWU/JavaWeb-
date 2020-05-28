package guiwu.domain;

import java.io.Serializable;

public class RecruitMIBrief  implements Serializable
{
    private int rid;
    private int eid;
    private String username;
    private String name;
    private String time;
    private String title;

    public RecruitMIBrief()
    {
    }

    public RecruitMIBrief(int rid, int eid, String username, String name, String time, String title)
    {
        this.rid = rid;
        this.eid = eid;
        this.username = username;
        this.name = name;
        this.time = time;
        this.title = title;
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

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "RecruitMIBrief{" + "rid=" + rid + ", eid=" + eid + ", username='" + username + '\'' + ", name='" + name + '\'' + ", time='" + time + '\'' + ", title='" + title + '\'' + '}';
    }
}
