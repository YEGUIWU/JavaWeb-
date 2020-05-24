package guiwu.domain;

import java.io.Serializable;

public class RecruitBrief implements Serializable
{
    private int rid;
    private int eid;
    private String enterprise;
    private String logo;
    private String title;
    private String issue;
    private String position;
    private String salary;


    public RecruitBrief()
    {
    }

    public RecruitBrief(int rid,int eid, String enterprise, String logo, String title, String issue, String position, String salary)
    {
        this.rid = rid;
        this.eid = eid;
        this.enterprise = enterprise;
        this.logo = logo;
        this.title = title;
        this.issue = issue;
        this.position = position;
        this.salary = salary;
    }

    public int getEid()
    {
        return eid;
    }

    public void setEid(int eid)
    {
        this.eid = eid;
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

    public String getLogo()
    {
        return logo;
    }

    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getIssue()
    {
        return issue;
    }

    public void setIssue(String issue)
    {
        this.issue = issue;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getSalary()
    {
        return salary;
    }

    public void setSalary(String salary)
    {
        this.salary = salary;
    }

    @Override
    public String toString()
    {
        return "RecruitBrief{" + "rid=" + rid + ", eid=" + eid + ", enterprise='" + enterprise + '\'' + ", logo='" + logo + '\'' + ", title='" + title + '\'' + ", issue='" + issue + '\'' + ", position='" + position + '\'' + ", salary='" + salary + '\'' + '}';
    }
}
