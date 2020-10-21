<%@ page import="javax.naming.*"%> 

<%!
    private exoConversion.session.convertItf converter = null;

	public void jspInit() {
        try {
		InitialContext ctx = new InitialContext();		
		converter = (exoConversion.session.convertItf) ctx.lookup("laConversion");
        } catch (Exception ex) {
            System.out.println("erreur dans le lookup."+ ex.getMessage());
        }
    }

    public void jspDestroy() {
        converter = null;
    }
%>
<html>
    <head>
        <title>Conversion euro - dollar</title>
    </head>

    <body bgcolor="white">
        <h1>Conversion de monnaies</h1>
        <hr>
        <p>Entrer le montant :</p>
        <form method="post">
            <input type="text" name="amount" size="25">
            <br>
            <p>
            <input type="submit" value="Submit">
            <input type="reset" value="Reset">
        </form>

        <%
            String amount = request.getParameter("amount");
            if ( amount != null && amount.length() > 0 ) {
                int d = Integer.parseInt(amount);
                
                int euroAmount = converter.dollarToEuro(d);
        %>
        <p>
        <%= amount %> dollars valent  <%= euroAmount %>  Euros.
        <p>
        <%
                int dollarAmount = converter.euroToDollar(euroAmount);
        %>
        <%= euroAmount %> Euros valent <%= dollarAmount %>  Dollars.
        <%
            }
        %>

    </body>
</html>
