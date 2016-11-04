package com.sober.bos.web.action;

import com.sober.bos.domain.Region;
import com.sober.bos.domain.Staff;
import com.sober.bos.utils.PageBean;
import com.sober.bos.utils.PinYin4jUtils;
import com.sober.bos.web.action.base.BaseAction;
import com.sober.bos.utils.PinYin4jUtils;
import com.sober.bos.web.action.base.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renhai on 2016/11/1.
 *
 * region 区域模块
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
    //提供属性接受上传的文件
    private File myFile;

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

    //一键导入区域数据的方法
    public String importXls() throws IOException {
        //设置一个是否成功导入的标志
        String flag="1";
        //批量导入需要的一个list集合容器
      try {

        List<Region> regionList=new ArrayList<>();
        //使用apache POI技术可以操作office办公软件的文档
        //解析Excel文件 读取Excel的文件的内容
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook(new FileInputStream(myFile));
        //解析Excel表的第一个sheet
        HSSFSheet sheetAt = hssfWorkbook.getSheetAt(0);
        //foreach遍历该sheet   得到每一行的记录  需要排除第一行的标题
        for (Row row:sheetAt) {
            //排除第一行
            if (row.getRowNum()==0){
                //第一行标题忽略
                continue;
            }
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();
            //将解析出的Excel文件的数据封装到Region对象中
            Region region=new Region(id,province,city,district,postcode);

            //使用pinyin4工具生成城市简码和城市编码
            //简码   比如北京市北京市昌平区  生成为 BJSBJSCPQ
            String shortcode=province+city+district;
            String[] strings = PinYin4jUtils.getHeadByString(shortcode);
            shortcode = StringUtils.join(strings, "");
            //城市编码----北京市-----》beijingshi
          String[] string2=PinYin4jUtils.stringToPinyin(city);
            String citycode=StringUtils.join(string2,"");

            //把城市简码和城市编码设置进Region对象中
            region.setCitycode(citycode);
            region.setShortcode(shortcode);
            //把每一个region对象都添加进list中
            regionList.add(region);
        }
        //执行批量保存的方法
        regionService.saveBatch(regionList);
      }catch (Exception e){
          //如果批量保存失败
          flag="0";
      }
        //向客户端会写数据 表示保存的状态 1 成功  0 失败
        response.getWriter().print(flag);
        return NONE;
    }

    //分页查询的方法
    //抽取方法到baseAction中简化代码
    public String pageQuery() throws IOException {

        //调用service层去做分页查询
        regionService.pageQuery(pageBean);
        writePageBean2Json(pageBean, new String[]{"detachedCriteria","pageSize","currentPage","subareas"});
        return NONE;
    }


    public String findListByAjax() throws IOException {
        //查询所有的区域并返回到页面
        List<Region> list=regionService.findAll();
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.setExcludes(new String[]{"subareas"});
        JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
        String json = jsonArray.toString();
        //响应json数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().print(json);
        return NONE;
    }
}
