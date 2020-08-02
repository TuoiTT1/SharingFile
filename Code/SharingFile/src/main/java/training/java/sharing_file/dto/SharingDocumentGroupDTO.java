package training.java.sharing_file.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SharingDocumentGroupDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int ID;
	
	private GroupDTO groupDto;
	
	private DocumentDTO documentDto;

	@Override
	public String toString() {
		return "SharingDocumentGroupDTO [ID=" + ID + ", groupDto=" + groupDto + ", documentDto=" + documentDto + "]";
	}
	
	
}
