package training.java.sharing_file.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import training.java.sharing_file.dto.GroupDTO;
import training.java.sharing_file.service.GroupService;
import training.java.sharing_file.service.response.MessageResponse;
import training.java.sharing_file.utils.Constants;

@RestController
@RequestMapping(value = "/group")
public class GroupController {

	@Autowired
	GroupService groupService;
	
	@PostMapping (value = "/new")
	public ResponseEntity<?> createGroup(@RequestBody GroupDTO groupDTO) {
		groupDTO =  groupService.save(groupDTO);
		if(groupDTO == null) {
			return ResponseEntity.badRequest().body(new MessageResponse(Constants.CREATE_GROUP_FAIL));
		}
		return ResponseEntity.ok(new MessageResponse(Constants.CREATE_GROUP_SUCCESS));
	}
	
	@GetMapping(value = "/all")
	public List<GroupDTO> getAll(){
		return groupService.getAll();
	}
}
