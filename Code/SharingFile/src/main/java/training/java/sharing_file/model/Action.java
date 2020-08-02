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
@Table(name = "Action")
@Getter
@Setter
public class Action implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3812310153492355255L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, length = 4)
	private int ID;

	@Column(name = "Code", nullable = false, length = 4)
	private String code;

	@Column(name = "Name", nullable = false, length = 255)
	private String name;

	@OneToMany(mappedBy = "action", targetEntity = ActionDocument.class)
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.LOCK })
	@LazyCollection(LazyCollectionOption.TRUE)
	private Set<ActionDocument> actionDocuments = new HashSet<ActionDocument>();

	@Override
	public String toString() {
		return "Action [ID=" + ID + ", code=" + code + ", name=" + name + ", actionDocuments=" + actionDocuments + "]";
	}
}
