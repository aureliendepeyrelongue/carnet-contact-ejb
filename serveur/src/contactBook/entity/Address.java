package contactBook.entity;

import java.io.Serializable;
import java.util.HashSet;

import javax.persistence.*;

@Entity
@Table(name = "carnet_contact_ejb.addresses")
public class Address implements Serializable {

	private static final long serialVersionUID = 2758651940660248934L;
	
	private String street;
	private String city;
	private String zip;
	private String country;
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	public Address(){
	}
	
	public Address(String street, String city, String zip, String country) {
		super();
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
