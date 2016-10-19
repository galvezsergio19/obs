<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SMS Interface Simulator</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet"> <!-- Custom CSS -->
    
</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	            <h1 class="title">SMS Interface</h1>
            </div> <!-- /.navbar-collapse -->
        </div> <!-- /.container -->
    </nav>
  

    <!-- Page Content -->
    <div class="container" style="padding-top:80px;">
    	<br /><br /><br /><br />
        <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-check" style="font-size: 5em;"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">Sms</div>
                                    <div>Transaction Authorization Code Verification</div>
                                </div>
                            </div>
                        </div>
                        <a href="#" data-toggle="modal" data-target="#myModal">
                            <div class="panel-footer">
                                <span class="pull-left">Click here to proceed</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                        
                         <!-- Modal -->
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">Verify Online Registration</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form method="post" action="sms">
				                            <fieldset>
				                                <div class="form-group">
				                                	<input class="form-control" placeholder="Account Number" name="account_no" 
				                                	type="number" min="100000000000" max="999999999999" autofocus required>
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
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-barcode" style="font-size: 5em;"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><!-- The TAC -->
                                    	${requestScope.sms_tac!=null?requestScope.sms_tac:'0000000000'}
                                    </div>
                                    <div>
                                    	${requestScope.displayMessage!=null?requestScope.displayMessage:'Transaction Auth Code'}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <span class="pull-left">Enter TAC above to proceed to your Fund Transfer.</span>
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
		            <h1 class="title">SMS Interface</h1>
	            </div> <!-- /.navbar-collapse -->
	        </div> <!-- /.container -->
	    </nav>
    </div> <!-- /.container -->

    <script src="js/jquery.js"></script> <!-- jQuery -->
    <script src="js/bootstrap.js"></script> <!-- Bootstrap Core JavaScript -->

</body>

</html>
