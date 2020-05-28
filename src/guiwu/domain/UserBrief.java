package guiwu.domain;

import java.io.Serializable;

public class UserBrief implements Serializable
{
    int id;
    String username;
    String email;
    String status;

    public UserBrief(int id, String username, String email, String status)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.status = status;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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
        return "UserBrief{" + "id=" + id + ", username='" + username + '\'' + ", email='" + email + '\'' + ", status='" + status + '\'' + '}';
    }
}