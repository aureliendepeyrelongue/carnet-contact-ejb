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

		em.createQuery("DELETE FROM Contact c").executeUpdate();
		em.createQuery("DELETE FROM ContactGroup cg").executeUpdate();
		em.createQuery("DELETE FROM PhoneNumber p").executeUpdate();
		em.createQuery("DELETE FROM Address a").executeUpdate();
		ContactGroup cg = new ContactGroup("M2miage");

		Address a1 = new Address("12 rue Benjamin constant", "Sartrouville", "78600", "France");
		PhoneNumber pn1 = new PhoneNumber("portable","0645328945");
		PhoneNumber pn2 = new PhoneNumber("fixe","0123459856");
		Contact c1 = new Contact("Jean","Le violon", "jean.leviolon@gmail.com",a1);
		c1.getPhones().add(pn1);
		c1.getPhones().add(pn2);
		pn1.setContact(c1);
		pn2.setContact(c1);

		Address a2 = new Address("11 rue de Marseille", "Paris", "75011", "France");
		PhoneNumber pn3 = new PhoneNumber("portable","0612654390");
		PhoneNumber pn4 = new PhoneNumber("fixe","0132458769");
		Contact c2 = new Contact("Marie","Dupont", "marie.dupont@gmail.com",a2);
		c2.getPhones().add(pn3);
		c2.getPhones().add(pn4);
		pn3.setContact(c2);
		pn4.setContact(c2);

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

