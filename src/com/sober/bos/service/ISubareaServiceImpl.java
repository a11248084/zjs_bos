package com.sober.bos.service;

import com.sober.bos.dao.ISubareaDao;
import com.sober.bos.domain.Subarea;
import com.sober.bos.service.base.ISubareaService;
import com.sober.bos.utils.PageBean;
import com.sober.bos.service.base.ISubareaService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Renhai on 2016/11/1.
 */
@Service
@Transactional
public class ISubareaServiceImpl implements ISubareaService {
    @Resource
    private ISubareaDao subareaDao;

    @Override
    public void save(Subarea model) {
        subareaDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    @Override
    public List<Subarea> findAll() {
        return subareaDao.findAll();
    }

    @Override
    public List<Subarea> findByCondition(DetachedCriteria dc) {
        return subareaDao.findByCriteria(dc);
    }
}
