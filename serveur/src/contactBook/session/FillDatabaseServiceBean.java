package contactBook.session;

import javax.ejb.*;
import javax.persistence.*;
import java.util.*;

import contactBook.entity.*;

@Stateless (mappedName="fillDatabaseService")
public class FillDatabaseServiceBean implements FillDatabaseServiceItf {
    @PersistenceContext(unitName="carnetContactPU")
	private EntityManager em;
	public String initDatabase(){
		ContactGroup cg = new ContactGroup("M2miage");

		Address a1 = new Address("12 rue Benjamin constant", "Sartrouville", "78600", "France");
		PhoneNumber pn1 = new PhoneNumber("0645328945","portable");
		PhoneNumber pn2 = new PhoneNumber("0123459856","fixe");
		Contact c1 = new Contact("Jean","Le violon", "jean.leviolon@gmail.com",a1);
		c1.getPhones().add(pn1);
		c1.getPhones().add(pn2);

		Address a2 = new Address("11 rue de Marseille", "Paris", "75011", "France");
		PhoneNumber pn3 = new PhoneNumber("0612654390","portable");
		PhoneNumber pn4 = new PhoneNumber("0132458769","fixe");
		Contact c2 = new Contact("Marie","D'ertang", "marie.dertang@gmail.com",a2);
		c2.getPhones().add(pn3);
		c2.getPhones().add(pn4);


		cg.getContacts().add(c1);
		cg.getContacts().add(c2);
		c1.getContactGroups().add(cg);
		c2.getContactGroups().add(cg);

		try{
			em.persist(cg);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "Insertion de test réussie";
		
	}
	

}

