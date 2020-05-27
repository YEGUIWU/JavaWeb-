package guiwu.dao;

import guiwu.domain.ComplainInfo;

import java.util.List;

public interface ComplainDao
{
    List<ComplainInfo> getComplainInfoByPid(int pid);
    List<ComplainInfo> getComplainInfoByStatus(String status);
    ComplainInfo getComplainInfo(int cid);
    void addComplain(int pid, int rid, String title, String content);
    void updateStatus(int cid, String status);
    void updateResult(int cid, String result);
    void delComplain(int cid);
}
