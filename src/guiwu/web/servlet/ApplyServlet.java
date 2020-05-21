package guiwu.web.servlet;

import guiwu.domain.ApplyInfo;
import guiwu.domain.EnterpriseUser;
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
}
