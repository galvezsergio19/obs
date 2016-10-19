<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Online Banking System - Balance"></c:set>
<%@ include file="common/header.jspf" %>

    <div id="wrapper">
		<c:set var="isBalanceActive" scope="request" value="active"></c:set>
		<%@ include file="common/sidebar.jspf" %>
        <div id="page-wrapper">
            <div class="col-lg-12">
                <h1 class="page-header">Balance Inquiry</h1>
            </div>
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                    	<div class="pull-right">
                    		<h4><span class="glyphicon glyphicon-dashboard"></span>
                       		Date and Time: ${requestScope.today}
                       		</h4>
                    	</div>
	                   	<h4><span class="glyphicon glyphicon-user"></span>
	                        List of Enrolled Accounts
	                   	</h4>
                    </div>
                    <div class="panel-body">
						<div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                            <!--  Table Body -->
                            <div class="row">
                            	<div class="col-sm-12">
                           			<form method="post" action="transaction">
	                            		<table class="table table-striped table-bordered table-hover dataTable no-footer" id="dataTables-balance">
				                            <thead> <!--  TABLE HEADER  -->
				                                <tr>
				                                	<th tabindex="0" rowspan="1" colspan="1" style="width: 187px;">Enrolled Accounts</th>
				                                	<th tabindex="0" rowspan="1" colspan="1" style="width: 187px;">Preferred Name</th>
				                                	<th tabindex="0" rowspan="1" colspan="1" style="width: 222px;">Available Balance</th>
				                                	<th tabindex="0" rowspan="1" colspan="1" style="width: 222px;">Last Transaction Date</th>
				                                </tr>
				                            </thead>
				                            <tbody> <!--  TABLE BODY -->
				                            	<c:forEach var="account" items="${requestScope.accountsBalance}">
					                            	<tr class="gradeA odd">
						                            	<td class="sorting_1" style="padding-left: 25px;">
						                            		<p style="margin-bottom: 0px; vertical-align: middle;" >
						                            			<button type="submit" class="btn btn-outline btn-link" 
						                            			value="${account.account_no}" name="account_no">
						                            				<strong>${account.account_no}</strong>
						                            			</button>
						                            		</p> 
														</td>
														<td style="padding-left: 25px; vertical-align: middle;">
						                               		<p style="margin-bottom: 0px;">${account.preffered_name}</p>
						                               	</td>
														<td style="padding-left: 25px; vertical-align: middle;">
						                               		<!-- p style="margin-bottom: 0px;">${account.balance}</p-->
						                               		<p style="margin-bottom: 0px;">${account.balance}</p>
						                               	</td>
						                               <td style="padding-left: 25px; vertical-align: middle;">
						                               		<p style="margin-bottom: 0px;">
						                               			${account.last_transaction_date ne null?account.last_transaction_date:'None'}
						                               		</p>
						                               </td>
					                           		</tr>
					                           	</c:forEach>
											</tbody>
										</table>
									</form>
								</div>
							</div>
						</div>
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