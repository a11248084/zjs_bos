package com.sober.bos.service;

import com.sober.bos.dao.IFunctionDao;
import com.sober.bos.domain.Function;
import com.sober.bos.service.base.IFunctionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Renhai on 2016/11/7.
 */
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

    @Resource
    private IFunctionDao functionDao;

    @Override
    public List<Function> findAll() {
        return functionDao.findAll();
    }
}
