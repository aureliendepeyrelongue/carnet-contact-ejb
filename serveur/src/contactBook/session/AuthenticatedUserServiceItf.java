package contactBook.session;

import javax.ejb.Remote;
import java.util.*;

@Remote()
public interface AuthenticatedUserServiceItf {
	public String login(String userName);

	public String addContactToFriendGroup(
		String firstName, 
		String lastName,
		String email,
		String street,
		String city,
		String zip,
		String country,
		String phoneKind,
		String phoneNumber
	
	);

	public List<String> getPhones() throws Error;
	
	public String addPhoneNumber(String phoneKind, String phoneNumber);
    
	}
