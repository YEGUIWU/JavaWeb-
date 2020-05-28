package guiwu.domain;

import java.io.Serializable;

public class ComplainMIBrief  implements Serializable
{
    int cid;
    int pid;
    int rid;
    int eid;
    String time;
    String personalUsername;
    String enterpriseUsername;
    String title;
    String status;

    public ComplainMIBrief(int cid, int pid, int rid, int eid, String time, String personalUsername, String enterpriseUsername, String title, String status)
    {
        this.cid = cid;
        this.pid = pid;
        this.rid = rid;
        this.eid = eid;
        this.time = time;
        this.personalUsername = personalUsername;
        this.enterpriseUsername = enterpriseUsername;
        this.title = title;
        this.status = status;
    }

    public int getCid()
    {
        return cid;
    }

    public void setCid(int cid)
    {
        this.cid = cid;
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

    public String getPersonalUsername()
    {
        return personalUsername;
    }

    public void setPersonalUsername(String personalUsername)
    {
        this.personalUsername = personalUsername;
    }

    public String getEnterpriseUsername()
    {
        return enterpriseUsername;
    }

    public void setEnterpriseUsername(String enterpriseUsername)
    {
        this.enterpriseUsername = enterpriseUsername;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
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
        return "ComplainMIBrief{" + "cid=" + cid + ", pid=" + pid + ", rid=" + rid + ", eid=" + eid + ", time='" + time + '\'' + ", personalUsername='" + personalUsername + '\'' + ", enterpriseUsername='" + enterpriseUsername + '\'' + ", title='" + title + '\'' + ", status='" + status + '\'' + '}';
    }
}
