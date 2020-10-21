<%@ page import="javax.naming.*"%>
<%@ page import="java.util.List" %>
<%@page pageEncoding="UTF-8"%> 

    <%!
    private contactBook.session.CommonServiceItf commonService;
    private InitialContext ctx;

	public void jspInit() {
        try {
	     ctx = new InitialContext();		
       commonService  = (contactBook.session.CommonServiceItf) ctx.lookup("commonService");
  
        } catch (Exception ex) {
            System.out.println("erreur dans l'initial context");
        }
    }


%>

<%
  
    contactBook.session.AuthenticatedUserServiceItf authenticatedUserService;

    if(session.getAttribute("authenticatedUserService") == null){
    authenticatedUserService = (contactBook.session.AuthenticatedUserServiceItf) ctx.lookup("authenticatedUserService");
    session.setAttribute("authenticatedUserService", authenticatedUserService);
    }
    else{  
        authenticatedUserService = (contactBook.session.AuthenticatedUserServiceItf) session.getAttribute("authenticatedUserService");
    }

%>

    <!doctype html>
<html lang="fr">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
  <title>Carnet Contact EJB - Servlet JSP</title>
  <script
			  src="https://code.jquery.com/jquery-3.5.1.min.js"
			  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
			  crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!-- Compiled and minified JavaScript -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
       <style>
    #main-container{
        margin-top:40px;

       }
       </style>
</head>
<body>
<ul id="dropdown1" class="dropdown-content">
        <li><a href="/carnet-contact/phones-number.jsp">Liste des numéros de téléphone par groupe</a></li>
       <% if(session.getAttribute("authenticated") != null && session.getAttribute("authenticated").equals("true") ) { 
         %> <li><a href="/carnet-contact/add-contact-to-friends.jsp">
         Ajouter un Contact aux amis</a></li>
            <li><a href="/carnet-contact/add-phone-number.jsp">
            Ajouter un numéro de téléphone</a></li>
          <% }  %>

</ul>
<nav class=" blue  darken-4" role="navigation">
    <div class="nav-wrapper container">
    <a id="logo-container" href="/carnet-contact" class="brand-logo valign-wrapper" style="font-size:1.8rem;"><i class="large material-icons">texture</i><span style="display:flex;"><span style="margin:auto;">Carnet Contact</span></span></a>
      <ul class="right hide-on-med-and-down">
       <li><a href="/carnet-contact/login.jsp">Login</a></li>
        <li><a class="dropdown-trigger" href="#!" data-target="dropdown1">Actions<i class="material-icons right">arrow_drop_down</i></a></li>
      </ul>

      <ul id="nav-mobile" class="sidenav">
        <li><a href="#">Lien</a></li>
      </ul>
      <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
     
    </div>
  </nav>
   <div id="main-container" class="container z-depth-2">
      <div class="row">
    <div class="col s12">
    <h5 class="header grey-text ">Login</h5>
</div>
</div>
    <div class="row">
        <div class="col s12">
    <form  method="post">
         <div class="input-field col s6">
          <input placeholder="Placeholder" name="login" id="first_name" type="text" class="validate">
          <label for="first_name">Nom</label>
        </div>
          <div class="row">
        <div class="input-field col s12">
         <button class="btn waves-effect waves-light right" type="submit" name="action">Se connecter
    <i class="material-icons right">send</i>
  </button>
        </div>
      </div>
      </form>
      </div>
    </div>

<% 

   String login = request.getParameter("login");
            if ( login != null && login.length() > 0 ) {
              try {
               String logResponse = authenticatedUserService.login(login);
               session.setAttribute("authenticated","true");
          
   
%>
 <div class="row">
    <div class="col s12 m5">
      <div class="card-panel teal">
        <span class="white-text">
        Vous êtes authentifié en tant que <%= login %>!
        </span>
      </div>
    </div>
  </div>
  
   <%
       }
                catch(javax.ejb.EJBException e){
                 session.removeAttribute("authenticated");
                 e.printStackTrace();
                  %>
 <div class="row">
    <div class="col s12 m5">
      <div class="card-panel red">
        <span class="white-text">
       Erreur <%= login %> inconnu !
        </span>
      </div>
    </div>
  </div>
                  <%
                }
    }
   %>
  </div>
  
  </div>


 <footer class="page-footer blue-grey  darken-4">
    <div class="container">
      <div class="row">
        <div class="col l6 s12">
          <h5 class="white-text">à propos</h5>
          <p class="grey-text text-lighten-4">
          Nous sommes une équipe d'étudiants universitaires et travaillons sur ce projet à temps plein.
        Toute aide extérieure nous est précieuse, n'hésitez pas à nous contacter !
          </p>


        </div>
        <div class="col l3 s12">
          <h5 class="white-text">Liens</h5>
          <ul>
            <li><a class="white-text" href="#!">Link 1</a></li>
            <li><a class="white-text" href="#!">Link 2</a></li>
            <li><a class="white-text" href="#!">Link 3</a></li>
            <li><a class="white-text" href="#!">Link 4</a></li>
          </ul>
        </div>
        <div class="col l3 s12">
          <h5 class="white-text">Contacts</h5>
          <ul>
            <li><a class="white-text" href="#!">Link 1</a></li>
            <li><a class="white-text" href="#!">Link 2</a></li>
            <li><a class="white-text" href="#!">Link 3</a></li>
            <li><a class="white-text" href="#!">Link 4</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="footer-copyright">
      <div class="container">
      Réalisé avec <a class="orange-text text-lighten-3" href="http://materializecss.com">Materialize</a>
      </div>
    </div>
  </footer>

<script type="text/javascript">
$(".dropdown-trigger").dropdown({ hover: true, constrainWidth: false });   
</script>
                </body>

        </html>