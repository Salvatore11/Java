<%@page import="java.util.List"%>
<%@page import="it.unipr.informatica.esercizio12.modello.Studente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Esercizio 12</title>
<script>
function conferma(matricola) {
	var risposta= confirm("Sei sicuro di voler cancellare lo studente " + matricola);
	
	if(risposta)
		location.href= "cancella_studente? matricola= " + matricola;
}
</script>
</head>
<body>
<h1>Sistema di Gestione dell'Ateneo</h1>
<p><a href="logout">Esci</a></p>
<p><a href="elenco_studenti">Elenco degli Studenti</a></p>
<p><a href="nuovo_studente.jsp">Nuovo Studente</a></p>
<table>
<thead>
<tr>
<td/><td/><td/><td>Matricola</td><td>Cognome</td><td>Nome</td>
</tr>
</thead>
<tbody>
<%
	@SuppressWarnings("unchecked")
	List<Studente> studenti= (List<Studente>)session.getAttribute("elenco_studenti");

	if(studenti != null) {
		for(Studente studente : studenti) {
%>
<tr>
<td><a href="visualizza_studente?matricola=<%=studente.getMatricola() %>">Visualizza</a></td>
<td><a href="modifica_studente?matricola=<%=studente.getMatricola() %>">Modifica</a></td>
<td><a href="javascript:conferma('<%=studente.getMatricola() %>')">Cancella</a></td>
<td><%=studente.getMatricola() %></td>
<td><%=studente.getCognome() %></td>
<td><%=studente.getNome() %></td>
</tr>
<%
		}
		session.removeAttribute("elenco_studenti");
	}
%>
</tbody>
</table>
</body>
</html>