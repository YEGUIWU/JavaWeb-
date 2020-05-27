package guiwu.service.impl;

import guiwu.dao.BlacklistDao;
import guiwu.dao.impl.BlacklistDaoImpl;
import guiwu.domain.BlacklistInfo;
import guiwu.service.BlacklistService;

import java.util.List;

public class BlacklistServiceImpl implements BlacklistService
{
    private BlacklistDao blacklistDao = new BlacklistDaoImpl();

    @Override
    public List<BlacklistInfo> get(int eid)
    {
        return blacklistDao.get(eid);
    }

    @Override
    public void add(int pid, int eid)
    {
        blacklistDao.add(pid, eid);
    }

    @Override
    public void del(int bid)
    {
        blacklistDao.del(bid);
    }
}
