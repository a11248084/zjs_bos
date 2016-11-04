package com.sober.bos.service;

import com.sober.bos.dao.IDecidedzoneDao;
import com.sober.bos.dao.ISubareaDao;
import com.sober.bos.domain.Decidedzone;
import com.sober.bos.domain.Subarea;
import com.sober.bos.service.base.IDecidedzoneService;
import com.sober.bos.service.base.ISubareaService;
import com.sober.bos.utils.PageBean;
import com.sober.bos.dao.IDecidedzoneDao;
import com.sober.bos.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Renhai on 2016/11/3.
 */
@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {
    @Resource
    private IDecidedzoneDao decidedzoneDao;
    @Resource
    private ISubareaDao subareaDao;

    @Override
    public void pageQuery(PageBean pageBean)  {
        decidedzoneDao.pageQuery(pageBean);
    }

    @Override
    public void save(Decidedzone model, String[] subareaid) {
        decidedzoneDao.save(model);//持久态对象
        for (String id:subareaid) {
            //关联分区
            Subarea subarea = subareaDao.findById(id);
            subarea.setDecidedzone(model);
        }
    }
}
