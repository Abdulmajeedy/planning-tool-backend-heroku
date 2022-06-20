package com.hmy.planning.systemconfiguration.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hmy.planning.systemconfiguration.dto.projectRequestDto;
import com.hmy.planning.systemconfiguration.dto.projectResponseDto;
import com.hmy.planning.systemconfiguration.models.projects;
import com.hmy.planning.systemconfiguration.repository.ProjectsRepository;

import lombok.Data;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Data
public class projectService {

    @Autowired
    private ProjectsRepository projectRepo;
    private final ModelMapper modelmapper;

    public List<projectResponseDto> findAllProjects(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<projects> project = projectRepo.findAll(pageRequest).getContent();
        List<projectResponseDto> projDto = new ArrayList<>();
        for (projects proj : project) {
            projectResponseDto responseDto = modelmapper.map(proj, projectResponseDto.class);
            projDto.add(responseDto);
        }
        return projDto;
    }

    public ResponseEntity<projectResponseDto> addNewProject(projectRequestDto reqproj) {
        projects project = modelmapper.map(reqproj, projects.class);
        projectRepo.save(project);

        projectResponseDto projectDto = modelmapper.map(project, projectResponseDto.class);
        return ResponseEntity.ok(projectDto);
    }

    public void deleteProject(String projectCode) {

        projectRepo.deleteById(projectCode);
    }

    public projectResponseDto getProjectById(String projectCode) {
        Optional<projects> project = projectRepo.findById(projectCode);
        if (!project.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + projectCode + "is not Found");
        } else {
            projects proj = project.get();
            projectResponseDto responseDto = modelmapper.map(proj, projectResponseDto.class);
            return responseDto;
        }

    }

    public ResponseEntity<projectResponseDto> updateProject(String projectCode, projectRequestDto reqProj) {
        Optional<projects> project = projectRepo.findById(projectCode);
        if (!project.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "project  with this code" + projectCode + "is not Found");
        }
        projects proj = modelmapper.map(reqProj, projects.class);
        proj.setProjectCode(projectCode);
        projectRepo.save(proj);

        projectResponseDto projectDto = modelmapper.map(proj, projectResponseDto.class);
        return ResponseEntity.ok(projectDto);
    }

    public Map<String, Boolean> updateStatus(String projectCode) {
        Optional<projects> project = projectRepo.findById(projectCode);
        if (!project.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (project.get().getStatus() == 1)
            project.get().setStatus(0);
        else
            project.get().setStatus(1);
        projectRepo.save(project.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", Boolean.TRUE);
        return response;
    }

}
