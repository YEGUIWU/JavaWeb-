package guiwu.service;

import guiwu.domain.*;

import java.util.List;

public interface ExpService {
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
