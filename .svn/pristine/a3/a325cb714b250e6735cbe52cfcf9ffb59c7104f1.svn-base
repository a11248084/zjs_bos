package com.itheima.bos.service.base;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.utils.PageBean;
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
