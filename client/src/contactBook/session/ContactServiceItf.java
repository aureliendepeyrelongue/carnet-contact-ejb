package contactBook.session;

import javax.ejb.Remote;
import java.util.*;

@Remote()
public interface ContactServiceItf {
	public List<String> getLesNoms();
	public String addContact();
	}
