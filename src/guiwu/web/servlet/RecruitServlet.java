package guiwu.web.servlet;
import guiwu.domain.*;
import guiwu.service.RecruitService;
import guiwu.service.impl.RecruitServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

@WebServlet("/recruit/*") //
public class RecruitServlet extends BaseServlet
{
    private RecruitService recruitService = new RecruitServiceImpl();

    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        EnterpriseUser enterpriseUser = (EnterpriseUser) request.getSession().getAttribute("user");
        List<Recruit> recruits = recruitService.getRecruit(enterpriseUser.getEid());
        writeValue(recruits, response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response)
    {
        EnterpriseUser personalUser = (EnterpriseUser) request.getSession().getAttribute("user");
        recruitService.addRecruit(personalUser.getEid(),
                request.getParameter("title"),
                request.getParameter("position"),
                request.getParameter("salary"),
                request.getParameter("description"),
                request.getParameter("requirement"),
                request.getParameter("priority"),
                request.getParameter("welfare")
                );
    }

    public void del(HttpServletRequest request, HttpServletResponse response)
    {
        recruitService.delRecruit(Integer.parseInt(request.getParameter("rid")));
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
    {
        EnterpriseUser personalUser = (EnterpriseUser) request.getSession().getAttribute("user");
        String status =  request.getParameter("status");
        int rid = Integer.parseInt(request.getParameter("rid"));
        if (status.equals("已发布"))
        {
            recruitService.issueRecruit(rid);
        }
        recruitService.updateStatus(rid, status);
    }
}
