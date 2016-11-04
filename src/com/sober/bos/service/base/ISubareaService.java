package com.sober.bos.service.base;

import com.sober.bos.domain.Subarea;
import com.sober.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by Renhai on 2016/11/1.
 */
public interface ISubareaService {
    public void save(Subarea model);

   public void pageQuery(PageBean pageBean);

    public List<Subarea> findAll();


    public  List<Subarea> findByCondition(DetachedCriteria dc);

}
