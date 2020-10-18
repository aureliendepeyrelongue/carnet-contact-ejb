package contactBook.session;

import javax.ejb.*;
import javax.persistence.*;
import java.util.*;

import contactBook.entity.*;

@Stateless (mappedName="contactService")
public class ContactServiceBean implements ContactServiceItf {
    @PersistenceContext(unitName="carnetContactPU")
	private EntityManager em;
	
	public List<String> getPhoneNumbersByContactGroupName(String contactGroupName)
	throws Exception{
	 List<ContactGroup> cgList = 
	 em.createQuery("SELECT cg FROM ContactGroup cg WHERE cg.groupName LIKE :groupName")
	 .setParameter("groupName",contactGroupName)
	 .setMaxResults(1)
	 .getResultList();
	 
	 List<String> pnList = new ArrayList<String>();

	 if(cgList.size() == 0){
		throw new Exception("Le groupe "+contactGroupName+" n'est pas présent en base de donnée");
	 }

	 ContactGroup cg = cgList.get(0);
	  
	 for (Contact contact : cg.getContacts()){
		 for(PhoneNumber phone : contact.getPhones()){
			 pnList.add(phone.getPhoneNumber());
		 }
	 }

	 return pnList;
	}

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

