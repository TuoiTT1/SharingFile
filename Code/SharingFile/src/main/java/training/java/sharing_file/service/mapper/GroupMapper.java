package training.java.sharing_file.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import training.java.sharing_file.dto.GroupDTO;
import training.java.sharing_file.model.Group;

@Mapper
public interface GroupMapper{

	@Mapping(target = "")
	GroupDTO toDto(Group group);
}
