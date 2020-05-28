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
    void updateInfo(PersonalUser user);
    void updateInfo(EnterpriseUser user);
    void updateInfo(AdminUser user);
    void updateStatus(PersonalUser user, String status);
    void updateStatus(EnterpriseUser user, String status);

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


    int getPersonalCount();
    int getEnterpriseCount();

    List<UserBrief> getPersonalUserBrief();
    List<UserBrief> getEnterpriseUserBrief();


    List<UserBrief> getPersonalUserBrief(int begin, int size);
    List<UserBrief> getEnterpriseUserBrief(int begin, int size);

    void updatePersonalUserStatus(int pid, String status);
    void updateEnterpriseUserStatus(int eid, String status);

    void delPersonalUserById(int pid);
    void delEnterpriseUserById(int eid);
}
