package com.jen.exceldevice.service.serviceImpl;

import com.jen.exceldevice.common.globalConfig.GlobalConstants;
import com.jen.exceldevice.dao.BaseDao;
import com.jen.exceldevice.pojo.Drive;
import com.jen.exceldevice.pojo.Link;
import com.jen.exceldevice.pojo.Project;
import com.jen.exceldevice.pojo.Stastion;
import com.jen.exceldevice.service.DriveService;
import com.jen.exceldevice.service.LinkService;
import com.jen.exceldevice.service.ProjectService;
import com.jen.exceldevice.service.StastionService;
import com.jen.exceldevice.utils.ReadExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
import java.util.Map;

@Service("linkService")
public class LinkServiceImpl implements LinkService {
    private final static Logger logger = LoggerFactory.getLogger(LinkServiceImpl.class);
    @Autowired
    BaseDao baseDao;

    @Autowired
    ProjectService projectService;
    @Autowired
    StastionService stastionService;
    @Autowired
    DriveService driveService;
    @Autowired
    LinkService linkService;


    @Override
    public List<Link> getLinkList(Link link) {
        return baseDao.getList("LinkMapper.getLinkList", link);
    }

    @Override
    public int insertLink(Link link) {
        return baseDao.save("LinkMapper.insertLink", link);
    }

    @Override
    public boolean readExcel(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<Link> linkList = new ArrayList<>();
        //识别文件格式
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
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
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
            sheet = workbook.getSheetAt(i);
            System.out.println("sheet:" + sheet);
            if (sheet != null) {
                notNull = true;
            }

            if (i == 0) {//sheet1
                readExcelSheet1(sheet);
            }else if(i == 1){//sheet2
                readExcelSheet2(sheet);
            }else if(i == 2){//sheet3
                readExcelSheet3(sheet);
            }

        }
        return notNull;
    }



    //导入sheet1
    public void readExcelSheet1(Sheet sheet) {
        //项目信息导入
       int project_id =  readProjectData(sheet);
        //站点信息导入
        readStastionData(sheet,project_id);
        //驱动信息导入
        readDriveData(sheet,project_id);
        //链路信息导入
        readLinkData(sheet,project_id);
    }


    //导入sheet2
    public void readExcelSheet2(Sheet sheet) {
        //读取Excel项目信息
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        String projectName  = cell.getStringCellValue();
        int project_id =  projectService.getProjectListBy(new Project(projectName)).get(0).getId();








    }

    //导入sheet3
    public void readExcelSheet3(Sheet sheet) {

    }



    //项目信息导入
    public int readProjectData(Sheet sheet){
        Project project = ReadExcelUtils.readProject(sheet.getRow(0));
        List<Project> projectList = projectService.getProjectListBy(project);
        int project_id = 0;
        if (projectList.size() == 0) {
            projectService.insertProject(project);
        }
        project_id = projectService.getProjectListBy(project).get(0).getId();
        System.out.println("readProjectData()-project_id:"+project_id);
        return  project_id;
    }

    //站点信息导入
    public void readStastionData(Sheet sheet,int project_id){
        List<String> stastionListAll = ReadExcelUtils.readCellByRow(sheet.getRow(1));
        for(String satastionName:stastionListAll){
            Stastion stastion = new Stastion(project_id,satastionName);
            List<Stastion> stastions = stastionService.getStastionList(stastion);
            if (stastions.size() == 0) {//插入
                stastionService.insertStastion(stastion);
            }

        }
    }

    //驱动信息导入
    public void  readDriveData(Sheet sheet,int project_id){
        List<String> driveListALl = ReadExcelUtils.readCellByRow(sheet.getRow(2));
        for(String strByCell:driveListALl){
            String stastionName = strByCell.split(":")[0].trim();
            String driveName = strByCell.split(":")[1].trim();
            Stastion stastion = new Stastion(project_id, stastionName);
            List<Stastion> stastions = stastionService.getStastionList(stastion);
            if (stastions != null) {
                for (Stastion stastion1 : stastions) {
                    Drive drive = new Drive(stastion1.getProject_id(), stastion1.getId(),driveName);
                    List<Drive> driveListBy = driveService.getDriveList(drive);
                    if (driveListBy.size() == 0) {//插入
                        driveService.insertDrive(drive);
                    }
                    //测试
                    List<Drive> driveListtest = driveService.getDriveList(drive);
                }
            }
        }
    }

    //链路信息导入
    public void readLinkData(Sheet sheet,int project_id){
        List<String> linkNameList = ReadExcelUtils.readCellByRow(sheet.getRow(3));
        List<String> linkIPList = ReadExcelUtils.readCellByRow(sheet.getRow(4));

        String linkIP =null;
        String linkPortId =null;
        int linkPort = 0;
        for(int i=0;i<linkNameList.size();i++){
            String linkNameStr = linkNameList.get(i);

            String stastionName = linkNameStr.split(":")[0].trim();
            String driveProtocolName = ((linkNameStr.split(":")[1]).split("_")[0]).trim();
            String linkName = ((linkNameStr.split(":")[1]).split("_")[1]).trim();

            String linkIPStr = linkIPList.get(i);
            if(linkIPStr.contains(".")){
                GlobalConstants.linkCOMFlag = true;
                linkIP = linkIPStr.split(":")[0].trim();
                String linkPortStr = linkIPStr.split(":")[1].trim();
                linkPort = Integer.valueOf(linkPortStr);
            }else{
                linkPortId =linkIPStr.trim();
            }
            Stastion stastion = new Stastion(project_id, stastionName);
            List<Stastion> stastions = stastionService.getStastionList(stastion);
            if (stastions != null) {
                for (Stastion stastion1 : stastions) {
                    int stastion_id = stastion1.getId();
                    Drive drive = new Drive(stastion1.getProject_id(), stastion_id, driveProtocolName);
                    List<Drive> driveList = driveService.getDriveList(drive);
                    if (driveList != null) {
                        for (Drive drive1 : driveList) {
                            int drive_id = drive1.getId();
                            System.err.println("drive_id"+drive_id);
                            Link link = new Link(drive1.getProject_id(), stastion_id, drive_id, linkName);
                            List<Link> kList = linkService.getLinkList(link);
                            if (kList.size() == 0) {//插入
                                if(GlobalConstants.linkCOMFlag){//插入服务器串口
                                    link.setType(0);
                                    link.setIpaddress(linkIP);
                                    link.setPort(linkPort);
                                    GlobalConstants.linkCOMFlag =false;
                                }else{//插入通讯串口
                                    link.setType(1);
                                    link.setPortid(linkPortId);
                                }
                                linkService.insertLink(link);
                            }

                        }
                    }
                }
            }
        }

    }

}
