<html>
<body>
<h2>Simple OAuth Consumer</h2>
<a href="./notes">Get Notes</a> | <a href="./user">Get User Info</a> | <a href="./logout">Logout</a><br/>

<% 
	String notesJson = (String)session.getAttribute("notes");

	if (notesJson != null) {
		out.println("<pre>" + notesJson + "</pre>");
	}

	String userJson = (String)session.getAttribute("user");

	if (userJson != null) {
		out.println("<pre>" + userJson + "</pre>");
	}
%>
</body>
</html>
