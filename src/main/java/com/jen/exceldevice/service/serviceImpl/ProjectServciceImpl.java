package com.jen.exceldevice.service.serviceImpl;

import com.jen.exceldevice.dao.BaseDao;
import com.jen.exceldevice.pojo.Project;
import com.jen.exceldevice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("projectService")
public class ProjectServciceImpl implements ProjectService {

    @Autowired
    private BaseDao baseDao;

    @Override
    public List<Project> getProjectList() {
        return baseDao.getList("ProjectMapper.getProjectList",null);
    }

    @Override
    public List<Project> getProjectListBy(Project project) {

        return baseDao.getList("ProjectMapper.getProjectListBy",project);
    }

    @Override
    public int insertProject(Project project) {

        return baseDao.save("ProjectMapper.insertProject",project);
    }
}
