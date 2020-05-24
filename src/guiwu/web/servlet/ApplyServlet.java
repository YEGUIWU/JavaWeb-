package guiwu.web.servlet;

import guiwu.domain.Apply;
import guiwu.domain.ApplyInfo;
import guiwu.domain.EnterpriseUser;
import guiwu.domain.PersonalUser;
import guiwu.service.ApplyService;
import guiwu.service.impl.ApplyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/apply/*")
public class ApplyServlet extends BaseServlet
{
    ApplyService applyService = new ApplyServiceImpl();

    public void getApplyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EnterpriseUser enterpriseUser = (EnterpriseUser) request.getSession().getAttribute("user");
        List<ApplyInfo> applyInfos = applyService.getApplyInfo(enterpriseUser.getEid());
        for (ApplyInfo applyInfo : applyInfos)
        {
            System.out.println(applyInfo);
        }
        writeValue(applyInfos, response);
    }
    public void updateApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EnterpriseUser enterpriseUser = (EnterpriseUser) request.getSession().getAttribute("user");
        String newStatus = request.getParameter("status");
        int aid = Integer.parseInt(request.getParameter("aid"));
        applyService.updateStatus(aid, newStatus);
    }

    public void getApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userType = (String) request.getSession().getAttribute("userType");
        if (userType != null && userType.equals("personal"))
        {
            PersonalUser personalUser = (PersonalUser) request.getSession().getAttribute("user");
            if (personalUser != null)
            {
                int rid = Integer.parseInt(request.getParameter("rid"));
                Apply apply = applyService.getPersonalApply(personalUser.getPid(), rid);
                writeValue(apply, response);
            }
        }
    }

    public void getAllApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userType = (String) request.getSession().getAttribute("userType");
        List<ApplyInfo> applyInfos = null;
        if (userType != null)
        {
            if (userType.equals("personal"))
            {
                PersonalUser personalUser = (PersonalUser) request.getSession().getAttribute("user");
                if (personalUser != null)
                {
                    applyInfos = applyService.getPersonalApplyInfo(personalUser.getPid());
                }
            }
            else if (userType.equals("enterprise"))
            {
                EnterpriseUser enterpriseUser = (EnterpriseUser) request.getSession().getAttribute("user");
                if (enterpriseUser != null)
                {
                    applyInfos = applyService.getApplyInfo(enterpriseUser.getEid());
                }
            }
        }
        writeValue(applyInfos, response);
    }

    public void addPersonalApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PersonalUser personalUser = (PersonalUser) request.getSession().getAttribute("user");
        int rid = Integer.parseInt(request.getParameter("rid"));
        applyService.addApply(personalUser.getPid(), rid, "待接受");
    }
}
