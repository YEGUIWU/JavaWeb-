package guiwu.domain;

import java.io.Serializable;
//   cid                  int not null auto_increment,
//           rid 					int not null,
//           title 				varchar(32),
//           content				varchar(256),
//           time             	datetime default now(),
//        status				varchar(16) default "待处理",
//        result				varchar(64),
public class ComplainInfo implements Serializable
{
    int cid;
    int pid;
    int rid;
    String title;
    String content;
    String time;
    String status;
    String result;
    String enterprise;
    String recruit;

    public ComplainInfo()
    {
    }

    public ComplainInfo(int cid, int pid, int rid, String title, String content, String time, String status, String result, String enterprise, String recruit)
    {
        this.cid = cid;
        this.pid = pid;
        this.rid = rid;
        this.title = title;
        this.content = content;
        this.time = time;
        this.status = status;
        this.result = result;
        this.enterprise = enterprise;
        this.recruit = recruit;
    }

    public int getPid()
    {
        return pid;
    }

    public void setPid(int pid)
    {
        this.pid = pid;
    }

    public String getRecruit()
    {
        return recruit;
    }

    public void setRecruit(String recruit)
    {
        this.recruit = recruit;
    }

    public int getCid()
    {
        return cid;
    }

    public void setCid(int cid)
    {
        this.cid = cid;
    }

    public int getRid()
    {
        return rid;
    }

    public void setRid(int rid)
    {
        this.rid = rid;
    }

    public String getEnterprise()
    {
        return enterprise;
    }

    public void setEnterprise(String enterprise)
    {
        this.enterprise = enterprise;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
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

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    @Override
    public String toString()
    {
        return "ComplainInfo{" + "cid=" + cid + ", pid=" + pid + ", rid=" + rid + ", title='" + title + '\'' + ", content='" + content + '\'' + ", time='" + time + '\'' + ", status='" + status + '\'' + ", result='" + result + '\'' + ", enterprise='" + enterprise + '\'' + ", recruit='" + recruit + '\'' + '}';
    }
}
