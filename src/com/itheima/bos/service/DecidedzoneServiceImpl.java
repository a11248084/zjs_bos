package com.itheima.bos.service;

import com.itheima.bos.dao.IDecidedzoneDao;
import com.itheima.bos.service.base.IDecidedzoneService;
import com.itheima.bos.utils.PageBean;
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

    @Override
    public void pageQuery(PageBean pageBean) {
        decidedzoneDao.pageQuery(pageBean);
    }
}
