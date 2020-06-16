package guiwu.web.servlet;

import guiwu.domain.*;
import guiwu.service.ExpService;
import guiwu.service.UserService;
import guiwu.service.impl.ExpServiceImpl;
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
    private UserService userService = new UserServiceImpl();
    private ExpService expService = new ExpServiceImpl();
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
            session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
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
                EnterpriseUser enterpriseUser = new EnterpriseUser();
                enterpriseUser.setUsername(request.getParameter("username"));
                enterpriseUser.setPassword(request.getParameter("password"));
                enterpriseUser.setEmail(request.getParameter("email"));
                user = enterpriseUser;
            }

            try
            {
                if (type.equals("personal"))
                {
                    userService.register((PersonalUser)user);
                }
                else if (type.equals("enterprise"))
                {
                    userService.register((EnterpriseUser)user);
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
            System.out.println("个人用户");
        }
        else if (type.equals("enterprise"))
        {
            EnterpriseUser enterpriseUser = new EnterpriseUser();
            enterpriseUser.setUsername(request.getParameter("username"));
            enterpriseUser.setPassword(request.getParameter("password"));
            user = enterpriseUser;
            System.out.println("企业用户");
        }
        else if (type.equals("admin"))
        {
            AdminUser adminUser = new AdminUser();
            adminUser.setUsername(request.getParameter("username"));
            adminUser.setPassword(request.getParameter("password"));
            user = adminUser;
            System.out.println("管理用户");
        }
        else // do nothing
        {
            System.out.println("未知类型用户登陆");
            return;
        }
        //调用Service查询
        UserService service = new UserServiceImpl();
        ResultInfo info = new ResultInfo();
        //Object u = null;
        try
        {
            if (type.equals("personal"))
            {
                PersonalUser u  = service.login((PersonalUser)user);
                System.out.println(u);
                if(u != null && "N".equals(u.getStatus()))
                {
                    //用户尚未激活
                    info.setFlag(false);
                    info.setErrorMsg("您尚未激活，请激活");
                    System.out.println("您尚未激活，请激活");
                }
                if(u != null && !"N".equals(u.getStatus()))
                {
                    request.getSession().setAttribute("user",u);//登录成功标记
                    request.getSession().setAttribute("userType", "personal");//登录成功标记
                    System.out.println("登录成功");
                    //登录成功
                    info.setFlag(true);
                    info.setData("userhome.html");
                }
            }
            else if (type.equals("enterprise"))
            {
                System.out.println("企业用户登陆");
                EnterpriseUser u  = service.login((EnterpriseUser) user);
                System.out.println(u);
                if(u != null && "N".equals(u.getStatus()))
                {
                    //用户尚未激活
                    info.setFlag(false);
                    info.setErrorMsg("您尚未激活，请激活");
                    System.out.println("您尚未激活，请激活");
                }
                if(u != null && !"N".equals(u.getStatus()))
                {
                    request.getSession().setAttribute("user",u);//登录成功标记
                    request.getSession().setAttribute("userType", "enterprise");//登录成功标记
                    System.out.println("登录成功");
                    //登录成功
                    info.setFlag(true);
                    info.setData("companyhome.html");
                }
            }
            else if (type.equals("admin"))
            {
                System.out.println("管理员用户登陆");
                AdminUser adminUser = service.login((AdminUser)user);
                System.out.println(adminUser);
                if (adminUser != null)
                {
                    request.getSession().setAttribute("user",adminUser);//登录成功标记
                    request.getSession().setAttribute("userType", "admin");//登录成功标记
                    System.out.println("登录成功");
                    //登录成功
                    info.setFlag(true);
                    info.setData("adminhome.html");
                }
                else
                {
                    info.setFlag(false);
                    info.setErrorMsg("登陆失败");
                }
            }
            else
            {
                info.setFlag(false);
                info.setErrorMsg("错误登陆类型");
            }
        }
        catch (Exception e)
        {
            //用户名密码或错误
            System.out.println(e.toString().substring(20));
            info.setFlag(false);
            info.setErrorMsg(e.toString().substring(20));
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
        String type = request.getParameter("type");
        if(code != null){
            //2.调用service完成激活
            UserService service = new UserServiceImpl();
            boolean flag = false;
            if (type.equals("personal"))
            {
                System.out.println("激活普通用户");
                flag = service.activePersonalUser(code);
            }
            else if (type.equals("enterprise"))
            {
                System.out.println("激活企业用户");
                flag = service.activeEnterpriseUser(code);
            }
            //3.判断标记
            String msg = null;
            if(flag){
                //激活成功
                msg = "激活成功!!!";
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
        String type = (String)request.getSession().getAttribute("userType");
        //1.销毁session
        request.getSession().invalidate();
        //2.跳转登录页面
        if (type.equals("admin"))
        {
            response.sendRedirect(request.getContextPath()+"/admin.html");
        }
        else
        {
            response.sendRedirect(request.getContextPath()+"/login.html");
        }
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
            userService.updateInfo(personalUser);
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
     * 更新企业用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateEnterpriseUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EnterpriseUser user = (EnterpriseUser) request.getSession().getAttribute("user");
        if (user != null)
        {
            String srcPassword = user.getPassword();
            user.setPassword(request.getParameter("password"));
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setSize(request.getParameter("size"));
            user.setLocation(request.getParameter("location"));
            user.setLogo(request.getParameter("logo"));
            user.setBrief(request.getParameter("brief"));
            System.out.println(user.getLogo());
            ResultInfo info = new ResultInfo();
            try
            {
                System.out.println(user);
                userService.updateInfo(user);
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

            if (srcPassword.equals(user.getPassword())) //没有修改密码
            {
                request.getSession().setAttribute("user",user);//登录成功标记
                writeValue(info, response);
            }
            else //修改了密码
            {
                exit(request,response);
            }
        }

    }
    /**
     * 更新管理员用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateAdminUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdminUser user = (AdminUser) request.getSession().getAttribute("user");
        if (user != null)
        {
            String srcPassword = user.getPassword();
            user.setPassword(request.getParameter("password"));
            user.setName(request.getParameter("name"));
            ResultInfo info = new ResultInfo();
            try
            {
                System.out.println(user);
                userService.updateInfo(user);
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

            if (srcPassword.equals(user.getPassword())) //没有修改密码
            {
                request.getSession().setAttribute("user",user);//登录成功标记
                writeValue(info, response);
            }
            else //修改了密码
            {
                exit(request,response);
            }
        }
    }

    public void toHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String type =(String)request.getSession().getAttribute("userType");
        if (type != null)
        {
            if (type.equals("personal"))
            {
                response.sendRedirect(contextPath+"/userhome.html");
            }
            else
            {
                response.sendRedirect(contextPath+"/companyhome.html");
            }
        }
    }


    public void getUserCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String type = request.getParameter("type");
        if (type.equals("personal"))
        {
            writeValue(userService.getPersonalCount(), response);
        }
        else if (type.equals("enterprise"))
        {
            writeValue(userService.getEnterpriseCount(), response);
        }
    }

    public void getUserBrief(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String type = request.getParameter("type");
        if (type.equals("personal"))
        {
            writeValue(userService.getPersonalUserBrief(), response);
        }
        else if (type.equals("enterprise"))
        {
            writeValue(userService.getEnterpriseUserBrief(), response);
        }
    }

    public void getUserBriefByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String type = request.getParameter("type");
        int begin = Integer.parseInt(request.getParameter("begin"));
        int size = Integer.parseInt(request.getParameter("size"));
        if (type.equals("personal"))
        {
            writeValue(userService.getPersonalUserBrief(begin, size), response);
        }
        else if (type.equals("enterprise"))
        {
            writeValue(userService.getEnterpriseUserBrief(begin, size), response);
        }
    }

    public void updateUserStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String type = request.getParameter("type");
        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");
        ResultInfo resultInfo = new ResultInfo();
        if (type.equals("personal"))
        {
            resultInfo.setFlag(true);
            userService.updatePersonalUserStatus(id, status);
        }
        else if (type.equals("enterprise"))
        {
            resultInfo.setFlag(true);
            userService.updateEnterpriseUserStatus(id, status);
        }
        else
        {

            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("修改失败");
        }
    }
    public void delUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String type = request.getParameter("type");
        int id = Integer.parseInt(request.getParameter("id"));
        ResultInfo resultInfo = new ResultInfo();
        if (type.equals("personal"))
        {
            resultInfo.setFlag(true);
            userService.delPersonalUserById(id);
        }
        else if (type.equals("enterprise"))
        {
            resultInfo.setFlag(true);
            userService.delEnterpriseUserById(id);
        }
        else
        {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("删除失败");
        }
    }

}
