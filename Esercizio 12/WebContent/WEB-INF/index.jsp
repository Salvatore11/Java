<%@page import="it.unipr.informatica.esercizio12.web.Login"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Esercizio 12</title>
</head>
<body>
<h1>Esercizio 12</h1>
<form action="login" method="post">
<p>Nome utente:<input type="text" name="<%=Login.NOME_UTENTE%>" value=""/></p>
<p>Password:<input type="password" name="<%=Login.PASSWORD%>" value="" /></p>
<p><input type= "submit" value="Entra" /></p>
</form>
<%
	String errore=(String)session.getAttribute(Login.LOGIN_FALLITO);

	if(errore != null) {
		session.removeAttribute(Login.LOGIN_FALLITO);
%>

<p>
<%=errore %>
</p>
<%
	}
%>
</body>
</html>