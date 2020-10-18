import javax.naming.InitialContext;
import java.io.*;
import java.util.*;

public class Client {
    
    private static contactBook.session.FillDatabaseServiceItf fillDatabaseService ;
    private static contactBook.session.CommonServiceItf commonService;
    private static contactBook.session.AuthenticatedUserServiceItf authenticatedUserService;
    
    public static void main(String[] args) throws Exception {

        System.out.println("Lancement du client lourd.");
        try {
            InitialContext ctx = new InitialContext();
            fillDatabaseService = (contactBook.session.FillDatabaseServiceItf) ctx.lookup("fillDatabaseService");
            commonService = (contactBook.session.CommonServiceItf) ctx.lookup("commonService");
            authenticatedUserService = (contactBook.session.AuthenticatedUserServiceItf) ctx.lookup("authenticatedUserService");
        }
        catch (Exception ex) {
            System.err.println("Erreur dans le lookup.");
            ex.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        boolean menuContinue = true;
        String menuOption;

        while(menuContinue){
        System.out.println("Menu -> tapez un chiffre pour choisir une option :");
        System.out.println("1 : Initiez la base avec des donnees de test.");
        System.out.println("2 : Affichez les numéros de telephone par groupe de contact.");
        System.out.println("3 : Login et ajout d'ami.");
        System.out.println("4 : Login et ajout de numéro de téléphone puis affichage des numéros.");
        System.out.println("Autre : Quitter le programme client.");
     
        menuOption = scanner.nextLine();

        if(menuOption.equals("1")){
            System.out.println("Initialisation de la base de données avec des valeurs de test.");
            try{
                fillDatabaseService.initDatabase();
                
                Thread.sleep(4000);
            }
            catch (Exception e){
                System.err.println("Erreur lors de l'initialisation de valeurs dans la base.");
                e.printStackTrace();
            }
        }
        else if(menuOption.equals("2")){

            System.out.println("Entrez un nom de groupe : (ex : M2miage) ");
            try{
                String groupName;
            
                groupName = scanner.nextLine();
                // Appel d'un bean session stateless
                List<String> phoneNumberList =  commonService.getPhoneNumbersByContactGroupName(groupName);
    
                for(String pn : phoneNumberList){
                    System.out.println(pn);
                }

                Thread.sleep(4000);
    
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(menuOption.equals("3")){
            try {
                
                System.out.println("Entrez un nom de login : (Dupont)");
                String loginName;
        
                loginName = scanner.nextLine();
    
                String loginResponse = authenticatedUserService.login(loginName);
                System.out.println(loginResponse);
    
                System.out.println("Insertion d'un nouvel ami en base :");
    
                String addFriendResponse = authenticatedUserService.addContactToFriendGroup("Toto", "Chevrel", "toto.chevrel@gmail.com", "11 rue du mesnil",
               "Le Mesnil", "78300", "France", "Portable","0645678932");
               System.out.println(addFriendResponse);
               
               Thread.sleep(4000);
    
            } catch (Exception e) {
                e.printStackTrace();
                //TODO: handle exception
            }
        }
        else if(menuOption.equals("4")){
            try {
                
                Thread.sleep(4000);
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
        else{
            menuContinue = false;
        }
        

    }
    System.out.println("Fin du client.");


    }
}

