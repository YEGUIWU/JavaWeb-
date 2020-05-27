package guiwu.dao;

import guiwu.domain.Blacklist;
import guiwu.domain.BlacklistInfo;

import java.util.List;

public interface BlacklistDao
{
    List<BlacklistInfo> get(int eid);
    void add(int pid, int eid);
    void del(int bid);
    Blacklist find(int eid, int pid);
}
