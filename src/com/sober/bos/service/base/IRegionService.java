package com.sober.bos.service.base;

import com.sober.bos.domain.Region;
import com.sober.bos.utils.PageBean;
import com.sober.bos.utils.PageBean;

import java.util.List;

/**
 * Created by Renhai on 2016/11/1.
 */
public interface IRegionService {
    public void saveBatch(List<Region> regionList);

    public void pageQuery(PageBean pageBean);

    public List<Region> findAll();

}
