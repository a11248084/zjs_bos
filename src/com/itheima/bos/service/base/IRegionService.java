package com.itheima.bos.service.base;

import com.itheima.bos.domain.Region;
import com.itheima.bos.utils.PageBean;

import java.util.List;

/**
 * Created by Renhai on 2016/11/1.
 */
public interface IRegionService {
    public void saveBatch(List<Region> regionList);

    public void pageQuery(PageBean pageBean);

    public List<Region> findAll();

}
