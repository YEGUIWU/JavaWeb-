package guiwu.domain;

import java.io.Serializable;

public class AdminUser implements Serializable
{
    private int uid;//用户id
    private String username;//用户名，账号
    private String password;//密码

    public int getUid()
    {
        return uid;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
