package guiwu.web.servlet;


import guiwu.domain.EnterpriseInfo;
import guiwu.domain.EnterpriseUser;
import guiwu.domain.Recruit;
import guiwu.domain.Resume;
import guiwu.service.ResumeService;
import guiwu.service.impl.ResumeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/resume/*") //
public class ResumeSerlvet  extends BaseServlet
{
    ResumeService resumeService = new ResumeServiceImpl();
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        int pid = Integer.parseInt(request.getParameter("pid"));
        Resume resume = null;
        if (pid > 0)
        {
            resume = resumeService.getResume(pid);
        }
        writeValue(resume, response);
    }
    public void getEnterpriseInfo(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        int eid = Integer.parseInt(request.getParameter("eid"));
        EnterpriseInfo enterpriseInfo = null;
        if (eid > 0)
        {
            enterpriseInfo = resumeService.getEnterpriseInfo(eid);
        }
        writeValue(enterpriseInfo, response);
    }
}
