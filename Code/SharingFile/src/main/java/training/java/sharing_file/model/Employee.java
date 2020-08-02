package training.java.sharing_file.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4910083894417810567L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private int ID;

	@Column(name = "Name", nullable = false, length = 100)
	private String name;

	@Column(name = "Email", nullable = false, length = 255)
	private String email;

	@Column(name = "Phone", nullable = false, length = 10)
	private String phone;

	@Column(name = "Is_Working", nullable = false, length = 1)
	private char isWorking;

	@ManyToOne(targetEntity = Group.class, fetch = FetchType.LAZY)
	@Cascade(CascadeType.LOCK)
	@JoinColumn(name = "GroupID", referencedColumnName = "ID", nullable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	private Group group;

	@OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL})
	@JoinColumn(name = "UserName", referencedColumnName = "UserName")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	private User user;

	@ManyToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "Manager", referencedColumnName = "ID", nullable = true)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	private Employee manager;

	@OneToMany(mappedBy = "manager", targetEntity = Employee.class, fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL  })
	@LazyToOne(LazyToOneOption.NO_PROXY)
	private Set<Employee> employees = new HashSet<Employee>();

	public Employee(int ID) {
		super();
		this.ID = ID;
	}

	public Employee() {
	}

	public Employee(int iD, String name, String email, String phone, char isWorking, Group group, User user,
			Employee manager, Set<Employee> employees) {
		super();
		ID = iD;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.isWorking = isWorking;
		this.group = group;
		this.user = user;
		this.manager = manager;
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Employee [ID=" + ID + ", name=" + name + ", email=" + email + ", phone=" + phone + ", isWorking="
				+ isWorking + ", group=" + group + ", user=" + user + ", manager=" + manager + ", employees="
				+ employees + "]";
	}

}
