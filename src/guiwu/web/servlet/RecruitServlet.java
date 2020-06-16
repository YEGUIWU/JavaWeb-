package guiwu.web.servlet;
import guiwu.domain.*;
import guiwu.service.RecruitService;
import guiwu.service.impl.RecruitServiceImpl;
import guiwu.util.JedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/recruit/*") //
public class RecruitServlet extends BaseServlet
{
    private RecruitService recruitService = new RecruitServiceImpl();

    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        EnterpriseUser enterpriseUser = (EnterpriseUser) request.getSession().getAttribute("user");
        if (enterpriseUser != null)
        {
            List<Recruit> recruits = recruitService.getRecruit(enterpriseUser.getEid());
            writeValue(recruits, response);
        }
    }

    public void add(HttpServletRequest request, HttpServletResponse response)
    {
        EnterpriseUser user = (EnterpriseUser) request.getSession().getAttribute("user");

        System.out.println(user + " add recruit");
        if (user != null && user.getStatus().equals(EnterpriseUser.kWell)) //状态良好的才能添加
        {
            System.out.println(user + " add recruit");
            recruitService.addRecruit(user.getEid(),
                    request.getParameter("title"),
                    request.getParameter("position"),
                    request.getParameter("salary"),
                    request.getParameter("description"),
                    request.getParameter("requirement"),
                    request.getParameter("priority"),
                    request.getParameter("welfare")
            );
        }
    }

    public void del(HttpServletRequest request, HttpServletResponse response)
    {
        recruitService.delRecruit(Integer.parseInt(request.getParameter("rid")));
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
    {
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
        Jedis jedis = JedisUtil.getJedis();
        Integer rid = Integer.parseInt(request.getParameter("rid"));
        if (rid <= 0)
        {
            writeValue(null, response);
            return;
        }
        jedis.zincrby("the_hottest_recruit", 1, rid.toString());
        if (userType == null)
        {
            System.out.println("还没登陆");
            RecruitInfo recruitInfo = recruitService.getARecruitInfo(rid, "已发布");
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

    public void getTheHottestRecruit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Jedis jedis = JedisUtil.getJedis();
        Set<String> set = jedis.zrevrange("the_hottest_recruit", 0, 4);
        List<RecruitBrief> recruitBriefs = recruitService.getSomeRecruitBrief(set);
        writeValue(recruitBriefs , response);
    }

    public void  searchRecruitBrief(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //int rid = Integer.parseInt(request.getParameter("rid"));
        //Recruit recruit = recruitService.getARecruit(Integer.parseInt(request.getParameter("rid")));
        List<RecruitBrief> recruitBriefs = recruitService.searchBecruitBrief(request.getParameter("searchStr"));
        writeValue(recruitBriefs , response);
    }

    public void  getRecruitMIBriefByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //int rid = Integer.parseInt(request.getParameter("rid"));
        //Recruit recruit = recruitService.getARecruit(Integer.parseInt(request.getParameter("rid")));
        List<RecruitMIBrief> recruitMIBriefs = recruitService.getRecruitMIBrief(
                Integer.parseInt(request.getParameter("begin")),
                Integer.parseInt(request.getParameter("size")));
        writeValue(recruitMIBriefs, response);
    }
}
