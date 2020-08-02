package training.java.sharing_file.model;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "action_document")
@Getter
@Setter
public class ActionDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7663704549781092490L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, length = 4)
	private int ID;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@Cascade(CascadeType.LOCK)
	@JoinColumn(name = "UserName", referencedColumnName = "UserName", nullable = false)
	private User user;

	@ManyToOne(targetEntity = Document.class, fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "DocID", referencedColumnName = "ID", nullable = false)
	private Document document;

	@ManyToOne(targetEntity = Action.class, fetch = FetchType.LAZY)
	@Cascade(CascadeType.LOCK)
	@JoinColumn(name = "ActionID", referencedColumnName = "ID", nullable = false)
	private Action action;

	@Column(name = "Note", nullable = false, length = 255)
	private String note;

	@Column(name = "ModifyOn", nullable = false)
	private Timestamp modifyOn;

	@Override
	public String toString() {
		return "ActionDocument [ID=" + ID + ", user=" + user + ", document=" + document + ", action=" + action
				+ ", note=" + note + ", modifyOn=" + modifyOn + "]";
	}

	public ActionDocument(User user, Action action, Document document, String note, Timestamp modifyOn) {
		super();
		this.user = user;
		this.action = action;
		this.document = document;
		this.note = note;
		this.modifyOn = modifyOn;
	}

	public ActionDocument() {
		super();
	}

	public ActionDocument(int iD, User user, Document document, Action action, String note, Timestamp modifyOn) {
		super();
		ID = iD;
		this.user = user;
		this.document = document;
		this.action = action;
		this.note = note;
		this.modifyOn = modifyOn;
	}

}
