package contactBook.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.*;

@Entity
@Table(name = "jpa.contactgroup")
public class ContactGroup implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1108112576432022038L;
	
	private String groupName;
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToMany(mappedBy="contactGroups")
	private Set <Contact> contacts=new HashSet<Contact>();
	
	public ContactGroup(){
	}

	public ContactGroup(String groupName) {
		super();
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void removeContact(Contact contact) {
		
	this.contacts.remove(contact);
		
		
	}
	
	
}
