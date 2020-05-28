package guiwu.web.servlet;

import guiwu.domain.ComplainInfo;
import guiwu.domain.ComplainMIBrief;
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


    public void getComplainMIBriefByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<ComplainMIBrief> complainMIBriefs = complainService.getComplainMIBrief(
                Integer.parseInt(request.getParameter("begin")),
                Integer.parseInt(request.getParameter("size")));
        writeValue(complainMIBriefs, response);
    }

    public void  getTotalCountOfStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String status = request.getParameter("status");
        int size = complainService.getTotalCountOfStatus(status);
        writeValue(size , response);
    }


    public void ignore(HttpServletRequest request, HttpServletResponse response)
    {
        int cid = Integer.parseInt(request.getParameter("cid"));
        if (cid > 0)
        {
            complainService.updateStatus(cid, "已忽略");
        }
    }
    public void handle(HttpServletRequest request, HttpServletResponse response)
    {
        String result = request.getParameter("result");
        int cid = Integer.parseInt(request.getParameter("cid"));
        if (cid > 0)
        {
            complainService.updateStatusAndResult(cid, "已处理", result);
            //System.out.println(result);
        }
    }

    public void findComplainInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int cid  = Integer.parseInt(request.getParameter("cid"));
        System.out.println("Cid = " + cid);
        ComplainInfo complainInfo = complainService.getComplainInfo(cid);
        writeValue(complainInfo, response);
    }


}
