package com.sober.bos.service;

import com.sober.bos.dao.IDecidedzoneDao;
import com.sober.bos.dao.INoticebillDao;
import com.sober.bos.dao.IWorkbillDao;
import com.sober.bos.domain.Decidedzone;
import com.sober.bos.domain.Noticebill;
import com.sober.bos.domain.Staff;
import com.sober.bos.domain.Workbill;
import com.sober.bos.service.base.CustomerService;
import com.sober.bos.service.base.INoticebillService;
import com.sober.bos.service.base.IWorkbillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * Created by Renhai on 2016/11/4.
 */
@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {
    @Resource
    private INoticebillDao noticebillDao;

    @Resource
    private IDecidedzoneDao decidedzoneDao;
    @Resource
    private CustomerService customerService;
    @Resource
    private IWorkbillDao workbillDao;

    @Override
    public void save(Noticebill model) {
        //保存通知单    持久态对象
        noticebillDao.save(model);
        //尝试自动分单
        //通过地址查询到定区id 绑定定区
        String decidedzoned_id = customerService.findDecidedzoneByAddress(model.getPickaddress());
        if (decidedzoned_id != null) {
            //说明定区存在可以自动分单
            Decidedzone decidedzone = decidedzoneDao.findById(decidedzoned_id);
            //获取定区中的取派员对象
            Staff staff = decidedzone.getStaff();
            model.setStaff(staff);
            model.setOrdertype("自动分单");
            //创建工单
            Workbill workbill = new Workbill();
            workbill.setAttachbilltimes(0);//追单次数
            workbill.setStaff(staff);
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//工单生成时间
            workbill.setPickstate("未取件");
            workbill.setAttachbilltimes(0);
            workbill.setRemark(model.getRemark());
            workbill.setType("新");   //工单类型  新 追 改  销
            workbill.setNoticebill(model);
            workbillDao.save(workbill);
        }else {
            //不能直接自动分单  转为人工分单
            model.setOrdertype("人工分单");
        }
    }
}
