package contactBook.session;

import javax.ejb.Remote;
import java.util.*;

@Remote()
public interface FillDatabaseServiceItf {
	public String initDatabase();
	}
