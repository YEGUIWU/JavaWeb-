package guiwu.web.servlet;

import guiwu.domain.*;
import guiwu.service.BlacklistService;
import guiwu.service.impl.BlacklistServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/blacklist/*") //
public class BlacklistServlet extends BaseServlet
{
    BlacklistService blacklistService = new BlacklistServiceImpl();
    public void getBlacklist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EnterpriseUser enterpriseUser = (EnterpriseUser)request.getSession().getAttribute("user");
        ResultInfo resultInfo = new ResultInfo();
        if (enterpriseUser != null && enterpriseUser.getStatus().equals("Y"))
        {
            resultInfo.setFlag(true);
            List<BlacklistInfo> blacklistInfos = blacklistService.get(enterpriseUser.getEid());
            resultInfo.setData(blacklistInfos);
        }
        else
        {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("权限不足");
        }
        writeValue(resultInfo, response);
    }

    public void addBlacklist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EnterpriseUser enterpriseUser = (EnterpriseUser)request.getSession().getAttribute("user");
        ResultInfo resultInfo = new ResultInfo();
        if (enterpriseUser != null && enterpriseUser.getStatus().equals("Y"))
        {
            int pid = Integer.parseInt(request.getParameter("pid"));
            blacklistService.add(pid, enterpriseUser.getEid());
        }
        else
        {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("权限不足");
        }
        writeValue(resultInfo, response);
    }

    public void delBlacklist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EnterpriseUser enterpriseUser = (EnterpriseUser)request.getSession().getAttribute("user");
        ResultInfo resultInfo = new ResultInfo();
        if (enterpriseUser != null && enterpriseUser.getStatus().equals("Y"))
        {
            int bid = Integer.parseInt(request.getParameter("bid"));
            blacklistService.del(bid);
        }
        else
        {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("权限不足");
        }
        writeValue(resultInfo, response);
    }
}
