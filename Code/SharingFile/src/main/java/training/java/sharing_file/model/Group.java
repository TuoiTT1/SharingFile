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
@Table(name = "group_department")
@Getter
@Setter
public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7743164049650347997L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private int ID;

	@Column(name = "Code", nullable = false, length = 20)
	private String code;

	@Column(name = "Name", nullable = false, length = 255)
	private String name;

	@Column(name = "Description", nullable = true, length = 255)
	private String description;

	@OneToMany(mappedBy = "group", targetEntity = Employee.class)
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.LOCK })
	@LazyCollection(LazyCollectionOption.TRUE)
	private Set<Employee> employees = new HashSet<>();

	@OneToMany(mappedBy = "group", targetEntity = SharingDocumentGroup.class)
	@Cascade({ CascadeType.ALL })
	@LazyCollection(LazyCollectionOption.TRUE)
	private Set<SharingDocumentGroup> sharingDocumentGroups = new HashSet<SharingDocumentGroup>();

	@Override
	public String toString() {
		return "Group [ID=" + ID + ", code=" + code + ", name=" + name + ", description=" + description + ", employees="
				+ employees + ", sharingDocumentGroups=" + sharingDocumentGroups + "]";
	}

}
