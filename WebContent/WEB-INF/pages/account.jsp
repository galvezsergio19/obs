<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Online Banking System - Account Enrollement"></c:set>
<%@ include file="common/header.jspf" %>
    <div id="wrapper">
    	<c:set var="isAccountActive" scope="request" value="active"></c:set>
		<%@ include file="common/sidebar.jspf" %>
        <div id="page-wrapper">
            <div class="col-lg-12">
                <h1 class="page-header">Account Enrollment</h1>
            </div>
            <div class="col-lg-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                    	<h4><span class="glyphicon glyphicon-user"></span>
                           Account Enrollment Form</h4>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="account">
                            <fieldset ${requestScope.status eq 'success'?'disabled':''}>
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
                                
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Enroll">
                            </fieldset>
                        </form>
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