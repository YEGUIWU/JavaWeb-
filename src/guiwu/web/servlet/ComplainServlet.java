package guiwu.web.servlet;

import guiwu.domain.ComplainInfo;
import guiwu.domain.PersonalUser;
import guiwu.domain.ResultInfo;
import guiwu.service.ComplainService;
import guiwu.service.impl.ComplainServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/complain/*")
public class ComplainServlet extends BaseServlet
{
    ComplainService complainService = new ComplainServiceImpl();

    public void getComplainInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PersonalUser personalUser =  (PersonalUser)request.getSession().getAttribute("user");
        List<ComplainInfo> complainInfos = complainService.getComplainInfoByPid(personalUser.getPid());
        writeValue(complainInfos, response);
    }

    public void complainRecruit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PersonalUser personalUser =  (PersonalUser)request.getSession().getAttribute("user");
        ResultInfo resultInfo = new ResultInfo();

        if (personalUser != null && personalUser.getStatus().equals("Y"))
        {
            resultInfo.setFlag(true);
            int rid = Integer.parseInt(request.getParameter("rid"));
            String title = (request.getParameter("title"));
            String content = (request.getParameter("content"));
            System.out.println(title + content);
            complainService.addComplain(personalUser.getPid(), rid, title, content);
        }
        else
        {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("请先登陆");
        }
        writeValue(resultInfo, response);
    }

}
