<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Online Banking System - User Account"></c:set>
<%@ include file="common/header.jspf" %>
	<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">User Account Creation</h3>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="user">
                            <fieldset ${requestScope.isFieldSetEnabled}>
                            	<div class="form-group input-group">
                                	<span class="input-group-addon">U</span>
                                	<input type="text" class="form-control" placeholder="User Name" name="username"
                                	type="text" maxLength="7" autofocus required>
                                	<p class="help-block">Enter username without space.</p>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" 
                                    type="password" maxLength="20" value="" required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Repeat Password" name="repeat_password" 
                                    type="password" maxLength="20" value="" required>
                                </div>
                                
                                <!-- Error Display -->
                                <c:choose>
                                	<c:when test="${requestScope.status eq 'fail'}">
                                		<div class="alert alert-danger alert-dismissable">
		                               		<button type="button" class="close" data-dismiss="alert">×</button>
		                                	${requestScope.displayMessage}
		                            	</div>
		                            </c:when>
		                            <c:when test="${requestScope.status eq 'success'}">
		                            	<div class="alert alert-success alert-dismissable">
			                                <button type="button" class="close" data-dismiss="alert">×</button>
			                                ${requestScope.displayMessage}
			                            </div>
		                            </c:when>
		                            <c:otherwise></c:otherwise>
                                </c:choose>
                                
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Create">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@ include file="common/footer.jspf" %>