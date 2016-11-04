package com.sober.bos.service;

import com.sober.bos.dao.IWorkbillDao;
import com.sober.bos.service.base.IWorkbillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Renhai on 2016/11/4.
 */
@Service
@Transactional
public class WorkbillServiceImpl implements IWorkbillService {

    @Resource
    private IWorkbillDao workbillDao;

}
