<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Online Banking System - Verification"></c:set>
<%@ include file="common/header.jspf" %>
	<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Verification Page</h3>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="verify">
                            <fieldset ${requestScope.isFieldSetEnabled}>
                            	<div class="form-group">
                                	<input class="form-control" placeholder="Preferred Account Name" name="preffered_name" 
                                	type="text" maxLength="150" value="${requestScope.VerificationModel.preferredName!=null?
                                	requestScope.VerificationModel.preferredName:''}" autofocus required>
                                </div>
                                <div class="form-group">
                                	<input class="form-control" placeholder="Account Number" name="account_no" 
                                	type="number" min="100000000000" max="999999999999" 
                                	value="${requestScope.VerificationModel.account_no!=0?
                                	requestScope.VerificationModel.account_no:''}" required>
                                </div>	
                                <div class="form-group">
                                    <input class="form-control" placeholder="Joint Account Indicator" name="jai" 
                                    type="number" type="number" min="01" max="99" 
                                    value="${requestScope.VerificationModel.jai!=0?
                                	requestScope.VerificationModel.jai:''}" required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Full Name" name="full_name" 
                                    type="text" maxLength="150" value="${requestScope.VerificationModel.full_name!=null?
                                	requestScope.VerificationModel.full_name:''}" required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Birthday" name="birthday" 
                                    type="date" value="${requestScope.VerificationModel.birthday!=null?
                                	requestScope.VerificationModel.birthday:''}" required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Verification Code from ATM" name="verification_code" 
                                    type="number" type="number" min="1000000000" max="9999999999" 
                                    value="${requestScope.VerificationModel.atm_verification_code!=0?
                                	requestScope.VerificationModel.atm_verification_code:''}" required>
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
		                            	<c:set var="customer_id" value="${requestScope.customer_id}" scope="session"></c:set>
		                            	<div class="alert alert-success alert-dismissable">
			                                <button type="button" class="close" data-dismiss="alert">×</button>
			                                ${requestScope.displayMessage}
			                            </div>
		                            </c:when>
		                            <c:otherwise></c:otherwise>
                                </c:choose>
                                
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Verify">
                            </fieldset>
                        </form>
                    </div>
                    <c:if test="${requestScope.status eq 'fail'}">
	                     <div class="panel-default">
	                    	<div class="panel-heading">
	                    		<a href="login">Back to Login Page</a>
	                    	</div>
	                    </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
<%@ include file="common/footer.jspf" %>