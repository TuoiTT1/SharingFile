package training.java.sharing_file.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2238486122166151998L;

	private int ID;

	private String code;

	private String name;

	private Set<ActionDocumentDTO> actionDocuments = new HashSet<ActionDocumentDTO>();

	@Override
	public String toString() {
		return "ActionDTO [ID=" + ID + ", code=" + code + ", name=" + name + ", actionDocuments=" + actionDocuments
				+ "]";
	}

	public enum EAction {
		INSERT, UPDATE, DELETE;

		@Override
		public String toString() {
			if (this == INSERT) {
				return "INSERT";
			}
			if (this == UPDATE) {
				return "UPDATE";
			}
			if (this == DELETE) {
				return "DELETE";
			}

			return super.toString();
		}

	}
}
