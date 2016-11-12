package com.sober.bos.dao;

import com.sober.bos.dao.base.BaseDaoImpl;
import com.sober.bos.domain.Function;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Renhai on 2016/11/7.
 */
@Component
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {

    @Override
    public List<Function> findByUserId(String uid) {
        String hql="from Function f left outer join fetch f.roles r left outer join fetch r.users u where u.username = ?  ";
        return this.getHibernateTemplate().find(hql,uid);
    }

    @Override
    public List<Function> findAllMenu() {
        String hql="from Function f where f.generatemenu= '1' and f.type != 1 order by f.zindex desc ";
        List<Function> list = this.getHibernateTemplate().find(hql);
        //获取
        return  list;
    }

    @Override
    public List<Function> findMenu(String id) {
        String hql="select  distinct f from Function f left outer join fetch f.roles r left outer join fetch r.users u where u.id = ? and  f.generatemenu ='1' and f.type != 1 and f.parentFunction.id!= ?  and f.id!=? order by f.zindex desc ";
        return  this.getHibernateTemplate().find(hql,id,"100","100");
    }

    @Override
    public List<Function> findParent() {
     /* String sql="select * from auth_function where pid is null;";
        SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
        sqlQuery.addEntity(Function.class);
        List<Function> list = sqlQuery.list();
        return list;*/

     String hql="from Function f left join fetch f.parentFunction ff where ff.id is null";
       return this.getHibernateTemplate().find(hql);
    }

    @Override
    public List<Function> findSysMenu() {
        String hql="from Function f where f.generatemenu= '1' and f.type = 1 order by f.zindex desc ";
        return this.getHibernateTemplate().find(hql);
    }
}
/*
    from Function f left join fetch f.parentFunction ff where ff.id is null;*/
