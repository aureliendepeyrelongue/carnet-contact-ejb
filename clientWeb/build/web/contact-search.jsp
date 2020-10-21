<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Accueil</title>
  <%@ include file="./fragments/links.jspf" %>
</head>
<body>
<%@ include file="./fragments/navbar.jspf" %>
  
 <div id="main-container" class="container z-depth-2">
   
     
      <div class="row">
    <div class="col s12">
    <h5 class="header grey-text ">Rechercher un/des contacts</h5>
</div>
</div>

      <div class="row">
        <div class="col s12">
    <form>
        <div class="input-field">
          <input id="search" type="search" required>
          <label class="label-icon" for="search"><i class="material-icons">search</i></label>
          <i class="material-icons">close</i>
        </div>
      </form>
      </div>
    </div>
  </div>

  

    
  
  .<%@ include file="./fragments/footer.jspf" %>
   .<%@ include file="./fragments/scripts.jspf" %>
</body>
</html>

