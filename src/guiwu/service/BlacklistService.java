package guiwu.service;

import guiwu.domain.BlacklistInfo;

import java.util.List;

public interface BlacklistService
{
    List<BlacklistInfo> get(int eid);
    void add(int pid, int eid);
    void del(int bid);
}
