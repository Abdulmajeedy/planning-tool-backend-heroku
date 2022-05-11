package com.hmy.planning.systemconfiguration.service;

import java.util.List;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.projectRequestDto;
import com.hmy.planning.systemconfiguration.dto.projectResponseDto;
import com.hmy.planning.systemconfiguration.models.projects;
import com.hmy.planning.systemconfiguration.repository.ProjectsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class projectService {

    @Autowired
    private ProjectsRepository projectRepo;

    @Autowired
    public projectService(ProjectsRepository orgStructureRepo) {
        this.projectRepo = projectRepo;

    }

    public List<projects> findAllProjects(PageRequest pageRequest) {
        return projectRepo.findAll(pageRequest).getContent();
    }

    public ResponseEntity<projectResponseDto> addNewProject(projectRequestDto reqproj) {
        projects org = new projects();

        org.setProjectName(reqproj.getProjectName());
        org.setDescription(reqproj.getDescription());
        org.setStatus(reqproj.getStatus());
        projectRepo.save(org);

        projectResponseDto projectDto = new projectResponseDto();
        projectDto.setProjectCode(org.getProjectCode());
        projectDto.setProjectName(org.getProjectName());
        projectDto.setDescription(org.getDescription());
        projectDto.setStatus(org.getStatus());
        projectDto.setCreatedDate(org.getCreatedDate());
        projectDto.setCreatedBy(org.getCreatedBy());
        projectDto.setModifiedDate(org.getModifiedDate());
        projectDto.setModifiedBy(org.getModifiedBy());
        return ResponseEntity.ok(projectDto);
    }

    public void deleteProject(String projectCode) {

        projectRepo.deleteById(projectCode);
    }

    public Optional<projects> getProjectById(String projectCode) {
        return projectRepo.findById(projectCode);
    }

    public void updateProject(String projectCode, projects reqProj) {
        projectRepo.findById(
                projectCode)
                .orElseThrow(() -> new IllegalStateException(
                        "Project  with Code " + projectCode + " does not exist"));

        reqProj.setProjectCode(projectCode);
        projects org = new projects();
        org.setCreatedBy(reqProj.getCreatedBy());
        org.setCreatedDate(reqProj.getCreatedDate());
        org.setModifiedBy(reqProj.getModifiedBy());
        projectRepo.save(reqProj);

    }

}
