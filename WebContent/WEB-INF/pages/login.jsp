<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Online Banking System - Login"></c:set>
<%@ include file="common/header.jspf" %>
	<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
            <a href="index.html">
            	<span class="glyphicon glyphicon-fire logo"></span>
            </a>
                <div class="login-panel panel panel-default" style="margin-top: 20px;">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="login">
                            <fieldset>
                                <div class="form-group input-group">
                                	<span class="input-group-addon">U</span>
                                    <input class="form-control" placeholder="User Name" name="username" 
                                    type="text" maxLength="7" autofocus required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" 
                                    type="password" maxLength="20" value="" required>
                                </div>
                                
                                <!-- Error Display -->
                                <c:choose>
                                	<c:when test="${requestScope.isError eq true}">
                                		<div class="alert alert-danger alert-dismissable">
		                               		<button type="button" class="close" data-dismiss="alert">×</button>
		                                	${requestScope.displayMessage}
		                            	</div>
		                            </c:when>
		                            <c:otherwise></c:otherwise>
                                </c:choose>
                                
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Login"/>
                            </fieldset>
                        </form>
                    </div>
                    <div class="panel-default">
                    	<div class="panel-heading">
                    		<a href=register>Register Now.</a><br />
			       			<a href="verify">Verify Account Here.</a>
                    	</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@ include file="common/footer.jspf" %>	