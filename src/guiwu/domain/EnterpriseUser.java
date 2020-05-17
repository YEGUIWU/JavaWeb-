package guiwu.domain;

import java.io.Serializable;

public class EnterpriseUser implements Serializable
{
    private int companyId;
    private String companyName;
    private String password;
    private String email;
    private String logo;
    private String brief;
    private int size;
    private String location;

    public EnterpriseUser()
    {

    }

    public EnterpriseUser(int companyId, String companyName, String password, String email, String logo, String brief, int size, String location)
    {
        this.companyId = companyId;
        this.companyName = companyName;
        this.password = password;
        this.email = email;
        this.logo = logo;
        this.brief = brief;
        this.size = size;
        this.location = location;
    }

    public void setCompanyId(int companyId)
    {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public void setBrief(String brief)
    {
        this.brief = brief;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public int getCompanyId()
    {
        return companyId;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public String getPassword()
    {
        return password;
    }

    public String getEmail()
    {
        return email;
    }

    public String getLogo()
    {
        return logo;
    }

    public String getBrief()
    {
        return brief;
    }

    public int getSize()
    {
        return size;
    }

    public String getLocation()
    {
        return location;
    }


    @Override
    public String toString()
    {
        return "Company{" + "companyId=" + companyId + ", companyName='" + companyName + '\''
                + ", password='" + password + '\'' + ", email='" + email + '\''
                + ", logo='" + logo + '\'' + ", brief='" + brief + '\''
                + ", size=" + size + ", location='" + location + '\'' + '}';
    }
}
