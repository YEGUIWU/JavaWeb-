package guiwu.dao;

import guiwu.domain.PersonalUser;
import guiwu.domain.Exp;

import java.util.List;

public interface ExpDao
{
    List<Exp> getWorkExp(PersonalUser user);
    List<Exp> getWorkExp(int pid);
    void addWorkExp(PersonalUser personalUser, Exp workExp);
    void addWorkExp(int pid, Exp workExp);
    void addWorkExp(int pid, String title, String content);
    void delWorkExp(int expId);
    List<Exp> getProjectExp(PersonalUser user);
    List<Exp> getProjectExp(int pid);
    void addProjectExp(PersonalUser personalUser, Exp workExp);
    void addProjectExp(int pid, Exp workExp);
    void addProjectExp(int pid, String title, String content);
    void delProjectExp(int expId);
}
