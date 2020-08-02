package training.java.sharing_file.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int ID;
	
	private String code;
	
	private String name;
	
	private String description;
	
	private Set<EmployeeDTO> employees = new HashSet<>();
	
	private Set<SharingDocumentGroupDTO> sharingDocumentGroups = new HashSet<SharingDocumentGroupDTO>();
	
	@Override
	public String toString() {
		return "Group [ID=" + ID + ", code=" + code + ", name=" + name + ", description=" + description + "]";
	}
	
	public GroupDTO() {
		super();
	}

	public GroupDTO(int iD) {
		super();
		ID = iD;
	}

}
