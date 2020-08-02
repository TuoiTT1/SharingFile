package training.java.sharing_file.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int ID;

	private String name;

	private String email;

	private String phone;

	private char isWorking;

	private GroupDTO groupDTO;

	private UserDTO userDTO;

	private EmployeeDTO manager;

	private Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();

	public EmployeeDTO(int iD) {
		super();
		ID = iD;
	}

	public EmployeeDTO() {
	}

	public EmployeeDTO(int iD, String name, String email, String phone, char isWorking, GroupDTO groupDTO,
			UserDTO userDTO, EmployeeDTO manager, Set<EmployeeDTO> employees) {
		super();
		ID = iD;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.isWorking = isWorking;
		this.groupDTO = groupDTO;
		this.userDTO = userDTO;
		this.manager = manager;
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [ID=" + ID + ", name=" + name + ", email=" + email + ", phone=" + phone + ", isWorking="
				+ isWorking + ", groupId=" + groupDTO + ", userDto=" + userDTO + ", manager=" + manager + ", employees="
				+ employees + "]";
	}

}
