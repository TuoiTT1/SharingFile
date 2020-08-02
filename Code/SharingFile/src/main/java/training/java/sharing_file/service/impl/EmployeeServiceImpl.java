package training.java.sharing_file.service.impl;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import training.java.sharing_file.dto.EmployeeDTO;
import training.java.sharing_file.model.Employee;
import training.java.sharing_file.model.Group;
import training.java.sharing_file.model.User;
import training.java.sharing_file.repository.EmployeeRepository;
import training.java.sharing_file.repository.GroupRepository;
import training.java.sharing_file.service.EmployeeService;
import training.java.sharing_file.utils.Constants;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private GroupRepository groupRepository;
	

	
	public static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	
	@Override
	public EmployeeDTO insert(EmployeeDTO employeeDTO) {

		try {
			Employee employee = modelMapper.map(employeeDTO, Employee.class);
			
			Group group = groupRepository.getOne(employeeDTO.getGroupDTO().getID());
			Employee manager = employeeRepository.getOne(employeeDTO.getManager().getID());
			User user = modelMapper.map(employeeDTO.getUserDTO(), User.class);
			
			employee.setGroup(group);
			employee.setUser(user);
			employee.setManager(manager);
			
			
			employee = employeeRepository.save(employee);
			
			employeeDTO.setID(employee.getID());
			return employeeDTO;
		} catch (Exception e) {
			logger.error(Constants.MSG_ERROR + e.getMessage());
			return null;
		}

	}
}
