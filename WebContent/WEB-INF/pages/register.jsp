<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Online Banking System - Register"></c:set>
<%@ include file="common/header.jspf" %>
	<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Registration Page</h3>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="register">
                            <fieldset ${requestScope.isFieldSetEnabled}>
                                <div class="form-group">
                                	<input class="form-control" placeholder="Account Number" name="account_no" 
                                	type="number" min="100000000000" max="999999999999" 
                                	value="${requestScope.RegistrationModel.account_no!=0?
                                	requestScope.RegistrationModel.account_no:null}" autofocus required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Joint Account Indicator" name="jai" 
                                    type="number" type="number" min="01" max="99" 
                                    value="${requestScope.RegistrationModel.jai!=0?
                                	requestScope.RegistrationModel.jai:''}" required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Full Name" name="full_name" 
                                    type="text" maxLength="150" value="${requestScope.RegistrationModel.full_name!=null?
                                	requestScope.RegistrationModel.full_name:''}" required>
                                </div>
                                
                                <div class="form-group">
                                    <input class="form-control" placeholder="Birthday" name="birthday"
                                    type="date"  value="${requestScope.RegistrationModel.full_name!=null?
                                	requestScope.RegistrationModel.birthday:''}" required>
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
			                                <strong>${requestScope.verification_code}</strong><br />
			                                ${requestScope.displayMessage}
			                            </div>
		                            </c:when>
		                            <c:otherwise></c:otherwise>
                                </c:choose>
                                
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Register">
                            </fieldset>
                        </form>
                    </div>
                    <div class="panel-default">
                    	<div class="panel-heading">
                    		<a href="login">Back to Login Page</a>
                    	</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@ include file="common/footer.jspf" %>
	