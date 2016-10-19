<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Online Banking System - Transaction"></c:set>
<%@ include file="common/header.jspf" %>
    <div id="wrapper">
		<c:set var="isBalanceActive" scope="request" value="active"></c:set>
		<%@ include file="common/sidebar.jspf" %>
        <div id="page-wrapper">
            <div class="col-lg-12">
                <h1 class="page-header">Transaction Report</h1>
            </div>
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
	                   	<h4><span class="glyphicon glyphicon-user"></span>
	                        Transaction Report</h4>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="balance">
							<div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
	                            <!--  Table Filter -->
	                            <div class="row">
	                            	<div class="col-sm-5">
	                            		<div style="margin-bottom: 5px;">
		                            		<label><input type="checkbox" value=""></input>  Filter by Date  </label>
		                            		<select name="dataTables-example_length" class="form-control column_filter" 
		                            		name="transaction" id="transaction" onChange="check();">
			                            		<option value="today">Transaction for today</option>
			                            		<option value="60days">Transaction for Last 60 Days</option>
			                            		<option value="Speicific">Transaction for Specific Period</option>
		                            		</select>
			                            </div>
			                            <div style="margin-bottom: 5px;">
			                            	<label><input type="checkbox" value=""></input>  Filter by Data  </label>  
		                            		<select name="dataTables-example_length" class="form-control" name="filter">
			                            		<option value="receiver">Filter by Receiver Account Number</option>
			                            		<option value="sender">Filter by Sender Account Number</option>
			                            		<option value="trans_type">Filter by Transaction Type</option>
			                            		<option value="shop_name">Filter by Shop Name</option>
			                            		<option value="atm_ref">Filter by ATM Reference ID</option>
		                            		</select>
		                            	</div>
		                            </div>
		                            <div class="col-sm-6">
		                            	<div style="margin-bottom: 5px;">
		                            		<label>Date From: </label><input type="date" class="form-control" id="dateFrom"/>
			                            	<label>Date To: </label><input type="date" class="form-control" id="dateTo"/>
			                            </div>
			                            <div style="margin-bottom: 5px;">
			                            	<label>Account Number: </label>
			                            	<select name="dataTables-example_length" class="form-control" name="filter">
			                            		<option value="receiver">Filter by Receiver Account Number</option>
			                            		<option value="sender">Filter by Sender Account Number</option>
			                            		<option value="trans_type">Filter by Transaction Type</option>
			                            		<option value="shop_name">Filter by Shop Name</option>
			                            		<option value="atm_ref">Filter by ATM Reference ID</option>
		                            		</select>
		                            	</div>
		                          </div>
		                          <div class="col-sm-1">
		                          	<input type="submit" value="Filter" class="btn btn-primary"/>
		                          </div>
	                         </div>
	                         <hr/>
                            <!--  Table Body -->
                            <div class="row">
                            	<div class="col-sm-12 dataTable_wrapper">
                            		<table class="table table-striped table-bordered table-hover" id="dataTables-transaction"> 
			                            <thead> <!--  TABLE HEADER  -->
			                                <tr>
								                <th colspan="2">Transaction</th>
								                <th colspan="2">Balance</th>
								                <th colspan="2">Account Number</th>
								                <th colspan="2">Reference</th>
								            </tr>
			                                <tr>
			                                	<th style="width: 20%;">Date</th>
			                                	<th style="width: 10%;">Type</th>
			                                	<th style="width: 16%;">Before</th>
			                                	<th style="width: 16%;">After</th>
			                                	<th style="width: 12%">Receiver</th>
			                                	<th style="width: 12%;">Sender</th>
			                                	<th style="width: 12%;">Shop</th>
			                                	<th style="width: 12%;">ATM</th>
			                                </tr>
			                            </thead>
			                            <tbody> <!--  TABLE BODY -->
			                            	<c:forEach var="transaction" items="${requestScope.accountsTransaction}">
				                            	<tr class="gradeA odd">
					                            	<td class="sorting_1">${transaction.transaction_date}</td>
													<!-- Transaction Type -->
													<c:choose>
					                            		<c:when test="${transaction.transation_type eq 'S'}">
					                            			<c:set var="transaction_type" value="T-Send" scope="page"></c:set>
					                            		</c:when>
					                            		<c:when test="${transaction.transation_type eq 'R'}">
					                            			<c:set var="transaction_type" value="T-Receive" scope="page"></c:set>
					                            		</c:when>
					                            	</c:choose>
					                            	
													<td class="tooltip-demo">
														 ${pageScope.transaction_type}
													</td>
					                               	<td>${transaction.balance_before}</td>
					                               	<td>${transaction.balance_after}</td>
					                               	<td>${transaction.receiver_account_no}</td>
					                               	<td>${transaction.sender_account_no}</td>
					                               	<td>${transaction.shop_name}</td>
					                               	<td>${transaction.atm_ref_id}</td>
				                           		</tr>
				                           	</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
                            </div>
                        </form>
                    </div>
                    <div class="panel-footer">
                    	<a href="balance">
	                    	<i class="glyphicon glyphicon-circle-arrow-left"></i> Back to Balance Page.
	                  	</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script type="text/javascript">
	function check() {
		var utc = new Date().toJSON().slice(0,10);
		if (document.getElementById("transaction").selectedIndex=="0") {
			document.getElementById("dateFrom").value=utc;
			document.getElementById("dateTo").value=utc;
		}
	}
</script>
<%@ include file="common/footer.jspf" %>