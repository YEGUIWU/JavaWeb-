package guiwu.domain;

import java.io.Serializable;

public class AdminUser implements Serializable
{

    public static final int kAidIndex = 1;
    public static final int kUsernameIndex = 2;
    public static final int kPasswordIndex = 3;
    public static final int kNameIndex = 4;

    private int aid;//用户id
    private String username;//用户名，账号
    private String password;//密码
    private String name;


    public int getAid()
    {
        return aid;
    }

    public void setAid(int aid)
    {
        this.aid = aid;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
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
        return "AdminUser{" + "aid=" + aid + ", username='" + username + '\'' + ", password='" + password + '\'' + ", name='" + name + '\'' + '}';
    }
}
