package com.kbrom.charity_lens_backend.mapper;

import com.kbrom.charity_lens_backend.dto.GetProjectDTO;
import com.kbrom.charity_lens_backend.model.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GetProjectMapper {
    GetProjectDTO toDTO (Project project);
    List<GetProjectDTO> toDTO_List (List<Project> projects);
}
