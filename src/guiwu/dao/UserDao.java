package guiwu.dao;

import guiwu.domain.*;

public interface UserDao {

    PersonalUser findPersonalUser(String username);
    EnterpriseUser findEnterpriseUser(String username);
    AdminUser findAdminUser(String username);

    void savePersonalUser(PersonalUser user);
    void saveEnterprise(EnterpriseUser enterprise);
    void saveAdmin(AdminUser admin);

    PersonalUser findPersonalUserByCode(String code);

    void updateStatus(PersonalUser personalUser);
    void updateStatus(EnterpriseUser enterpriseUser);

    void updateInfo(PersonalUser personalUser);
    void updateInfo(EnterpriseUser enterpriseUser);
    void updateInfo(AdminUser adminUser);


    PersonalUser findPersonalUser(String username, String password) throws Exception;
    EnterpriseUser findEnterpriseUser(String username, String password) throws Exception;
    AdminUser findAdminUser(String username, String password) throws Exception;
}
