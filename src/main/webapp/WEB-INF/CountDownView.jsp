<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>CountDown</title>
	<style>
	blockquote {
		font-style: italic;
		padding: 20px;
	}

	blockquote footer{
		color:#555;
		font-weight: bold
	}
	</style>
</head>

<body>
	<blockquote>
	&laquo;
	<% 
            String diff = (String) request.getAttribute("diff");
            out.println( diff );
	%>
	 left before the end of this lecture! &raquo;
	<footer>
	<% 
            String parametre =  request.getParameter( "author" );
            out.println( parametre!=null?parametre:"Nobody" );
            //http://localhost:8080/CountDownWebApp/countdown?author=Yoann
	%>
	</footer>
	</blockquote>
</body>
</html>