package training.java.sharing_file.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Sharing_Document_Group")
@Getter
@Setter
public class SharingDocumentGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1382397442588819724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, length = 4)
	private int ID;

	@ManyToOne(targetEntity = Group.class, fetch = FetchType.LAZY)
	@Cascade(CascadeType.LOCK)
	@JoinColumn(name = "GroupID", referencedColumnName = "ID", unique = true, nullable = false)
	private Group group;

	@ManyToOne(targetEntity = Document.class, fetch = FetchType.LAZY)
	@Cascade(CascadeType.LOCK)
	@JoinColumn(name = "DocID", referencedColumnName = "ID", unique = true, nullable = false)
	private Document document;

	@Override
	public String toString() {
		return "SharingDocumentGroup [ID=" + ID + ", group=" + group + ", document=" + document + "]";
	}
}
