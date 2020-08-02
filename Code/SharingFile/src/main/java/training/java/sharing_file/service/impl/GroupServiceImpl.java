package training.java.sharing_file.service.impl;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import training.java.sharing_file.dto.GroupDTO;
import training.java.sharing_file.model.Group;
import training.java.sharing_file.repository.GroupRepository;
import training.java.sharing_file.service.GroupService;
import training.java.sharing_file.utils.Constants;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	public static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);


	@Override
	public GroupDTO save(GroupDTO groupDTO) {
		try {
			Group group = modelMapper.map(groupDTO, Group.class);
			group = groupRepository.save(group);

			File currentDirFile = new File(".");
			File folderGroup =  new File(Paths.get(currentDirFile.getCanonicalPath(), 
					Constants.PATH_FOLDER,
					group.getCode()).toString());
			if(!folderGroup.exists()) {
				folderGroup.mkdir();
			}
			return modelMapper.map(group, GroupDTO.class);

		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<GroupDTO> getAll() {

		List<Group> groups = new ArrayList<>();
		groups = groupRepository.findAll();
		if (groups != null && !groups.isEmpty()) {
			return groups.stream().map(g -> modelMapper.map(g, GroupDTO.class)).collect(Collectors.toList());
		}
		return null;
	}

}
