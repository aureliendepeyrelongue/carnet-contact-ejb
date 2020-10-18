package contactBook.session;

import javax.ejb.*;
import javax.persistence.*;
import java.util.*;

import contactBook.entity.*;

@Stateless (mappedName="exoDemo2")
public class ContactServiceBean implements ContactServiceItf {
    @PersistenceContext(unitName="exoDemo2PU")
    private EntityManager em;

	public List<String> getLesNoms()
	{
		return em.createQuery("SELECT c.lastName FROM Contact c").getResultList();
	}

	public String addContact(){
		Address a = new Address("12 rue Benjamin constant", "Sartrouville", "78600", "France");
		Contact c = new Contact("Jean","Le violon", "jean.leviolon@gmail.com",a);
		try{
			em.persist(c);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "Insertion de test réussie";

	}
}

