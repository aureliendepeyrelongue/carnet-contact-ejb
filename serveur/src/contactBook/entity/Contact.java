package contactBook.entity;

import javax.persistence.*;
import java.util.*;
import java.io.Serializable; 

@Entity
@Table(name = "jpa.contacts")

public class Contact implements Serializable {

	private static final long serialVersionUID = 3586181024269191105L;
	
	private String firstName;
	private String lastName;
	private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_adress")
	private Address address;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="contact")
	Set<PhoneNumber> phones = new HashSet<PhoneNumber>();
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="CTC_GRP",
	joinColumns=@JoinColumn(name="CTC_ID"),
	inverseJoinColumns=@JoinColumn(name="GRP_ID"))
	private Set<ContactGroup> contactGroups=new HashSet<>();
	
	public Contact(){
	}

	public Contact(String firstName, String lastName, String email, Address adress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = adress;
	}
	
	public Contact(String firstName, String lastName, String email, Address adress, Set phones) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = adress;
		this.phones = phones;
	}


	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstname){
		this.firstName = firstname;
	}
	
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastname){
		this.lastName = lastname;
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	public Set<PhoneNumber> getPhones() {
		return phones;
	}

	public void setPhones(Set<PhoneNumber> phones) {
		this.phones = phones;
	}

	public void addPhoneNumber(PhoneNumber phone) {
		this.phones.add(phone);
	}

	public Set<ContactGroup> getContactGroups() {
		return contactGroups;
	}

	public void setContactGroups(Set<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}
	
}

