package com.kbrom.charity_lens_backend.common.util.mappers;

import com.kbrom.charity_lens_backend.project.dto.GetProjectDTO;
import com.kbrom.charity_lens_backend.project.model.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GetProjectMapper {
    GetProjectDTO toDTO (Project project);
    List<GetProjectDTO> toDTO_List (List<Project> projects);
}
