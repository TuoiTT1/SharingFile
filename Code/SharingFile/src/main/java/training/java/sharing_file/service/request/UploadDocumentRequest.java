package training.java.sharing_file.service.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadDocumentRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pathFileLocal;
	
	private String title;
	
	private String description;
	
}
