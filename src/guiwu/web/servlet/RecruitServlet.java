package guiwu.web.servlet;
import guiwu.domain.*;
import guiwu.service.RecruitService;
import guiwu.service.impl.RecruitServiceImpl;

import javax.servlet.ServletException;
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

    public void  findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userType = (String) request.getSession().getAttribute("userType");
        if (userType == null)
        {
            System.out.println("还没登陆");
            RecruitInfo recruitInfo = recruitService.getARecruitInfo(Integer.parseInt(request.getParameter("rid")), "已发布");
            writeValue(recruitInfo, response);
        }
        else
        {
            if (userType.equals("enterprise"))
            {
                RecruitInfo recruitInfo = recruitService.getARecruitInfo(Integer.parseInt(request.getParameter("rid")));
                writeValue(recruitInfo, response);
            }
            else
            {
                RecruitInfo recruitInfo = recruitService.getARecruitInfo(Integer.parseInt(request.getParameter("rid")), "已发布");
                writeValue(recruitInfo, response);
            }

        }
    }

    public void  getTotalCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //int rid = Integer.parseInt(request.getParameter("rid"));
        //Recruit recruit = recruitService.getARecruit(Integer.parseInt(request.getParameter("rid")));
        int size = recruitService.getTotalCount();
        writeValue(size , response);
    }
    public void  getTotalCountOfReleased(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //int rid = Integer.parseInt(request.getParameter("rid"));
        //Recruit recruit = recruitService.getARecruit(Integer.parseInt(request.getParameter("rid")));
        int size = recruitService.getTotatlCountOfStatus("已发布");
        writeValue(size , response);
    }
    public void  getLatestByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //int rid = Integer.parseInt(request.getParameter("rid"));
        //Recruit recruit = recruitService.getARecruit(Integer.parseInt(request.getParameter("rid")));
        List<RecruitBrief> recruitBriefs = recruitService.getTheLatestRecruitBrief(
                Integer.parseInt(request.getParameter("begin")),
                Integer.parseInt(request.getParameter("size")));
        writeValue(recruitBriefs , response);
    }




}
