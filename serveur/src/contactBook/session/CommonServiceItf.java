package contactBook.session;

import javax.ejb.Remote;
import java.util.*;

@Remote()
public interface CommonServiceItf {
	public List<String> getPhoneNumbersByContactGroupName(String contactGroupName) throws Exception;
	public List<String> getLesNoms();
	public String addContact();
	}
