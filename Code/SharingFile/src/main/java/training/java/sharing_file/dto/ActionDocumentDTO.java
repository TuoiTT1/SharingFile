package training.java.sharing_file.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionDocumentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int ID;

	private UserDTO userDto;

	private DocumentDTO documentDto;

	private ActionDTO actionDto;

	private String note;

	private Timestamp modifyOn;

	@Override
	public String toString() {
		return "ActionDocumentDTO [ID=" + ID + ", userDto=" + userDto + ", documentDto=" + documentDto + ", actionDto="
				+ actionDto + ", note=" + note + ", modifyOn=" + modifyOn + "]";
	}

	public ActionDocumentDTO(UserDTO userDto, ActionDTO actionDto, String note, Timestamp modifyOn) {
		super();
		this.userDto = userDto;
		this.actionDto = actionDto;
		this.note = note;
		this.modifyOn = modifyOn;
	}

	public ActionDocumentDTO() {
		super();
	}

	public ActionDocumentDTO(int iD, UserDTO userDto, DocumentDTO documentDto, ActionDTO actionDto, String note,
			Timestamp modifyOn) {
		super();
		ID = iD;
		this.userDto = userDto;
		this.documentDto = documentDto;
		this.actionDto = actionDto;
		this.note = note;
		this.modifyOn = modifyOn;
	}

}
