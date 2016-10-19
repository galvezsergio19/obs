<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Automated Teller Machine</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet"> <!-- Custom CSS -->
    
</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	            <h1 class="title">ATM Interface</h1>
            </div> <!-- /.navbar-collapse -->
        </div> <!-- /.container -->
    </nav>
  

    <!-- Page Content -->
    <div class="container" style="padding-top:80px;">
    	<br /><br />
        <div class="row">
                <div class="col-lg-6">
                	<div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-user" style="font-size: 5em;"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">Create</div>
                                    <div>Physical ATM Account Number</div>
                                </div>
                            </div>
                        </div>
                        <a href="#" data-toggle="modal" data-target="#myModalReg">
                            <div class="panel-footer">
                                <span class="pull-left">Click here to proceed</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                    
                	<hr />
                
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-check" style="font-size: 5em;"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">Verify</div>
                                    <div>Online Registration</div>
                                </div>
                            </div>
                        </div>
                        <a href="#" data-toggle="modal" data-target="#myModalVerify">
                            <div class="panel-footer">
                                <span class="pull-left">Click here to proceed</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                    
                    <hr />
                
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-link" style="font-size: 5em;"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">Create Joint Account</div>
                                    <div>Use this to link Another Account.</div>
                                </div>
                            </div>
                        </div>
                        <a href="#" data-toggle="modal" data-target="#myModalEnrol">
                            <div class="panel-footer">
                                <span class="pull-left">Click here to proceed</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                        
                         <!-- Modal -->
                        <div class="modal fade" id="myModalVerify" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">Verify Online Registration</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form method="post" action="atm">
				                            <fieldset>
				                            	<input type="hidden" name="form-name" value="Verify">
				                                <div class="form-group">
				                                	<input class="form-control" placeholder="Account Number" name="account_no" 
				                                	type="number" min="100000000000" max="999999999999" autofocus required>
				                                </div>
				                                <div class="form-group">
				                                    <input class="form-control" placeholder="Verification Code from Web" name="web_verify" 
				                                    type="number" type="number" min="1000000000" max="9999999999" required>
				                                </div>
				                                <button type="submit" class="btn btn-primary">Submit</button>
				                            </fieldset>
				                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                                <!-- /.modal-content -->
                            </div>
                            <!-- /.modal-dialog -->
                        </div>
                        <!-- /.modal -->
                        
                        
                        <!-- Modal for Registration -->
                        <div class="modal fade" id="myModalReg" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">Create new Physical ATM Card</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form method="post" action="atm">
				                            <fieldset>
				                            	<input type="hidden" name="form-name" value="Register">
				                                <div class="form-group">
				                                	<input class="form-control" placeholder="Account Number" name="account_no" 
				                                	type="number" min="100000000000" max="999999999999" autofocus required>
				                                </div>
				                                <div class="form-group">
				                                    <input class="form-control" placeholder="Joint Account Indicator" name="jai" 
				                                    type="number" type="number" min="01" max="99" required>
				                                </div>
				                                <div class="form-group">
				                                	<input class="form-control" placeholder="Initial Balance (must be 500 or above)" name="balance" 
				                                	type="number" min="500" max="999999999999" required>
				                                </div>
				                                <button type="submit" class="btn btn-primary">Submit</button>
				                            </fieldset>
				                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                                <!-- /.modal-content -->
                            </div>
                            <!-- /.modal-dialog -->
                        </div>
                        <!-- /.modal -->
                        
                        
                        <!-- Modal for Enrollment -->
                        <div class="modal fade" id="myModalEnrol" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">Create new Joint Account</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form method="post" action="atm">
				                            <fieldset>
				                            	<input type="hidden" name="form-name" value="Enrollment">
				                            	<div class="well well-sm">
				                            		<label>Primary Card Details</label>
					                                <div class="form-group">
					                                	<input class="form-control" placeholder="Primary Account Number" name="primary_account_no" 
					                                	type="number" min="100000000000" max="999999999999" autofocus required>
					                                </div>
					                                <div class="form-group">
					                                    <input class="form-control" placeholder="Primary Account Indicator" name="primary_account_jai" 
					                                    type="number" type="number" min="01" max="99" required>
					                                </div>
												</div>
												<div class="well well-sm">
				                            		<label>Joint Card Details</label>
					                                <div class="form-group">
					                                	<input class="form-control" placeholder="Joint Account Number" name="joint_account_no" 
					                                	type="number" min="100000000000" max="999999999999" autofocus required>
					                                </div>
					                                <div class="form-group">
					                                    <input class="form-control" placeholder="Joint Account Indicator" name="joint_account_jai" 
					                                    type="number" type="number" min="01" max="99" required>
					                                </div>
					                            </div>
				                                <button type="submit" class="btn btn-primary">Submit</button>
				                            </fieldset>
				                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                                <!-- /.modal-content -->
                            </div>
                            <!-- /.modal-dialog -->
                        </div>
                        <!-- /.modal -->
                        
                        
                </div>
                <div class="col-lg-6">
                
                	<!--  MESSAGE -->
                	<div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-info-sign" style="font-size: 5em;"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><!-- The verification Code -->
                                    	0000000000
                                    </div>
                                    <div>
                                    	<c:choose>
		                                	<c:when test="${requestScope.status eq 'fail'}">
		                                		${requestScope.displayMessageReg}
				                            </c:when>
				                            <c:when test="${requestScope.status eq 'success'}">
				                            	${requestScope.displayMessageReg}
				                            </c:when>
				                            <c:otherwise>
				                            	Information Message
				                            </c:otherwise>
		                                </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <span class="pull-left">Proceed to the Customer Registration Online to proceed.</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                    
                    <hr />
                
                	<!--  VERIFICATION CODE -->
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-barcode" style="font-size: 5em;"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><!-- The verification Code -->
                                    	${requestScope.atm_verification!=null?requestScope.atm_verification:'0000000000'}
                                    </div>
                                    <div>
                                    	${requestScope.displayMessage!=null?requestScope.displayMessage:'Verification Code'}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <span class="pull-left">Enter Verification above to proceed to your Online Registration.</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                    
                    <hr />
                
                	<!--  ENROLLMENT CODE -->
                    <div class="panel panel-danger">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-tags" style="font-size: 5em;"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><!-- The verification Code -->
                                    	${requestScope.enrol_verification!=null?requestScope.enrol_verification:'0000000000'}
                                    </div>
                                    <div>
                                    	${requestScope.displayMessageEnrol!=null?requestScope.displayMessageEnrol:'Verification Code'}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <span class="pull-left">Enter Verification above to proceed to your Online Account Enrollment.</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                    
                    
                </div>
            </div>
        <br /><br /><br />
        <nav class="navbar navbar-inverse navbar-fixed-bottom">
	        <div class="container">
	            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		            <h1 class="title">ATM Interface</h1>
	            </div> <!-- /.navbar-collapse -->
	        </div> <!-- /.container -->
	    </nav>
    </div> <!-- /.container -->

    <script src="js/jquery.js"></script> <!-- jQuery -->
    <script src="js/bootstrap.js"></script> <!-- Bootstrap Core JavaScript -->

</body>

</html>
