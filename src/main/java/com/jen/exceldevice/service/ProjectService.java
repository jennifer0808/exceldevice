package com.jen.exceldevice.service;

import com.jen.exceldevice.pojo.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getProjectList();

    List<Project> getProjectListBy(Project project) ;

    int insertProject(Project project);

    int deleteProject(Project project);
}
