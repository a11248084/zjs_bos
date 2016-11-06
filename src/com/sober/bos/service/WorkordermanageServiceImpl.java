package com.sober.bos.service;

import com.sober.bos.dao.IWorkordermanageDao;
import com.sober.bos.domain.Workordermanage;
import com.sober.bos.service.base.IWorkordermanageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Renhai on 2016/11/5.
 */
@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {

    @Resource
    private IWorkordermanageDao workordermanageDao;

    @Override
    public void save(Workordermanage model) {
        workordermanageDao.save(model);
    }

    @Override
    public List<Workordermanage> findAll() {
        return workordermanageDao.findAll();
    }

    @Override
    public void saveBatch(List<Workordermanage> list) {
        for (Workordermanage workordermanage:list){
            workordermanageDao.saveOrUpdate(workordermanage);
        }
    }
}
