<%@ page session="false"%>

User '<%=request.getRemoteUser()%>' has been logged out.

<% 
	Cookie[] array= request.getCookies(); 
	for(int i=0; i<array.length; i++) {
		if(array[i].getName().equals("JSESSIONID") || array[i].getName().equals("LtpaToken2")) {
			out.println("<br/>");
			out.println("Name of the cookie : " + array[i].getName() + "<br/>");
			out.println("Value in cookie : " + array[i].getValue());

			//Deleting the cookie
			array[i].setMaxAge(0);
			response.addCookie(array[i]); 
			//After this, although cookie will be not be visible from browser's memory
			//but Cookie data will still be visible until you refresh this page.
		}
	}
%>

<br/><br/>
<a href="${pageContext.request.contextPath}/">Click here to login</a>