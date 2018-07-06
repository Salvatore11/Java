<%@page import="it.unipr.informatica.esercizio12.modello.Studente"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta content="charset=ISO-8859-1">
<title>Esercizio 12</title>
<script>
function controllaParametri() {
	var tag = document.getElementById("cognome");

	var testo = tag.value;

	var invia = document.getElementById("invia");

	invia.setAttribute("disabled", "disabled");

	if(testo.length == 0 || testo.length > 50)
		return;

	tag = document.getElementById("nome");

	testo = tag.value;

	if(testo.length == 0 || testo.length > 50)
		return;

	invia.removeAttribute("disabled");
}
</script>
</head>
<body>
<h1>Sistema di Gestione dell'Ateneo</h1>
<p><a href="logout">Esci</a></p>
<p><a href="elenco_studenti">Elenco degli studenti</a></p>
<% 
	Studente studente = (Studente)session.getAttribute("modifica_studente");

	if(studente == null) {
%>
<h2>Studente non trovato</h2>
<% 
	} else {
%>
<form action="fai_modifica_studente" method="post">
<p>Matricola: <input type="text" name="matricola" value="<%=studente.getMatricola() %>" readonly="readonly" /></p>
<p>Cognome: <input id="cognome" type="text" name="cognome" value="<%=studente.getCognome() %>" onchange="controllaParametri()" /></p>
<p>Nome: <input id="nome" type="text" name="nome" value="<%=studente.getNome() %>" onchange="controllaParametri()" /></p>
<p><input id="invia" type="submit" value="Invia" disabled="disabled"/></p>
</form>
<% 
	}
%>
</body>
</html>