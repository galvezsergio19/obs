<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Online Banking System - Fund Transfer"></c:set>
<%@ include file="common/header.jspf" %>

    <div id="wrapper">
		<c:set var="isFundActive" scope="request" value="active"></c:set>
		<%@ include file="common/sidebar.jspf" %>
        <div id="page-wrapper">
            <div class="col-lg-12">
                <h1 class="page-header">Fund Transfer</h1>
            </div>
            <div class="col-lg-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                    	<h4><span class="glyphicon glyphicon-transfer"></span>
                           Fund Transfer Form</h4>
                    </div>
                    <div class="panel-body">
                       <form method="post" action="">
							<fieldset ${requestScope.isFieldSetEnabled}>
                            	<div class="form-group">
                                   <label>Account Number From: </label>
                                   <select class="form-control" name="account_no_from">
                                       <c:forEach var="account" items="${requestScope.Account_Number_Options}">
                                       		<option ${account eq requestScope.Account_Number?'selected':''}>
												<c:out value="${account}"></c:out>
											</option>
										</c:forEach>
                                   </select>
                                   <p class="help-block">This field will be used for the generation of TAC.</p>
                               	</div>
                               	<div class="form-group">
                               		<label>Account Number To: </label>
                                	<input class="form-control" placeholder="Account Number To" name="account_no_to" 
                                	type="number" min="100000000000" max="999999999999" 
                                	value="${requestScope.FundModel.account_no_to!=0?
                                	requestScope.FundModel.account_no_to:null}" ${requestScope.isFieldRequired}>
                                </div>
                                <div class="form-group">
                                	<label>Amount to Transfer: </label>
                                	<input class="form-control" placeholder="Amount to transfer" name="amount" 
                                	type="number" min="500" max="999999999999" 
                                	value="${requestScope.FundModel.amount!=0?
                                	requestScope.FundModel.amount:null}" ${requestScope.isFieldRequired}>
                                </div>
                                <input type="submit" class="btn btn-lg btn-success btn-block" name="Submit"
                                value="Send TAC" ${requestScope.isButtonEnabled eq ''?'':'disabled'}>
                                
                                <!--  Display Not for SMS TAC -->
                                <c:if test="${requestScope.isButtonEnabled eq 'disabled'}">
                                	<br />
	                                <div class="alert alert-danger alert-dismissable">
	                               		<button type="button" class="close" data-dismiss="alert">×</button>
	                                	Please verify the transaction using the sent TAC in the SMS Interface.
		                            </div>
				                </c:if>
                                
                                <br />
                                <div class="form-group">
                                	<label>TAC From SMS: </label>
                                	<input class="form-control" placeholder="Transaction Authorization Code" name="tac" 
                                	type="number" min="1000000000" max="9999999999" 
                                	value="${requestScope.FundModel.tac!=0?
                                	requestScope.FundModel.tac:null}" ${requestScope.isFieldRequired} 
                                	${requestScope.isButtonEnabled eq ''?'disabled':''}>
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
                                
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Transfer" 
                                name="Submit" ${requestScope.isButtonEnabled eq ''?'disabled':''}>
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