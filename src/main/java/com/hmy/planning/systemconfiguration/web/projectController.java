package com.hmy.planning.systemconfiguration.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.projectRequestDto;
import com.hmy.planning.systemconfiguration.dto.projectResponseDto;
import com.hmy.planning.systemconfiguration.models.projects;
import com.hmy.planning.systemconfiguration.repository.ProjectsRepository;
import com.hmy.planning.systemconfiguration.service.projectService;
import com.hmy.planning.systemconfiguration.utils.ApiResponse;
import com.hmy.planning.systemconfiguration.web.api.projectApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class projectController implements projectApi {

    private projectService projectService;
    private ProjectsRepository projectsRepo;
    private ModelMapper modelMapper;

    @Autowired
    public projectController(projectService projectService, ModelMapper modelMapper) {

        this.projectService = projectService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<projectResponseDto>> getProject(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<projects> project = projectService.findAllProjects(pageRequest);
        List<projectResponseDto> roleDto = new ArrayList<>();
        for (projects proj : project) {
            projectResponseDto responseDto = new projectResponseDto();
            responseDto.setProjectCode(proj.getProjectCode());
            responseDto.setProjectName(proj.getProjectName());
            responseDto.setDescription(proj.getDescription());
            responseDto.setStatus(proj.getStatus());
            responseDto.setCreatedDate(proj.getCreatedDate());
            responseDto.setCreatedBy(proj.getCreatedBy());
            responseDto.setModifiedDate(proj.getModifiedDate());
            responseDto.setModifiedBy(proj.getModifiedBy());
            roleDto.add(responseDto);
        }
        return ResponseEntity.ok(roleDto);
    }

    @Override
    public ResponseEntity<projectResponseDto> registerNewProject(projectRequestDto reqProject) {
        return projectService.addNewProject(reqProject);
    }

    @Override
    public ResponseEntity<projectResponseDto> getProjectById(String projectCode) {
        Optional<projects> project = projectService.getProjectById(projectCode);
        if (!project.isPresent()) {
            return new ResponseEntity(ApiResponse.error("Invalid role Code", null), HttpStatus.NOT_FOUND);
        } else {
            projects proj = project.get();
            projectResponseDto responseDto = new projectResponseDto();
            responseDto.setProjectCode(proj.getProjectCode());
            responseDto.setProjectName(proj.getProjectName());
            responseDto.setDescription(proj.getDescription());
            responseDto.setStatus(proj.getStatus());
            responseDto.setCreatedDate(proj.getCreatedDate());
            responseDto.setCreatedBy(proj.getCreatedBy());
            responseDto.setModifiedDate(proj.getModifiedDate());
            responseDto.setModifiedBy(proj.getModifiedBy());
            return ResponseEntity.ok(responseDto);
        }
    }

    @Override
    public void deleteById(String projectCode) {
        projectService.deleteProject(projectCode);

    }

    @Override
    public ResponseEntity updateProject(String projectCode, projects reqProject) {
        Optional<projects> project = projectService.getProjectById(projectCode);
        projectService.updateProject(projectCode, reqProject);
        projects proj = project.get();
        projectResponseDto responseDto = new projectResponseDto();

        responseDto.setProjectCode(proj.getProjectCode());
        responseDto.setProjectName(proj.getProjectName());
        responseDto.setDescription(proj.getDescription());
        responseDto.setStatus(proj.getStatus());
        responseDto.setCreatedDate(proj.getCreatedDate());
        responseDto.setCreatedBy(proj.getCreatedBy());
        responseDto.setModifiedDate(proj.getModifiedDate());
        responseDto.setModifiedBy(proj.getModifiedBy());
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity updateStatus(String projectCode) {
        return ResponseEntity.ok().body(projectService.updateStatus(projectCode));
    }

}
