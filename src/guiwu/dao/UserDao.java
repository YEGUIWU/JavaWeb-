package guiwu.dao;

import guiwu.domain.*;

import java.util.List;

public interface UserDao {

    PersonalUser findPersonalUser(String username);
    PersonalUser findPersonalUser(int pid);
    EnterpriseUser findEnterpriseUser(String username);
    EnterpriseUser findEnterpriseUser(int eid);
    AdminUser findAdminUser(String username);

    void savePersonalUser(PersonalUser user);
    void saveEnterprise(EnterpriseUser enterprise);
    void saveAdmin(AdminUser admin);

    PersonalUser findPersonalUserByCode(String code);
    EnterpriseUser findEnterpriseUserByCode(String code);

    void updateStatus(PersonalUser personalUser, String status);
    void updateStatus(EnterpriseUser enterpriseUser, String status);

    void updatePersonalUserStatus(int pid, String status);
    void updateEnterpriseUserStatus(int eid, String status);

    void updateInfo(PersonalUser personalUser);
    void updateInfo(EnterpriseUser enterpriseUser);
    void updateInfo(AdminUser adminUser);

    PersonalUser findPersonalUser(String username, String password) throws Exception;
    EnterpriseUser findEnterpriseUser(String username, String password) throws Exception;
    AdminUser findAdminUser(String username, String password) throws Exception;

    int getPersonalCount();
    int getEnterpriseCount();

    List<UserBrief> getPersonalUserBrief();
    List<UserBrief> getEnterpriseUserBrief();
    List<UserBrief> getPersonalUserBrief(int begin, int size);
    List<UserBrief> getEnterpriseUserBrief(int begin, int size);

    void delPersonalUserById(int pid);
    void delEnterpriseUserById(int eid);
}
