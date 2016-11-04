package com.sober.bos.service;

import com.sober.bos.dao.IStaffDao;
import com.sober.bos.domain.Staff;
import com.sober.bos.service.base.IStaffService;
import com.sober.bos.utils.PageBean;
import com.sober.bos.domain.Staff;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Staff的service层
 */

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
    @Resource
    private IStaffDao iStaffDao;

    //保存的方法
    @Override
    public void save(Staff model) {
        iStaffDao.save(model);
    }


    //分页查询的方法
    @Override
    public void pageQuery(PageBean pageBean) {
        iStaffDao.pageQuery(pageBean);
    }

    //作废的操作
    @Override
    public void delete(String ids) {
        //把字符串先且分开
        String[] split = ids.split(",");
        for (int i=0;i<split.length;i++){
            //调用dao层进行查询
            String id=split[i];
            Staff staff = iStaffDao.findById(id);
            //因为是持久态对象  所以可以直接进行修改  自动保存到数据库
            staff.setDeltag("1");
        }
    }

    @Override
    public void restore(String ids) {
        //把字符串先且分开
        String[] split = ids.split(",");
        for (int i=0;i<split.length;i++){
            //调用dao层进行查询
            String id=split[i];
            Staff staff = iStaffDao.findById(id);
            //因为是持久态对象  所以可以直接进行修改  自动保存到数据库
            staff.setDeltag("0");
        }
    }

    @Override
    public Staff findById(String id) {
        return iStaffDao.findById(id);
    }


    //保存的方法
    @Override
    public void update(Staff staff) {
        iStaffDao.update(staff);
    }


    //通过离线条件条件查询的方法
    @Override
    public List<Staff> findByCondition(DetachedCriteria dc) {
        return iStaffDao.findByCriteria(dc);
    }
}
