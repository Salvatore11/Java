<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
<form action="nuovo_studente" method="post">
<p>Cognome: <input id="cognome" type="text" name="cognome" value="" onchange="controllaParametri()" /></p>
<p>Nome: <input id="nome" type="text" name="nome" value="" onchange="controllaParametri()" /></p>
<p><input id="invia" type="submit" value="Invia" disabled="disabled" /></p>
</form>
</body>
</html>