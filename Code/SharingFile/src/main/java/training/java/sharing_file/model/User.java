package training.java.sharing_file.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6233920997837563412L;

	@Id
	@Column(name = "UserName", nullable = false)
	private String userName;

	@Column(name = "Password", nullable = false)
	private String password;

	@Column(name = "Is_Active", nullable = false)
	private char isActive;

	@OneToMany(mappedBy = "user", targetEntity = ActionDocument.class)
	@Cascade({CascadeType.ALL })
	@LazyCollection(LazyCollectionOption.TRUE)
	private Set<ActionDocument> actionDocuments = new HashSet<ActionDocument>();

	@OneToOne(mappedBy = "user", targetEntity = Employee.class, fetch = FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	@LazyToOne(LazyToOneOption.NO_PROXY)
	private Employee employee;

	@Override
	public String toString() {
		return "User [UserName=" + userName + ", password=" + password + ", isActive=" + isActive + ", actionDocuments="
				+ actionDocuments + ", employee=" + employee + "]";
	}

}
