package com.jen.exceldevice.service.serviceImpl;

import com.jen.exceldevice.common.globalConfig.GlobalConstants;
import com.jen.exceldevice.dao.BaseDao;
import com.jen.exceldevice.pojo.*;
import com.jen.exceldevice.service.*;
import com.jen.exceldevice.utils.ReadExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
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
    private String deviceDescribe = null;
    private String deviceName = null;
    private  String deviceAddress = null;
    private String stastionName = null;
    private String driveProtocolName = null;
    private String linkName = null;
    private String deviceTypeName = null;
    private  String deviceCategoryShszCode = null;
    private  Integer deviceOvertime = null;
    private Integer deviceIsvirtual = null;
    private  String deviceShszId = null;

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
    @Autowired
    DeviceService deviceService;


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
            } else if (i == 1) {//sheet2
                readExcelSheet2(sheet);
            } else if (i == 2) {//sheet3
                readExcelSheet3(sheet);
            }

        }
        return notNull;
    }


    //导入sheet1
    public void readExcelSheet1(Sheet sheet) {
        //项目信息导入
        int project_id = readProjectData(sheet);
        //站点信息导入
        readStastionData(sheet, project_id);
        //驱动信息导入
        readDriveData(sheet, project_id);
        //链路信息导入
        readLinkData(sheet, project_id);
    }


    //导入sheet2
    public void readExcelSheet2(Sheet sheet) {
        //读取头数据
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(1);
        String projectName = cell.getStringCellValue();
        projectService.deleteProject(new Project(projectName));//允许只有一条数据
        int project_id = projectService.getProjectListBy(new Project(projectName)).get(0).getId();
        //读正文数据
        for (int k = 0; k < sheet.getLastRowNum() - 1; k++) {

            row = sheet.getRow(k + 2);//从第二行读起
            StringBuffer buffer = new StringBuffer();

            for (int i = 0; i < row.getLastCellNum(); i++) {
                cell = row.getCell(i);
                cell.setCellType(CellType.STRING);
                String cellVal = cell.getStringCellValue();
                System.out.println(cellVal);
                if (cell == null || "".equals(cellVal)) {
                    break;
                }
                buffer.append(cellVal + ";");
            }
            System.err.println("buffer:"+buffer);
            //处理数据
            readDeviceData(buffer,project_id);
//            deviceService.insertDevice(new Device(project_id,stastion_id,drive_id,link_id,deviceName,deviceDescribe,deviceAddress,deviceOvertime,deviceTypeName,deviceShszId,deviceCategoryShszCode,deviceIsvirtual));

//            int count = deviceService.queryInnerAll(project_id, stastionName, driveProtocolName, linkName);
//            System.out.println(count);
//            if(count ==0){//插入
                //查出对应id

//                deviceService.insertDevice(new Device(project_id,stastion_id,drive_id,link_id,deviceName,deviceDescribe,deviceAddress,deviceOvertime,deviceTypeName,deviceShszId,deviceCategoryShszCode,deviceIsvirtual));
//            }

        }


    }

    //处理sheet2中的每行数据
    public void readDeviceData(StringBuffer stringBuffer,int projectId) {
        String[] str = stringBuffer.toString().split(";");
        for (int i = 0; i <= str.length; i++) {
            try {
                deviceDescribe = str[1].trim();
                deviceName = str[2].trim();
                deviceAddress = str[3].trim();
                stastionName = str[4].trim();
                driveProtocolName = str[5].trim();
                linkName = str[6].trim();
                deviceTypeName = str[7].trim();
                deviceCategoryShszCode = str[8].trim();
                deviceOvertime = Integer.valueOf(str[9].trim());
                deviceIsvirtual = Integer.valueOf(str[10].trim());
                deviceShszId = str[11].trim();

            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }


    //导入sheet3
    public void readExcelSheet3(Sheet sheet) {

    }


    //项目信息导入
    public int readProjectData(Sheet sheet) {
        Project project = ReadExcelUtils.readProject(sheet.getRow(0));
        projectService.deleteProject(project);
        List<Project> projectList = projectService.getProjectListBy(project);
        int project_id = 0;
        if (projectList.size() == 0) {
            projectService.insertProject(project);
        }
        project_id = projectService.getProjectListBy(project).get(0).getId();
        System.out.println("readProjectData()-project_id:" + project_id);
        return project_id;
    }

    //站点信息导入
    public void readStastionData(Sheet sheet, int project_id) {
        List<String> stastionListAll = ReadExcelUtils.readCellByRow(sheet.getRow(1));
        for (String satastionName : stastionListAll) {
            Stastion stastion = new Stastion(project_id, satastionName);
            List<Stastion> stastions = stastionService.getStastionList(stastion);
            if (stastions.size() == 0) {//插入
                stastionService.insertStastion(stastion);
            }
            //测试
            List<Stastion> stastionstest = stastionService.getStastionList(stastion);
            for (Stastion sta : stastionstest) {
                GlobalConstants.stastionMap.put(sta.getName(),sta.getId());
            }

        }
        System.out.println(GlobalConstants.stastionMap);
    }

    //驱动信息导入
    public void readDriveData(Sheet sheet, int project_id) {
        List<String> driveListALl = ReadExcelUtils.readCellByRow(sheet.getRow(2));
        for (String strByCell : driveListALl) {
            String stastionName = strByCell.split(":")[0].trim();
            String driveName = strByCell.split(":")[1].trim();
            Stastion stastion = new Stastion(project_id, stastionName);
            List<Stastion> stastions = stastionService.getStastionList(stastion);
            if (stastions != null) {
                for (Stastion stastion1 : stastions) {
                    Drive drive = new Drive(stastion1.getProject_id(), stastion1.getId(), driveName);
                    List<Drive> driveListBy = driveService.getDriveList(drive);
                    if (driveListBy.size() == 0) {//插入
                        driveService.insertDrive(drive);
                    }
                    //测试
                    List<Drive> driveListtest = driveService.getDriveList(drive);
                    for (Drive dri : driveListtest) {
                        GlobalConstants.driveMap.put(stastionName+":"+dri.getProtocol_name(),dri.getId());
                    }
                }
            }
        }
        System.out.println(GlobalConstants.driveMap);
    }

    //链路信息导入
    public void readLinkData(Sheet sheet, int project_id) {
        List<String> linkNameList = ReadExcelUtils.readCellByRow(sheet.getRow(3));
        List<String> linkIPList = ReadExcelUtils.readCellByRow(sheet.getRow(4));

        String linkIP = null;
        String linkPortId = null;
        int linkPort = 0;
        for (int i = 0; i < linkNameList.size(); i++) {
            String linkNameStr = linkNameList.get(i);

            String stastionName = linkNameStr.split(":")[0].trim();
            String driveProtocolName = ((linkNameStr.split(":")[1]).split("_")[0]).trim();
            String linkName = ((linkNameStr.split(":")[1]).split("_")[1]).trim();

            String linkIPStr = linkIPList.get(i);
            if (linkIPStr.contains(".")) {
                GlobalConstants.serverComFlag = true;
                linkIP = linkIPStr.split(":")[0].trim();
                String linkPortStr = linkIPStr.split(":")[1].trim();
                linkPort = Integer.valueOf(linkPortStr);
            } else {
                linkPortId = linkIPStr.trim();
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
                            System.err.println("drive_id" + drive_id);
                            Link link = new Link(drive1.getProject_id(), stastion_id, drive_id, linkName);
                            System.out.println("link:"+link);
                            List<Link> kList = linkService.getLinkList(link);
                            if (kList.size() == 0) {//插入
                                if (GlobalConstants.serverComFlag) {//插入服务器串口
                                    link.setType(0);
                                    link.setIpaddress(linkIP);
                                    link.setPort(linkPort);
                                    GlobalConstants.serverComFlag = false;
                                } else {//插入通讯串口
                                    link.setType(1);
                                    link.setPortid(linkPortId);
                                }
                                linkService.insertLink(link);
                            }

//                           // 测试
//                            List<Link> linkListtest = linkService.getLinkList(link);
//                            for(Link lin :linkListtest){
//                                GlobalConstants.linkMap.put(stastionName+":"+driveProtocolName+":"+lin.getName(),lin.getId());
//                            }


                        }
                    }
                }
            }
        }
        System.out.println(GlobalConstants.linkMap);

    }



}
