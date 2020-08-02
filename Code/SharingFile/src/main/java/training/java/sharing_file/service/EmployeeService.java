package training.java.sharing_file.service;

import org.springframework.stereotype.Service;

import training.java.sharing_file.dto.EmployeeDTO;

@Service
public interface EmployeeService {
	
	EmployeeDTO insert(EmployeeDTO employeeDTO);
}
