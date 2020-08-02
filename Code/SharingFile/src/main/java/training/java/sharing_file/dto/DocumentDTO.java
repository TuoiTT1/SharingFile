package training.java.sharing_file.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import training.java.sharing_file.model.Document;


@Getter
@Setter
public class DocumentDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int ID;
	
	private String fileName;
	
	private String title;
	
	private String extension;
	
	private Integer size;
	
	private String description;
	
	private char is_Active;
	
	private Set<ActionDocumentDTO> actionDocuments = new HashSet<ActionDocumentDTO>();
	
	private Set<SharingDocumentGroupDTO> sharingDocumentGroup = new HashSet<SharingDocumentGroupDTO>();

	@Override
	public String toString() {
		return "DocumentDTO [ID=" + ID + ", fileName=" + fileName + ", title=" + title + ", extension=" + extension
				+ ", size=" + size + ", description=" + description + ", is_Active=" + is_Active + ", actionDocuments="
				+ actionDocuments + ", sharingDocumentGroup=" + sharingDocumentGroup + "]";
	}
	
	public Document convertToModel() {
		Document document = new Document();
		document.setFileName(fileName);
		document.setExtension(extension);
		document.setDescription(description);
		document.setTitle(title);
		document.setSize(size);
		document.setIs_Active(is_Active);
		
		return document;
	}

}
