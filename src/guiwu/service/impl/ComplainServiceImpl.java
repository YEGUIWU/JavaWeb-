package guiwu.service.impl;

import guiwu.dao.ComplainDao;
import guiwu.dao.impl.ComplainDaoImpl;
import guiwu.domain.ComplainInfo;
import guiwu.domain.ComplainMIBrief;
import guiwu.service.ComplainService;

import java.util.List;

/**
 *   投诉机制
 */
public class ComplainServiceImpl implements ComplainService
{
    private ComplainDao complainDao = new ComplainDaoImpl();
    @Override
    public List<ComplainInfo> getComplainInfoByPid(int pid)
    {
        return complainDao.getComplainInfoByPid(pid);
    }
    @Override
    public List<ComplainInfo> getComplainInfoByStatus(String status)
    {
        return complainDao.getComplainInfoByStatus(status);
    }
    @Override
    public ComplainInfo getComplainInfo(int cid)
    {
        return complainDao.getComplainInfo(cid);
    }
    @Override
    public void addComplain(int pid, int rid, String title, String content)
    {
        complainDao.addComplain(pid, rid, title, content);
    }
    @Override
    public void updateStatus(int cid, String status)
    {
        complainDao.updateStatus(cid, status);
    }
    @Override
    public void handleComplain(int cid, String result)
    {
        complainDao.updateResult(cid, result);
    }
    @Override
    public void delComplain(int cid)
    {
        complainDao.delComplain(cid);
    }


    @Override
    public List<ComplainMIBrief> getComplainMIBrief(int begin, int size)
    {
        return complainDao.getComplainMIBrief(begin, size);
    }
    @Override
    public void updateStatusAndResult(int cid, String status, String result)
    {
        complainDao.updateStatusAndResult(cid, status, result);
    }

    @Override
    public int getTotalCountOfStatus(String status)
    {
        return complainDao.getTotalCountOfStatus(status);
    }
}
