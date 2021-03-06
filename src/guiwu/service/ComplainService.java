package guiwu.service;

import guiwu.domain.ComplainInfo;
import guiwu.domain.ComplainMIBrief;

import java.util.List;

public interface ComplainService
{

    int getTotalCountOfStatus(String status);
    List<ComplainInfo> getComplainInfoByPid(int pid);
    List<ComplainInfo> getComplainInfoByStatus(String status);
    ComplainInfo getComplainInfo(int cid);
    void addComplain(int pid, int rid, String title, String content);
    void updateStatus(int cid, String status);
    void handleComplain(int cid, String result);
    void delComplain(int cid);


    List<ComplainMIBrief> getComplainMIBrief(int begin, int size);
    void updateStatusAndResult(int cid, String status, String result);
}
