package guiwu.domain;

import java.io.Serializable;

public class RecruitInfo implements Serializable
{
    private String title;
    private String position;
    private String salary;
    private String description;
    private String requirement;
    private String priority;
    private String welfare;
    private String issue;
    private String status;
    private String brief;

    public RecruitInfo()
    {

    }

    public RecruitInfo(String title, String position, String salary, String description, String requirement, String priority, String welfare, String issue, String status, String brief)
    {
        this.title = title;
        this.position = position;
        this.salary = salary;
        this.description = description;
        this.requirement = requirement;
        this.priority = priority;
        this.welfare = welfare;
        this.issue = issue;
        this.status = status;
        this.brief = brief;
    }

    public String getBrief()
    {
        return brief;
    }

    public void setBrief(String brief)
    {
        this.brief = brief;
    }

    public String getIssue()
    {
        return issue;
    }

    public void setIssue(String issue)
    {
        this.issue = issue;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getRequirement()
    {
        return requirement;
    }

    public void setRequirement(String requirement)
    {
        this.requirement = requirement;
    }

    public String getPriority()
    {
        return priority;
    }

    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    public String getWelfare()
    {
        return welfare;
    }

    public void setWelfare(String welfare)
    {
        this.welfare = welfare;
    }

    @Override
    public String toString()
    {
        return "RecruitInfo{" + "title='" + title + '\'' + ", position='" + position + '\'' + ", salary='" + salary + '\'' + ", description='" + description + '\'' + ", requirement='" + requirement + '\'' + ", priority='" + priority + '\'' + ", welfare='" + welfare + '\'' + ", issue='" + issue + '\'' + ", status='" + status + '\'' + ", brief='" + brief + '\'' + '}';
    }
}
