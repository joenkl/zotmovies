<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="_dashboard-header.jsp"%>
<body>
    <!-- Page Content -->
    <c:if test="${empty isAdmin}">
    	<c:redirect url="_dashboard-login"/>
    </c:if>
    
    <div class="container">

        <!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">MetaData
                    <!-- <small>Secondary Text</small> -->
                </h1>
            </div>
        </div>
        <!-- /.row -->

  
		
		 <div class="row">
            <div class="col-lg-12">
            	<span style="white-space : pre"><c:out value ="${metaData}"/></span>
            </div>
        </div>
		

        <!-- Footer -->
        <%@ include file="footer.jsp" %>

    </div>
    <!-- /.container -->

 

</body>

</html>
