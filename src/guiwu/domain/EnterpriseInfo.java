package guiwu.domain;

import java.io.Serializable;

public class EnterpriseInfo implements Serializable
{
    private String name;
    private String email;
    private String size;
    private String location;
    private String logo;
    private String brief;

    public EnterpriseInfo(String name, String email, String size, String location, String logo, String brief)
    {
        this.name = name;
        this.email = email;
        this.size = size;
        this.location = location;
        this.logo = logo;
        this.brief = brief;
    }

    public EnterpriseInfo(EnterpriseUser enterpriseUser)
    {
        this.name = enterpriseUser.getName();
        this.email = enterpriseUser.getEmail();
        this.size = enterpriseUser.getSize();
        this.location = enterpriseUser.getLocation();
        this.logo = enterpriseUser.getLogo();
        this.brief = enterpriseUser.getBrief();
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    @Override
    public String toString()
    {
        return "EnterpriseInfo{" + "name='" + name + '\'' + ", email='" + email + '\'' + ", size='" + size + '\'' + ", location='" + location + '\'' + ", logo='" + logo + '\'' + ", brief='" + brief + '\'' + '}';
    }
}
