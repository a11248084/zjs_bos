package com.sober.bos.service.base;

import com.sober.bos.domain.Staff;
import com.sober.bos.utils.PageBean;
import com.sober.bos.domain.Staff;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by Renhai on 2016/10/31.
 */
public interface IStaffService {
    public void save(Staff model);

    public   void pageQuery(PageBean pageBean);

    public  void delete(String ids);

    public  void restore(String ids);


    public  Staff findById(String id);

    public  void update(Staff staff);

    List<Staff> findByCondition(DetachedCriteria dc);
}
