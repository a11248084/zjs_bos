package com.itheima.bos.web.action;

import com.itheima.bos.domain.Region;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.utils.FileUtils;
import com.itheima.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Renhai on 2016/11/1.
 */
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
    /**
     * 添加分区的方法
     */
    public String save(){
        ISubareaService.save(model);
        return "list";
    }

    //分页查询的方法
    public String pageQuery(){
        //结合分页查询构造条件分页查询的方法
        String addresskey = model.getAddresskey();
        Region region = model.getRegion();
        DetachedCriteria Subcriteria = pageBean.getDetachedCriteria();
        //按照地址关键字进行模糊查询
        if (StringUtils.isNotBlank(addresskey)){
            Subcriteria.add(Restrictions.like("addresskey","%"+addresskey+"%"));
        }

        if (region!=null){
            DetachedCriteria Regioncriteria = Subcriteria.createCriteria("region");
            if (StringUtils.isNotBlank(region.getProvince())){
                Regioncriteria.add(Restrictions.like("province","%"+region.getProvince()+"%"));
            }
            if (StringUtils.isNotBlank(region.getCity())){
                Regioncriteria.add(Restrictions.like("city","%"+region.getCity()+"%"));
            }

            if (StringUtils.isNotBlank(region.getDistrict())){
                Regioncriteria.add(Restrictions.like("district","%"+region.getDistrict()+"%"));
            }
        }

        ISubareaService.pageQuery(pageBean);
        //写会数据
        writePageBean2Json(pageBean, new String[]{"decidedzone","subareas","detachedCriteria", "pageSize", "currentPage"});
        return NONE;
    }

    public String exportXls() throws IOException {
        //查询所有的分区数据
        List<Subarea> list= ISubareaService.findAll();
        //将list集合写入到excel文件中
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");//创建一个sheet页
        HSSFRow hssfRow=sheet.createRow(0);//创建标题行
        hssfRow.createCell(0).setCellValue("分区编号");
        hssfRow.createCell(1).setCellValue("区域编码");
        hssfRow.createCell(2).setCellValue("关键字");
        hssfRow.createCell(3).setCellValue("起始号");
        hssfRow.createCell(4).setCellValue("结束号");
        hssfRow.createCell(5).setCellValue("单双号");
        hssfRow.createCell(6).setCellValue("位置信息");
        //循环list  将数据写道Excel文件中
        for (Subarea subarea :list) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(subarea.getId());
            dataRow.createCell(1).setCellValue(subarea.getRegion().getId());
            dataRow.createCell(2).setCellValue(subarea.getAddresskey());
            dataRow.createCell(3).setCellValue(subarea.getStartnum());
            dataRow.createCell(4).setCellValue(subarea.getEndnum());
            dataRow.createCell(5).setCellValue(subarea.getSingle());
            dataRow.createCell(6).setCellValue(subarea.getPosition());
        }

        //文件下载 一个流  两个头
        ServletOutputStream out = response.getOutputStream();
        String fileName="subarea.xls";
        FileUtils.encodeDownloadFilename(fileName,"user-agent");
        response.setContentType(ServletActionContext.getServletContext().getMimeType(fileName));
        //设置下载文件名 以附件得形式下载
        response.setHeader("content-disposition","attachment;filename="+fileName);
        hssfWorkbook.write(out);


        return NONE;

    }
}
