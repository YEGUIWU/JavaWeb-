package guiwu.domain;

import java.util.List;

public class Resume
{
    private String username;        //用户名
    private String name;            //真实姓名
    private String birthday;        //出生日期
    private String sex;             //男或女
    private String telephone;       //手机号
    private String email;           //邮箱
    private String brief;           //描述
    private String icon;            //头像名
    private String school;          //毕业学校
    private String education;       //学历
    private List<Exp> workExpList;     //工作经验
    private List<Exp> projectExpList;  //项目经验

    public Resume()
    {

    }

    public Resume(String username, String name, String birthday,
                  String sex, String telephone, String email,
                  String brief, String icon, String school,
                  String education, List<Exp> workExps, List<Exp> projectExps)
    {
        this.username = username;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.telephone = telephone;
        this.email = email;
        this.brief = brief;
        this.icon = icon;
        this.school = school;
        this.education = education;
        this.workExpList = workExps;
        this.projectExpList = projectExps;
    }

    public String getUsername()
    {
        return username;
    }

    public void setPersonalInfo(PersonalUser personalUser)
    {
        this.username = personalUser.getUsername();
        this.name = personalUser.getName();
        this.birthday = personalUser.getBirthday();
        this.sex = personalUser.getSex();
        this.telephone = personalUser.getTelephone();
        this.email = personalUser.getEmail();
        this.brief = personalUser.getBrief();
        this.icon = personalUser.getIcon();
        this.school = personalUser.getSchool();
        this.education = personalUser.getEducation();
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

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getBrief()
    {
        return brief;
    }

    public void setBrief(String brief)
    {
        this.brief = brief;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getSchool()
    {
        return school;
    }

    public void setSchool(String school)
    {
        this.school = school;
    }

    public String getEducation()
    {
        return education;
    }

    public void setEducation(String education)
    {
        this.education = education;
    }

    public List<Exp> getWorkExpList()
    {
        return workExpList;
    }

    public void setWorkExpList(List<Exp> workExpList)
    {
        this.workExpList = workExpList;
    }

    public List<Exp> getProjectExpList()
    {
        return projectExpList;
    }

    public void setProjectExpList(List<Exp> projectExpList)
    {
        this.projectExpList = projectExpList;
    }

    @Override
    public String toString()
    {
        return "Resume{" + "username='" + username + '\'' + ", name='" + name + '\'' + ", birthday='" + birthday + '\'' + ", sex='" + sex + '\'' + ", telephone='" + telephone + '\'' + ", email='" + email + '\'' + ", brief='" + brief + '\'' + ", icon='" + icon + '\'' + ", school='" + school + '\'' + ", education='" + education + '\'' + ", workExpList=" + workExpList + ", projectExpList=" + projectExpList + '}';
    }
}
