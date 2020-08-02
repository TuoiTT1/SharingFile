package training.java.sharing_file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import training.java.sharing_file.dto.EmployeeDTO;
import training.java.sharing_file.dto.GroupDTO;
import training.java.sharing_file.dto.UserDTO;
import training.java.sharing_file.service.EmployeeService;
import training.java.sharing_file.service.UserService;
import training.java.sharing_file.service.request.EmployeeInfoRequest;
import training.java.sharing_file.service.response.MessageResponse;
import training.java.sharing_file.utils.Constants;

@RestController
@RequestMapping(value = "/user")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UserService userService;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping(value = "/new")
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeInfoRequest employeeInfo) {
//		check userName exist
		if (userService.existsByUsername(employeeInfo.getUserName())) {
			return ResponseEntity.badRequest().body(new MessageResponse(Constants.USERNAME_ALREADY));
		}
		
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setName(employeeInfo.getName());
		employeeDTO.setEmail(employeeInfo.getEmail());
		employeeDTO.setPhone(employeeInfo.getPhone());
		employeeDTO.setIsWorking(employeeInfo.getIsWorking());
		employeeDTO.setManager(new EmployeeDTO(employeeInfo.getManagerId()));
		employeeDTO.setGroupDTO(new GroupDTO(employeeInfo.getGroupId()));
		
		UserDTO userDTO = new UserDTO(employeeInfo.getUserName(), encoder.encode(employeeInfo.getPassword()),
				Constants.IS_ACTIVE);

		employeeDTO.setUserDTO(userDTO);

		employeeDTO = employeeService.insert(employeeDTO);

		if (employeeDTO.getID() != 0) {
			return ResponseEntity.badRequest().body(new MessageResponse(Constants.EMPLOYEE_INSERT_FAIL));
		}

		return ResponseEntity.ok(new MessageResponse(Constants.REGISTER_USER_SUCCESS + employeeDTO));

	}
}
