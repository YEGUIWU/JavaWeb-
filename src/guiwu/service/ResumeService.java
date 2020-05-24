package guiwu.service;

import guiwu.domain.EnterpriseInfo;
import guiwu.domain.Resume;

public interface ResumeService
{
    Resume getResume(int pid);
    EnterpriseInfo getEnterpriseInfo(int eid);
}
