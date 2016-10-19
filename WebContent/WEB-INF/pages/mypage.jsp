<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Online Banking System - My Page"></c:set>
<%@ include file="common/header.jspf" %>

    <div id="wrapper">
		<c:set var="isMyPageActive" scope="request" value="active"></c:set>
		<%@ include file="common/sidebar.jspf" %>
        <div id="page-wrapper">
            <div class="col-lg-12">
                <h1 class="page-header">My Page</h1>
            </div>
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                    	<h4><span class="glyphicon glyphicon-time"></span>
                           Online Check</h4>
                    </div>
                    <div class="panel-body">
                        <p> Welcome Back! ${requestScope.Full_name} <br />
                        Last Login Date and Time : ${requestScope.Last_Login}
                        </p>
                    </div>
                    <div class="panel-footer">
                        - 
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- /#wrapper -->

<%@ include file="common/footer.jspf" %>