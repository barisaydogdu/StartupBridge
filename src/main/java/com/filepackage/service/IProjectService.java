package com.filepackage.service;

import com.filepackage.dto.ProjectDto;

public interface IProjectService <ProjectDto,Long> extends IBaseService<ProjectDto,Long>{
    ProjectDto createProject(ProjectDto commentDto);
}
