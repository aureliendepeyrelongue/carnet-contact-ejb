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
            System.out.println("\nInitialisation de la base de données avec des valeurs de test.");
            try{
                fillDatabaseService.initDatabase();
                
                Thread.sleep(2000);
            }
            catch (Exception e){
                System.err.println("Erreur lors de l'initialisation de valeurs dans la base.");
                e.printStackTrace();
            }
        }
        else if(menuOption.equals("2")){

            System.out.println("\nEntrez un nom de groupe : (ex : M2miage) ");
            try{
                String groupName;
            
                groupName = scanner.nextLine();
                // Appel d'un bean session stateless
                List<String> phoneNumberList =  commonService.getPhoneNumbersByContactGroupName(groupName);
                System.out.println("Affichage des numéros de téléphone pour le groupe " + groupName + " : ")
                for(String pn : phoneNumberList){
                    System.out.println(pn);
                }

                Thread.sleep(2000);
    
            }
            catch(Exception e){
                System.err.println("Erreur lors de la récupération des numéros du groupe.");
                e.printStackTrace();
            }
        }
        else if(menuOption.equals("3")){
            try {
                
                System.out.println("\nEntrez un nom de login : (Dupont)");
                String loginName;
        
                loginName = scanner.nextLine();
    
                String loginResponse = authenticatedUserService.login(loginName);
                System.out.println(loginResponse);
    
                System.out.println("Insertion d'un nouvel ami en base :");

                System.out.println("Ajout des valeurs -> ");
                System.out.println("Toto / Chevrel / toto.chevrel@gmail.com / 11 rue du mesnil / Le Mesnil / 78300 / France / Portable / 0645678932");
    
                String addFriendResponse = authenticatedUserService.addContactToFriendGroup("Toto", "Chevrel", "toto.chevrel@gmail.com", "11 rue du mesnil",
               "Le Mesnil", "78300", "France", "Portable","0645678932");
               System.out.println(addFriendResponse);
               Thread.sleep(2000);
            } catch (Exception e) {
                System.err.println("Erreur lors de l'ajout d'un contact au groupe d'ami.");
                e.printStackTrace();
            }
        }
        else if(menuOption.equals("4")){
            try {
                System.out.println("\nEntrez un nom de login : (Dupont)");
                String loginName, phoneKind, phoneNumber;
        
                loginName = scanner.nextLine();
                String loginResponse = authenticatedUserService.login(loginName);
                System.out.println(loginResponse + "\n");
                System.out.println("\nNuméros de télépone de l'utilisateur "+loginName+" avant ajout :" );
                List<String> phones = authenticatedUserService.getPhones();
    
                for(String pn : phones){
                    System.out.println(pn);
                }
                System.out.println("\nInsertion d'un nouveau numéro de téléphone en base ->");
                System.out.println("Type de téléphone à ajouter :");
                phoneKind = scanner.nextLine();
                System.out.println("Numéro de téléphone à ajouter :");
                phoneNumber = scanner.nextLine();
                
                String addPhoneNumberResponse = authenticatedUserService.addPhoneNumber(phoneKind, phoneNumber);
                System.out.println(addPhoneNumberResponse);
                System.out.println("\nMise en pause du programme avant affichage des numéros de téléphones de l'utilisateur connecté.");
                System.out.println("Tapez n'importe quoi pour continuer le programme :");
                scanner.nextLine();
                System.out.println("Numéros de télépone de l'utilisateur "+loginName+" après ajout :" );
                phones = authenticatedUserService.getPhones();
                for(String pn : phones){
                    System.out.println(pn);
                }

            Thread.sleep(2000);
            } catch (Exception e) {
                //TODO: handle exception
                System.err.println("Erreur lors de l'ajout d'un numéro et/ou de son affichage.");
                e.printStackTrace();
            }
        }
        else{
            menuContinue = false;
        }
        System.out.println("\n");
    }
    System.out.println("Fin du processus client.");


    }
}

