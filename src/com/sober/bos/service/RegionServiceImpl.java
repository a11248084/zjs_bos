package com.sober.bos.service;

import com.sober.bos.dao.IRegionDao;
import com.sober.bos.domain.Region;
import com.sober.bos.service.base.IRegionService;
import com.sober.bos.utils.PageBean;
import com.sober.bos.dao.IRegionDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Renhai on 2016/11/1.
 */
@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
    @Resource
    private IRegionDao iRegionDao;

    @Override
    public void saveBatch(List<Region> regionList) {
        for (Region region:regionList) {
            iRegionDao.saveOrUpdate(region);
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        iRegionDao.pageQuery(pageBean);
    }

    @Override
    public List<Region> findAll() {
        return iRegionDao.findAll();
    }
}
