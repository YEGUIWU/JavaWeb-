package guiwu.service;

import guiwu.domain.*;

import java.util.List;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    void register(PersonalUser user)throws Exception;
    void register(EnterpriseUser user)throws Exception;
    void register(AdminUser user)throws Exception;
    /**
     * 更新用户
     * @param user
     * @return
     */

    void update(PersonalUser user);
    void update(EnterpriseUser user);
    void update(AdminUser user);

    /**
     * 激活用户
     * @param code
     * @return successful is or not
     */
    boolean activePersonalUser(String code);
    boolean activeEnterpriseUser(String code);
    /**
     * 登陆用户
     * @param user
     * @return user
     */
    PersonalUser login(PersonalUser user) throws Exception;
    EnterpriseUser login(EnterpriseUser user) throws Exception;
    AdminUser login(AdminUser user) throws Exception;

    /**
     * 工作经历
     */
    List<Exp> getWorkExp(PersonalUser user);
    List<Exp> getWorkExp(int pid);
    void addWorkExp(PersonalUser personalUser, Exp workExp);
    void addWorkExp(int pid, Exp workExp);
    void addWorkExp(int pid, String title, String content);
    void delWorkExp(int expId);
    /**
     * 项目经验
     */
    List<Exp> getProjectExp(PersonalUser user);
    List<Exp> getProjectExp(int pid);
    void addProjectExp(PersonalUser personalUser, Exp workExp);
    void addProjectExp(int pid, Exp workExp);
    void addProjectExp(int pid, String title, String content);
    void delProjectExp(int expId);
}
