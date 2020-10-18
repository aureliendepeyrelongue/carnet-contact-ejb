package contactBook.entity;


import java.io.Serializable;
import java.util.HashSet;

import javax.persistence.*;

@Entity
@Table(name = "jpa.phonenumbers")
public class PhoneNumber implements Serializable {

	private static final long serialVersionUID = 5032950650642953367L;
	
	private String phoneKind;
	private String phoneNumber;
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="id_contact")
	private Contact contact=null;
	
	public PhoneNumber() {	
	}
	
	public PhoneNumber(String phoneKind, String phoneNumber) {
		super();
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneKind() {
		return phoneKind;
	}

	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
}
