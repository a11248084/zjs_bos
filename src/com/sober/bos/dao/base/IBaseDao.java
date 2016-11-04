package com.sober.bos.dao.base;

import com.sober.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.sober.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Renhai on 2016/10/30.
 */
public interface IBaseDao<T>  {
    /**
     * 增加
     */

    public void save(T t);

    void saveOrUpdate(T entity);

    /**
     *
     * 删除
     *
     */

    public void delete(T t);

    /**
     * 查询所有
     */
    public List<T> findAll();


    /**
     *
     * 通过id查询
     */
    public T findById(Serializable serializable);

    /**
     * 修改
     */
    public void update(T t);

    /**
     * 通过条件查询对象
     */

    public List<T> findByCriteria(DetachedCriteria detachedCriteria);

    /**
     *
     * 通过命名语句查询
     *
     */
    public List<T> findByNamedQuery(String queryName,Object...args);

    /**
     * 执行增删改的命名语句
     */
    public void executeNamedQuery(String queryName,Object...args);

    /**
     * 通过分页查询的方法
     */

    public void pageQuery(PageBean pageBean);
}


