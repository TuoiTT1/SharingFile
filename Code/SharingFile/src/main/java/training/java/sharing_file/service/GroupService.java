package training.java.sharing_file.service;

import java.util.List;

import org.springframework.stereotype.Service;

import training.java.sharing_file.dto.GroupDTO;

@Service
public interface GroupService {

	GroupDTO save(GroupDTO groupDTO);
	
	List<GroupDTO> getAll();
	
	
}
