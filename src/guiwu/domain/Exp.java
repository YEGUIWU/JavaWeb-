package guiwu.domain;
import java.io.Serializable;

public class Exp implements Serializable
{
    private int expId;
    private int pid;
    private String title;
    private String content;

    public Exp()
    {

    }

    public Exp(int expId, int pid, String title, String content)
    {
        this.expId = expId;
        this.pid = pid;
        this.title = title;
        this.content = content;
    }

    public int getExpId()
    {
        return expId;
    }

    public void setExpId(int expId)
    {
        this.expId = expId;
    }

    public int getPid()
    {
        return pid;
    }

    public void setPid(int pid)
    {
        this.pid = pid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Override
    public String toString()
    {
        return "Exp{" + "expId=" + expId + ", pid=" + pid + ", title='" + title + '\'' + ", content='" + content + '\'' + '}';
    }
}
