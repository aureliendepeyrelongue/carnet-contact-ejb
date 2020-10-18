import javax.naming.InitialContext;
import java.io.*;
import java.util.*;

public class Client {
    
    private static contactBook.session.FillDatabaseServiceItf refBean ;
    
    public static void main(String[] args) throws Exception {
        
        System.out.println("Lancement du client lourd.");
        try {
            InitialContext ctx = new InitialContext();
            refBean = (contactBook.session.FillDatabaseServiceItf) ctx.lookup("fillDatabaseService");
            refBean.initDatabase();
        }
        catch (Exception ex) {
            System.err.println("erreur dans le lookup");
            ex.printStackTrace();
        }

      

        /*
        System.out.println("Entrez un nom de groupe : (ex : M2miage) ");
      
        try{
            Scanner scanner = new Scanner(System.in);
            String groupName;
            
            groupName = scanner.nextLine();

            List<String> phoneNumberList = refBean.getPhoneNumbersByContactGroupName(groupName);

            for(String pn : phoneNumberList){
                System.out.println(pn);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        

            
         System.out.println(refBean.addContact());*/

    }
}

