package com.sober.bos.web.action;

import com.sober.bos.domain.Workordermanage;
import com.sober.bos.utils.FileUtils;
import com.sober.bos.web.action.base.BaseAction;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renhai on 2016/11/5.
 */

@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {

    public String save() throws IOException {
        String flag="0";
        try {
            workordermanageService.save(model);
        }catch (Exception e){
            flag="1";
        }
        //回写数据
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(flag);
        return NONE;
    }

    public String findAllByAjax(){
        //查询所有的数据
        List<Workordermanage> list=workordermanageService.findAll();
        //序列化成json 回写数据
        writeList2Json(list,new String[]{});
        return NONE;
    }

    //使用属性封装封装封装上传的文件
    private File myFile;

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

    //一键上传excel表单的功能
    public String importXls() throws IOException {
        String flag="1";
        try{
            List<Workordermanage> list=new ArrayList<Workordermanage>();
            //使用apache的 POI技术可以操作office办公软件的文档
            HSSFWorkbook hssfWorkbook=new HSSFWorkbook(new FileInputStream(myFile));
            //解析第一个sheet
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            //foreach遍历该sheet   得到每一行的记录  需要排除第一行的标题
            for (Row row:sheet){
                //排除表单的第一行
                int rowNum = row.getRowNum();
                if (rowNum==0){
                    continue;
                }
            String id=row.getCell(0).getStringCellValue();
            String product=row.getCell(1).getStringCellValue();
            String prodtimelimit=row.getCell(2).getStringCellValue();
            String prodtype=row.getCell(3).getStringCellValue();
            String sendername=row.getCell(4).getStringCellValue();
               // POI操作Excel时数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串并写入数据库时，就会出现Cannot get a text value from a numeric cell的异常错误，解决办法就是先设置Cell的类型，然后就可以把纯数字作为String类型读进来了：
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            String senderphone=row.getCell(5).getStringCellValue();
            String senderaddr=row.getCell(6).getStringCellValue();
            String receivername=row.getCell(7).getStringCellValue();
                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
            String receiverphone=row.getCell(8).getStringCellValue();
            String receiveraddr=row.getCell(9).getStringCellValue();
                row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
            String actlweit=row.getCell(10).getStringCellValue();
                 //为每一个对象设置属性
                Workordermanage workordermanage=new Workordermanage(id,product,prodtimelimit,prodtype,sendername,senderphone,senderaddr,receivername,receiverphone,receiveraddr,Double.parseDouble(actlweit));
                //放入集合中
                list.add(workordermanage);
            }
            workordermanageService.saveBatch(list);
        }catch (Exception e){
            flag="2";
            e.printStackTrace();
        }

        //回写标志
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(flag);
        return NONE;
    }


    //模板下载
    public String downloadModel() throws IOException {
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建一个sheet页
        HSSFSheet sheet = workbook.createSheet("工作单管理模板");
        //创建第一行
        HSSFRow row = sheet.createRow(0);
        //设置第一行的每一列的字段名称
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("产品");
        row.createCell(2).setCellValue("产品时限");
        row.createCell(3).setCellValue("产品类型");
        row.createCell(4).setCellValue("发件人姓名");
        row.createCell(5).setCellValue("发件人电话");
        row.createCell(6).setCellValue("发件人地址");
        row.createCell(7).setCellValue("收件人姓名");
        row.createCell(8).setCellValue("收件人电话");
        row.createCell(9).setCellValue("收件人地址");
        row.createCell(10).setCellValue("实际重量");

        //文件下载 设置一个流两个头
        //获得输出流
        ServletOutputStream outputStream = response.getOutputStream();
        String filename="工作单模板.xls";
        filename=new String(filename.getBytes("UTF-8"),"ISO-8859-1");
        //防止中文乱码
        FileUtils.encodeDownloadFilename(filename, "user-agent");
        //设置 内容类型  Content-Type，用于定义网络文件的类型和网页的编码，决定文件接收方将以什么形式、什么编码读取这个文件，
        response.setContentType(request.getServletContext().getMimeType(filename));

        //Content-Disposition 的作用  当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        //注意：在设置Content-Disposition头字段之前，一定要设置Content-Type头字段。
        response.setHeader("content-disposition","attachment;filename="+filename);
        //文件下载
        workbook.write(outputStream);
        return  NONE;
    }
}
