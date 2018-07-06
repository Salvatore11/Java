<%@page import="it.unipr.informatica.esercizio12.modello.Studente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Esercizio 12</title>
</head>
<body>
<h1>Sistema di Gestione dell'Ateneo</h1>
<p><a href="logout">Esci</a></p>
<p><a href="elenco_studenti">Elenco degli studenti</a></p>
<%
	Studente studente = (Studente)session.getAttribute("visualizza_studente");

	session.removeAttribute("visualizza_studente");

	if(studente == null) {
%>
<h2>Studente non trovato</h2>
<%
	} else {
%>
<p>Matricola: <%=studente.getMatricola() %></p>
<p>Cognome: <%=studente.getCognome() %></p>
<p>Nome: <%=studente.getNome() %></p>
<%		
	}
%>
</body>
</html>