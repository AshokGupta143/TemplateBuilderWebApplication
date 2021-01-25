<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>TemplateBuilder</title>
 <link href="${pageContext.request.contextPath}/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
 <script src="${pageContext.request.contextPath}/webjars/jquery/1.9.1/jquery.min.js"></script>
 <link href="${pageContext.request.contextPath}/assets/sass/custom.css" rel="stylesheet" />

 
 <script>
 function isDelete(){
	 var isDeleteTemplate = confirm("Are you sure, you want to delete template ?");
	 return isDeleteTemplate;
 }
 function newForm(){
	 window.sessionStorage.setItem("formData",'');
	 window.sessionStorage.setItem("docid",'');
	window.sessionStorage.setItem("revid",'');
	window.sessionStorage.setItem("templatename",'');
 }
 function editForm(docid,revid,name){
	 var url = "/EnforcementWebApplication/getTemplateMetaData";

	 var data = new FormData();
	 data.append('docid', docid);
	 data.append('revid', revid);
	 var metadata = "";
	 $.ajax({
         type: "POST",
         enctype: 'multipart/form-data',
         url: url,
         data: data,
         processData: false, //prevent jQuery from automatically transforming the data into a query string
         contentType: false,
         cache: false,
         success: function(response){
        	 metadata = response;
			 //alert(response);
			 // window.sessionStorage.setItem("formData",'');
	 		window.sessionStorage.setItem("formData",metadata);
			window.sessionStorage.setItem("docid",docid);
			window.sessionStorage.setItem("revid",revid);
			window.sessionStorage.setItem("templatename",name);
			window.location.href = "/EnforcementWebApplication/templateBuilder";
             //location.reload();
			},
         error: function(response) {
             alert('Error: Something went wrong.'+response); // @text = response error, it is will be errors: 324, 500, 404 or anythings else
            // location.reload();
         }

     })
	 
	
 }
 </script>
 
</head>
<body>

	<div class="container ">
	   
    <h1 class="formbuilder-title">Town Of Caledon  </h1>
	<h2 class="formbuilder-title">Form Builder System For mobiINSPECT </h2>
     <ul class="navigation">	
	 <li>
	 <a class="btn btn-primarys" href="${pageContext.request.contextPath}/logout">Logout</a>
	 </li>
	 <li>
	 <a class="btn btn-primarys" onclick="newForm()" href="/EnforcementWebApplication/templateBuilder">Add New Form</a>
	 </li>	
     </ul>
 <div class="pageheader" >Template Listing</div>
    </div>
   	 <div class="container">
	    
        <table class="table table-striped table-bordered">
            <thead>
               <tr>
                   	<th>Template Name</th>
					<th>Actions</th>
                </tr>
                
            </thead>
            <tbody>
            	<c:forEach var="template" items="${templates}">
					<tr>
						<td>${template.name}</td>
						<td>
							<a class="btn btn-warning" onclick="editForm('${template._id}','${template._rev}','${template.name}')" >Edit</a>
							<a class="btn btn-warning" href="${pageContext.request.contextPath}/deleteTemplate/${template._id}/${template._rev}" onClick="return isDelete();">Delete</a>	
						</td>
					</tr>
				</c:forEach>
                    
            </tbody>
        </table>
    </div>

</body>
</html>