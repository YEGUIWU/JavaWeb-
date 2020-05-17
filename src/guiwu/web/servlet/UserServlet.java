package guiwu.web.servlet;

import guiwu.domain.*;
import guiwu.service.UserService;
import guiwu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/*") // /user/add /user/find
public class UserServlet extends BaseServlet {

    //声明UserService业务对象
    private UserService service = new UserServiceImpl();
    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证校验
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次


        ResultInfo info = new ResultInfo();//响应结果

        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误

            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            System.out.println("验证码错误");
        }
        else    //验证码正确
        {
            //封装对象
            String type = request.getParameter("type");
            Object user = null;
            if (type .equals("personal"))
            {
                PersonalUser personalUser = new PersonalUser();
                personalUser.setUsername(request.getParameter("username"));
                personalUser.setPassword(request.getParameter("password"));
                personalUser.setEmail(request.getParameter("email"));
                user = personalUser;
            }
            else if (type.equals("enterprise"))
            {

            }

            try
            {
                if (type.equals("personal"))
                {
                    service.register((PersonalUser)user);
                }
                else if (type.equals("enterprise"))
                {
                    service.register((EnterpriseUser)user);
                }

                info.setFlag(true);
                System.out.println("注册成功");
            }
            catch (Exception e)
            {
                info.setFlag(false);
                info.setErrorMsg(e.toString().substring(20));
                System.out.println(e.toString().substring(20));
            }
        }
        writeValue(info, response);
    }

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //封装User对象
        String type = request.getParameter("type");
        Object user = null;
        if (type.equals("personal"))
        {
            PersonalUser personalUser = new PersonalUser();
            personalUser.setUsername(request.getParameter("username"));
            personalUser.setPassword(request.getParameter("password"));
            user = personalUser;
            System.out.println("个人用户登陆");
        }
        else if (type.equals("enterprise"))
        {
            System.out.println("企业用户登陆");
        }
        else if (type.equals("admin"))
        {
            System.out.println("管理用户登陆");
        }
        else // do nothing
        {
            System.out.println("未知类型用户登陆");
            return;
        }
        //调用Service查询
        UserService service = new UserServiceImpl();
        ResultInfo info = new ResultInfo();
        PersonalUser u = null;
        try
        {
            if (type.equals("personal"))
            {
                u  = service.login((PersonalUser)user);
            }
            else if (type.equals("enterprise"))
            {

            }
        }
        catch (Exception e)
        {
            //用户名密码或错误
            System.out.println(e.toString().substring(20));
            info.setFlag(false);
            info.setErrorMsg(e.toString().substring(20));
        }

        //判断用户是否激活
        if(u != null && !"Y".equals(u.getStatus())){
            //用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活");
            System.out.println("您尚未激活，请激活");
        }
        //判断登录成功
        if(u != null && "Y".equals(u.getStatus()))
        {
            request.getSession().setAttribute("user",u);//登录成功标记
            System.out.println("登录成功");
            //登录成功
            info.setFlag(true);
        }

        writeValue(info, response);
    }

    /**
     * 激活功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取激活码
        String code = request.getParameter("code");
        if(code != null){
            //2.调用service完成激活
            UserService service = new UserServiceImpl();
            boolean flag = service.activePersonalUser(code);

            //3.判断标记
            String msg = null;
            if(flag){
                //激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            }else{
                //激活失败
                msg = "激活失败，请联系管理员!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

    /**
     * 退出功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session
        request.getSession().invalidate();

        //2.跳转登录页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }


    /**
     * 查询单个对象
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        //将user写回客户端

       /* ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),user);*/
        writeValue(user,response);
    }


    /**
     * 更新
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updatePersonalUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //PersonalUser personalUser = new PersonalUser();
        PersonalUser personalUser = (PersonalUser)request.getSession().getAttribute("user");
        String srcPassword = personalUser.getPassword();
        personalUser.setPassword(request.getParameter("password"));
        personalUser.setName(request.getParameter("name"));
        personalUser.setBirthday(request.getParameter("birthday"));
        personalUser.setSex(request.getParameter("sex"));
        personalUser.setTelephone(request.getParameter("telephone"));
        personalUser.setEmail(request.getParameter("email"));
        personalUser.setSchool(request.getParameter("school"));
        personalUser.setEducation(request.getParameter("education"));

        ResultInfo info = new ResultInfo();
        //PersonalUser curUser = (PersonalUser)request.getSession().getAttribute("user");
        try
        {
//            personalUser.setPid(curUser.getPid());
//            personalUser.setUsername(curUser.getUsername());
            System.out.println(personalUser);
            service.update(personalUser);
//            user  = service.login(personalUser);
            info.setFlag(true);
            System.out.println("更新成功");
        }
        catch (Exception e)
        {
            //用户名密码或错误
            System.out.println(e.toString().substring(20));
            info.setFlag(false);
            info.setErrorMsg(e.toString().substring(20));
        }

        if (srcPassword.equals(personalUser.getPassword())) //没有修改密码
        {
            request.getSession().setAttribute("user",personalUser);//登录成功标记
            writeValue(info, response);
        }
        else //修改了密码
        {
            exit(request,response);
        }
    }


    /**
     * 查询工作经历
     */
    public void findWorkExp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonalUser personalUser = (PersonalUser) request.getSession().getAttribute("user");
        List<Exp> workExps = service.getWorkExp(personalUser);
        writeValue(workExps,response);
    }

    /**
     * 删除工作经历
     */
    public void delWorkExp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int expId = Integer.parseInt(request.getParameter("expId"));
        System.out.println("del: " + expId);
        service.delWorkExp(expId);
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
        service.addWorkExp(personalUser.getPid(), title, content);
//        writeValue(workExps,response);
    }


    /**
     * 查询项目经验
     */
    public void findProjectExp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonalUser personalUser = (PersonalUser) request.getSession().getAttribute("user");
        List<Exp> projectExps = service.getProjectExp(personalUser);
        writeValue(projectExps,response);
    }

    /**
     * 删除项目经验
     */
    public void delProjectExp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int expId = Integer.parseInt(request.getParameter("expId"));
        System.out.println("del: " + expId);
        service.delProjectExp(expId);
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
        service.addProjectExp(personalUser.getPid(), title, content);
//        writeValue(workExps,response);
    }

}
