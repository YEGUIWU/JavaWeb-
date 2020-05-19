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

}
