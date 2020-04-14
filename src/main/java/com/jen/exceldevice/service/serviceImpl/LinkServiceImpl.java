package com.jen.exceldevice.service.serviceImpl;

import com.jen.exceldevice.pojo.Drive;
import com.jen.exceldevice.pojo.Link;
import com.jen.exceldevice.pojo.Project;
import com.jen.exceldevice.pojo.Stastion;
import com.jen.exceldevice.service.LinkService;
import com.jen.exceldevice.service.ProjectService;
import com.jen.exceldevice.service.StastionService;
import com.jen.exceldevice.utils.ReadExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("linkService")
public class LinkServiceImpl implements LinkService {
    private final static Logger logger = LoggerFactory.getLogger(LinkServiceImpl.class);

    @Autowired
    ProjectService projectService;
    @Autowired
    StastionService stastionService;

    @Override
    public boolean readExcel(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<Link> linkList = new ArrayList<>();
        //识别文件格式

        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
//            throw new MyException(110, "上传文件格式不正确");
            logger.info("上传文件格式不正确");
        }

        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        //读取Excel文件


        InputStream inputStream = file.getInputStream();
        Workbook workbook = null;
        if (isExcel2003) {
            workbook = new HSSFWorkbook(inputStream);
            inputStream.close();
        } else {
            workbook = new XSSFWorkbook(inputStream);
            inputStream.close();
        }

        //循环工作表
        Sheet sheet = null;
        String cell = null;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
            sheet = workbook.getSheetAt(i);
            System.out.println("sheet:" + sheet);
            if (sheet != null) {
                notNull = true;
            }
            if (i == 0) {//sheet1
                readExcelSheet(sheet);
            }

        }

        return notNull;
    }

    //导入sheet1
    public void readExcelSheet(Sheet sheet) {

        Project project = ReadExcelUtils.readProject(sheet.getRow(0));
        List<Project> projectList = projectService.getProjectListBy(project);
        int project_id = 0;
        if (projectList.size() != 0) {
             project_id = projectList.get(0).getId();
        }
        List<Stastion> stastionList = ReadExcelUtils.readStastion(sheet.getRow(1));

        int count = stastionService.getStastionListById(project_id);
        if(count ==0){
            //查询是否存在,1存在,0不存在
            System.err.println(" insertStastion();");

        }else{
            System.err.println(" updateStastion();");
        }


        List<Drive> driveList = ReadExcelUtils.readDrive(sheet.getRow(2));

    }


}
