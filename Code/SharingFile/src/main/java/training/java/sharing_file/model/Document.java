package training.java.sharing_file.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Document")
@Getter
@Setter
public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 19292871029774679L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, length = 4)
	private int ID;

	@Column(name = "FileName", nullable = false, length = 255)
	private String fileName;

	@Column(name = "Title", nullable = false, length = 255)
	private String title;

	@Column(name = "Extension", nullable = false, length = 10)
	private String extension;

	@Column(name = "Size", nullable = true, length = 4)
	private Integer size;

	@Column(name = "Description", nullable = true, length = 255)
	private String description;

	@Column(name = "Is_Active", nullable = false, length = 1)
	private char is_Active;

	@OneToMany(mappedBy = "document", targetEntity = ActionDocument.class)
	@Cascade( CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.TRUE)
	private Set<ActionDocument> actionDocuments = new HashSet<ActionDocument>();

	@OneToMany(mappedBy = "document", targetEntity = SharingDocumentGroup.class)
	@Cascade(CascadeType.ALL )
	@LazyCollection(LazyCollectionOption.TRUE)
	private Set<SharingDocumentGroup> sharingDocumentGroup = new HashSet<SharingDocumentGroup>();

	@Override
	public String toString() {
		return "Document [ID=" + ID + ", fileName=" + fileName + ", extension=" + extension +  ", title=" + title
				+ ", size=" + size + ", description=" + description + ", is_Active=" + is_Active + ", actionDocuments="
				+ actionDocuments + ", sharingDocumentGroup=" + sharingDocumentGroup + "]";
	}

}
