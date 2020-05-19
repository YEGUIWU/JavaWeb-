package guiwu.web.servlet;

import guiwu.domain.*;
import guiwu.service.ExpService;
import guiwu.service.impl.ExpServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/exp/*") //
public class ExpServlet extends BaseServlet {


    private ExpService expService = new ExpServiceImpl();

    /**
     * 查询工作经历
     */
    public void findWorkExp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonalUser personalUser = (PersonalUser) request.getSession().getAttribute("user");
        List<Exp> workExps = expService.getWorkExp(personalUser);
        writeValue(workExps,response);
    }

    /**
     * 删除工作经历
     */
    public void delWorkExp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int expId = Integer.parseInt(request.getParameter("expId"));
        System.out.println("del: " + expId);
        expService.delWorkExp(expId);
        //        writeValue(workExps,response);
    }
    /**
     * 添加工作经历
     */
    public void addWorkExp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonalUser personalUser = (PersonalUser) request.getSession().getAttribute("user");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        System.out.println("add " + "title: " + title + " content: " + content);
        expService.addWorkExp(personalUser.getPid(), title, content);
        //        writeValue(workExps,response);
    }


    /**
     * 查询项目经验
     */
    public void findProjectExp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonalUser personalUser = (PersonalUser) request.getSession().getAttribute("user");
        List<Exp> projectExps = expService.getProjectExp(personalUser);
        writeValue(projectExps,response);
    }

    /**
     * 删除项目经验
     */
    public void delProjectExp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int expId = Integer.parseInt(request.getParameter("expId"));
        System.out.println("del: " + expId);
        expService.delProjectExp(expId);
        //        writeValue(workExps,response);
    }

    /**
     * 添加项目经验
     */
    public void addProjectExp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonalUser personalUser = (PersonalUser) request.getSession().getAttribute("user");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        System.out.println("add " + "title: " + title + " content: " + content);
        expService.addProjectExp(personalUser.getPid(), title, content);
        //        writeValue(workExps,response);
    }
}
