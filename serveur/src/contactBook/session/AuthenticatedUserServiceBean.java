package contactBook.session;

import javax.ejb.*;
import javax.persistence.*;
import java.util.*;

import contactBook.entity.*;

@Stateful (mappedName="authenticatedUserService")
public class AuthenticatedUserServiceBean implements AuthenticatedUserServiceItf {
    @PersistenceContext(unitName="carnetContactPU")
	private EntityManager em;
	private long authenticatedUserId;
	private boolean authenticated = false;
	
	public String login(String userName) {
		List<Contact> userList = 
		em.createQuery("SELECT c FROM Contact c WHERE c.lastName LIKE :lastName")
		.setParameter("lastName",userName)
		.setMaxResults(1)
		.getResultList();

		if(userList.size() == 0 ){
			throw new Error("Erreur d'authentification, l'utilisateur " + userName + "est inconnu.");
		}
		 
		authenticatedUserId = userList.get(0).getId();
		authenticated = true;

		return "Authentification réussie pour l'utilisateur : " + userName;
	};


	public String addContactToFriendGroup(String firstName, String lastName, String email, String street,
	String city, String zip, String country, String phoneKind,String phoneNumber
	){

		if(!authenticated){
			throw new Error("Erreur, accès interdit.");
		}
		Contact authenticatedUser = (Contact) em.find(Contact.class,authenticatedUserId);
		
		// on définit un nom dynamiquement pour le groupe d'amis de l'utilisateur authentifié
		String friendGroupName = "Amis-" + authenticatedUser.getLastName();
		// on entamme un processus "find or create" pour le groupe d'amis
		List<ContactGroup> groupList = 
		em.createQuery("SELECT cg FROM ContactGroup cg WHERE cg.groupName LIKE :groupName")
		.setParameter("groupName",friendGroupName)
		.setMaxResults(1)
		.getResultList();

		ContactGroup cg;

		// on crée le groupe s'il n'existe pas déjà
		if(groupList.size() == 0 ){
		cg = new ContactGroup(friendGroupName);
		cg.getContacts().add(authenticatedUser);
		authenticatedUser.getContactGroups().add(cg);
		}
       // on récupère le groupe d'amis s'il existe déjà en base
		else{
        cg = groupList.get(0);
		}

	   Address address = new Address(street,city,zip,country);

       Contact friend = new Contact(firstName,lastName,email, address);

	   PhoneNumber pn = new PhoneNumber(phoneKind,phoneNumber);

	   friend.getPhones().add(pn);
	   pn.setContact(friend);
	   cg.getContacts().add(friend);
	   friend.getContactGroups().add(cg);

	   try{
		// on persite le groupe 
		em.persist(cg);
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	   return "Ajout d'un nouveau contact ("+ firstName + " " + lastName +
	   ") au groupe d'amis de " + authenticatedUser.getLastName() +".";
	};

	public String addPhoneNumber(String phoneKind, String phoneNumber){
		if(!authenticated){
			throw new Error("Erreur, accès interdit.");
		}
		Contact authenticatedUser = (Contact) em.find(Contact.class,authenticatedUserId);
        PhoneNumber pn = new PhoneNumber(phoneKind,phoneNumber);
		authenticatedUser.getPhones().add(pn);
		pn.setContact(authenticatedUser);

		try{
			em.persist(authenticatedUser);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return "Succes de l'ajout du numero " + phoneNumber 
		+ " pour l'utilisateur " 
		+ authenticatedUser.getLastName()+".";

	}

	public List<String> getPhones() throws Error{
		if(!authenticated){
			throw new Error("Erreur, accès interdit.");
		}
		Contact authenticatedUser = (Contact) em.find(Contact.class,authenticatedUserId);

		List<String> phones = new ArrayList<String>();

		for(PhoneNumber phone : authenticatedUser.getPhones()){
			phones.add(phone.getPhoneNumber());
		}

		return phones;
	}




}

