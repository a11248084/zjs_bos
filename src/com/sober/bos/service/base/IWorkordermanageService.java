package com.sober.bos.service.base;

import com.sober.bos.domain.Workordermanage;

import java.util.List;

/**
 * Created by Renhai on 2016/11/5.
 */

public interface IWorkordermanageService {
    public void save(Workordermanage model);

    public  List<Workordermanage> findAll();

    public  void saveBatch(List<Workordermanage> list);
}
