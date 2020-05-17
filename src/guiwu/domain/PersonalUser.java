package guiwu.domain;

import java.io.Serializable;

/**
 * 用户实体类
 */
public class PersonalUser implements Serializable
{
    public static final int kPidIndex = 1;
    public static final int kUsernameIndex = 2;
    public static final int kPasswordIndex = 3;
    public static final int kCodeIndex = 14;
    private int pid;            //个人用户id（要求唯一）
    private String username;    //用户名，账号（要求唯一）
    private String password;    //密码
    private String name;        //真实姓名
    private String birthday;    //出生日期
    private String sex;         //男或女
    private String telephone;   //手机号
    private String email;       //邮箱
    private String brief;       //描述
    private String icon;        //头像名
    private String school;      //毕业学校
    private String education;   //学历
    private String status;      //激活状态，Y代表激活，N代表未激活
    private String code;        //激活码（要求唯一）

    /**
     * 无参构造方法
     */
    public PersonalUser() {
    }

    /**
     * 有参构方法
     * @param pid
     * @param username
     * @param password
     * @param email
     * @param status
     * @param code
     */
    public PersonalUser(int pid, String username, String password,String email, String status, String code) {
        this.pid = pid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.code = code;
    }

    public PersonalUser(int pid,
                        String username, String password, String name, String birthday,
                        String sex, String telephone, String email, String brief, String icon,
                        String school, String education, String status, String code)
    {
        this.pid = pid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.telephone = telephone;
        this.email = email;
        this.brief = brief;
        this.icon = icon;
        this.school = school;
        this.education = education;
        this.status = status;
        this.code = code;
    }

    public int getPid()
    {
        return pid;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public String getSex()
    {
        return sex;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public String getEmail()
    {
        return email;
    }

    public String getBrief()
    {
        return brief;
    }

    public String getIcon()
    {
        return icon;
    }

    public String getSchool()
    {
        return school;
    }

    public String getEducation()
    {
        return education;
    }

    public String getStatus()
    {
        return status;
    }

    public String getCode()
    {
        return code;
    }

    public void setPid(int puid)
    {
        this.pid = pid;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setBrief(String brief)
    {
        this.brief = brief;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public void setSchool(String school)
    {
        this.school = school;
    }

    public void setEducation(String education)
    {
        this.education = education;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return "PersonalUser{" + "pid=" + pid + ", username='" + username + '\'' + ", password='" +
                password + '\'' + ", name='" + name + '\'' + ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' + ", telephone='" + telephone + '\'' + ", email='" + email +
                '\'' + ", brief='" + brief + '\'' + ", icon='" + icon + '\'' + ", school='" + school +
                '\'' + ", education='" + education + '\'' + ", status='" + status + '\'' + ", code='" + code + '\'' + '}';
    }
}
