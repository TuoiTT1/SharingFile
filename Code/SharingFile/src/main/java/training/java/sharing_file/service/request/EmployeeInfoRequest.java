package training.java.sharing_file.service.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeInfoRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int groupId;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private char isWorking;

	private String userName;
	
	private String password;
	
	private int managerId;
}
