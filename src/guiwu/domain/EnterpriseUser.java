package guiwu.domain;

import java.io.Serializable;

public class EnterpriseUser implements Serializable
{
    public static final int kEidIndex = 1;
    public static final int kUsernameIndex = 2;
    public static final int kPasswordIndex = 3;
    public static final int kNameIndex = 4;
    private int eid;
    private String username;
    private String password;
    private String name;
    private String email;
    private String size;
    private String location;
    private String logo;
    private String brief;
    private String status;

    public EnterpriseUser()
    {

    }

    public EnterpriseUser(String username, String email, String password)
    {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public EnterpriseUser(int eid, String username, String password, String name, String email, String size, String location, String logo, String brief, String status)
    {
        this.eid = eid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.size = size;
        this.location = location;
        this.logo = logo;
        this.brief = brief;
        this.status = status;
    }

    public int getEid()
    {
        return eid;
    }

    public void setEid(int eid)
    {
        this.eid = eid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }



    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLogo()
    {
        return logo;
    }

    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public String getBrief()
    {
        return brief;
    }

    public void setBrief(String brief)
    {
        this.brief = brief;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public static int getkUsernameIndex()
    {
        return kUsernameIndex;
    }

    public static int getkPasswordIndex()
    {
        return kPasswordIndex;
    }

    @Override
    public String toString()
    {
        return "EnterpriseUser{" + "eid=" + eid + ", username='" + username + '\'' + ", name='" + name + '\'' + ", password='" + password + '\'' + ", email='" + email + '\'' + ", size='" + size + '\'' + ", location='" + location + '\'' + ", logo='" + logo + '\'' + ", brief='" + brief + '\'' + ", status='" + status + '\'' + '}';
    }
}
