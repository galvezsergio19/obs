<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Online Banking System - Profile"></c:set>
<%@ include file="common/header.jspf" %>
<script type="text/javascript">

//Profile Field Hidden Field
function displaySalutationField(radio){
	if (radio.value=='Others') {
		document.getElementById('other_salutation').type = 'text';
		document.getElementById('other_salutation').setAttribute("required", "true");
	} else {
		document.getElementById('other_salutation').value = '';
		document.getElementById('other_salutation').type = 'hidden';
	}
}
		
function displayRaceField(dropdown){
	if (dropdown.value=='Others') {
		document.getElementById('other_race').type = 'text';
		document.getElementById('other_race').setAttribute("required", "true");
	} else {
		document.getElementById('other_race').value = '';
		document.getElementById('other_race').type = 'hidden';
	}
}
</script>

    <div id="wrapper">
		<c:set var="isProfileActive" scope="request" value="active"></c:set>
		<%@ include file="common/sidebar.jspf" %>
        <div id="page-wrapper">
            <div class="col-lg-12">
                <h1 class="page-header">View / Edit Profile</h1>
            </div>
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                    	<h4><span class="glyphicon glyphicon-check"></span>
                        Profile Form</h4>
                    </div>
	                    <div class="panel-body">
							<form method="post" action="profile">
								<fieldset>
									<div class="container">
										<input type="submit" class="btn btn-outline btn-primary btn-lg" value="View" name="Submit"
										style="width: 12%;" ${requestScope.isView?'disabled':''}>
                                        <input type="submit" class="btn btn-outline btn-primary btn-lg" value="Edit" name="Submit"
                                        style="width: 12%;" ${!requestScope.isView?'disabled':''}>
                                        <!-- Error Display -->
		                                <c:choose>
		                                	<c:when test="${requestScope.status eq 'fail'}">
		                                		<div class="alert alert-danger alert-dismissable" style="display: inline;">
				                               		<button type="button" class="close" data-dismiss="alert">×</button>
				                                	${requestScope.displayMessage}
				                            	</div>
				                            </c:when>
				                            <c:when test="${requestScope.status eq 'success'}">
				                            	<div class="alert alert-success alert-dismissable" style="display: inline;">
					                                <button type="button" class="close" data-dismiss="alert">×</button>
					                                Customer Profile Successfully Updated.
					                            </div>
				                            </c:when>
				                            <c:otherwise></c:otherwise>
		                                </c:choose>
									</div>
								</fieldset>
							</form>
							<form method="post" action="profile">
								<fieldset>
									<hr/>
									<c:set var="CustomerModel" value="${requestScope.CustomerModel}" scope="request"></c:set>
	                            	<div class="col-lg-4">
	                            		<div class="form-group">
		                            		<div class="well well-sm">
		                            			<label  style="display: block;">Customer ID : </label>
		                            			<p style="font-size: 2em;">${sessionScope.customer_id}</p>
		                            			<c:if test="${requestScope.CustomerModel.national_id ne null && 
		                            			requestScope.CustomerModel.national_id ne ''}">
		                            				<label  style="display: block;">National ID : </label>
		                            				<p>${requestScope.CustomerModel.national_id}</p>
		                            			</c:if>
		                            		</div>
		                                </div>
		                                <label>Salutation</label>
		                                <div class="form-group">
                                            <c:forEach var="salutation" items="${requestScope.salutation}">
                                            	<label class="radio-inline">
                                                	<input type="radio" name="salutation" id="salutation" value="${salutation}"
                                                	${requestScope.isView?'disabled':''}
                                                	${salutation eq requestScope.CustomerModel.salutation?'checked':''}
                                                	onchange="displaySalutationField(this);">
                                                		${salutation}
                                            	</label>
                                            </c:forEach>
                                        </div>
                                        <div class="form-group">
		                                    <input class="form-control" placeholder="Other Salutation" name="other_salutation" 
		                                    id="other_salutation" value="${requestScope.CustomerModel.other_salutation}"
		                                    type="${requestScope.CustomerModel.salutation eq 'Others' ?'text':'hidden'}" maxLength="10" 
		                                    ${requestScope.isView?'readonly':''} ${!requestScope.isView?'required':''}>
		                                </div>
		                                <div class="form-group"><label>Full Name</label>
		                                    <input class="form-control" placeholder="Full Name" name="full_name" 
		                                    type="text" maxLength="150" value="${requestScope.CustomerModel.full_name ne null?
		                                    requestScope.CustomerModel.full_name:''}" ${requestScope.isView?'readonly':''}
		                                    ${!requestScope.isView?'required':''}>
		                                </div>
		                                <div class="form-group"><label>Birthday</label>
		                                    <input class="form-control" placeholder="Birthday" name="birthday" 
		                                    type="date" value="${requestScope.CustomerModel.birthday ne null?
		                                    requestScope.CustomerModel.birthday:''}" readonly>
		                                </div>
		                            </div>
		                            <div class="col-lg-4">
		                            	<div class="form-group"><label>Marital Status</label>
		                                   <select class="form-control" name="marital_stat" ${requestScope.isView?'disabled':''}
		                                   ${!requestScope.isView?'required':''}>
		                                       <option> <!--  When No value in Database -->
	                                       			<c:choose>
	                                       				<c:when test="${requestScope.CustomerModel.marital_stat eq null}">
	                                       					<c:out value=""></c:out>
	                                       				</c:when>
	                                       			</c:choose>
	                                       		</option>
		                                       <c:forEach var="marital_stat" items="${requestScope.marital_stat}">
		                                       		<option ${marital_stat eq requestScope.CustomerModel.marital_stat?'selected':''}>
		                                       			<c:out value="${marital_stat}"></c:out>
		                                       		</option>
												</c:forEach>
		                                   </select>
		                               	</div>
		                            	<div class="form-group"><label>Gender</label>
		                                   <select class="form-control" name="gender" ${requestScope.isView?'disabled':''}
		                                   ${!requestScope.isView?'required':''}>
		                                       <option> <!--  When No value in Database -->
	                                       			<c:choose>
	                                       				<c:when test="${requestScope.CustomerModel.gender eq null}">
	                                       					<c:out value=""></c:out>
	                                       				</c:when>
	                                       			</c:choose>
	                                       		</option>
		                                       <c:forEach var="gender" items="${requestScope.gender}">
		                                       		<option ${gender eq requestScope.CustomerModel.gender?'selected':''}>
		                                       			${gender}
		                                       		</option>
		                                       	</c:forEach>
		                                   </select>
		                               	</div>
		                            	<div class="form-group"><label>Email Address</label>
		                                    <input class="form-control" placeholder="Email Address" name="email" 
		                                    type="text" maxLength="150" value="${requestScope.CustomerModel.email ne null?
		                                    requestScope.CustomerModel.email:''}" ${requestScope.isView?'readonly':''}>
		                                </div>
		                            	<div class="form-group"><label>Race</label>
		                                   <select class="form-control" name="race"
		                                   onchange="displayRaceField(this)"
		                                   ${requestScope.isView?'disabled':''}>
		                                       <c:forEach var="race" items="${requestScope.race}">
		                                       		<option ${requestScope.isView?'disabled':''}
                                                	${race eq requestScope.CustomerModel.race?'selected':''} >
		                                       			<c:out value="${race}"></c:out>
		                                       		</option>
												</c:forEach>
		                                   </select>
		                               	</div>
		                               	<div class="form-group">
		                                    <input class="form-control" placeholder="Other Race" name="other_race" id="other_race"
		                                    type="${requestScope.CustomerModel.race eq 'Others'?'text':'hidden'}" 
		                                    value="${requestScope.CustomerModel.other_race}"
		                                    maxLength="10" ${requestScope.isView?'readonly':''} 
		                                    ${!requestScope.isView?'required':''}>
		                                </div>
                                        <div class="form-group"><label>Permanent Address</label>
		                                    <input class="form-control" placeholder="Permanent Address" name="permanent_address" 
		                                    type="text" maxLength="500" value="${requestScope.CustomerModel.permanent_address ne null?
		                                    requestScope.CustomerModel.permanent_address:''}" ${requestScope.isView?'readonly':''} 
		                                    ${!requestScope.isView?'required':''}>
		                                </div>
                                        <div class="form-group"><label>Permanent Postal Code</label>
		                                    <input class="form-control" placeholder="Permanent Postal Code" name="postal_code" 
		                                    type="number" min="1000" max="9999" value="${requestScope.CustomerModel.postal_code ne 0?
		                                    requestScope.CustomerModel.postal_code:''}" ${requestScope.isView?'readonly':''}
		                                    ${!requestScope.isView?'required':''}>
		                                </div>
		                            </div>
		                            <div class="col-lg-3">
		                            	<div class="form-group"><label>Telephone Number</label>
		                                    <input class="form-control" placeholder="(00)00-00-000" name="tel_no" id="tel"
		                                    type="text" maxLength="15" value="${requestScope.CustomerModel.tel_no ne null?
		                                    requestScope.CustomerModel.tel_no:''}" ${requestScope.isView?'readonly':''}
		                                    ${!requestScope.isView?'required':''}>
		                                </div>
		                                <div class="form-group"><label>Mobile Number</label>
		                                    <input class="form-control" placeholder="(00)00-00-000" name="mob_no" id="mob"
		                                    type="text" maxLength="15" value="${requestScope.CustomerModel.mob_no ne null?
		                                    requestScope.CustomerModel.mob_no:''}" ${requestScope.isView?'readonly':''}>
		                                </div>
		                            	<div class="form-group">
                                            <label>Receive Promotional Materials</label>
                                            <div class="radio"><label>
                                            	<input type="radio" name="promo_materials" 
                                            	value="Y"
                                            	${requestScope.CustomerModel.promo_materials eq 'Y'?'checked':''}
                                            	${requestScope.isView?'disabled':''} ${!requestScope.isView?'required':''}>Yes
                                            </label></div>
                                            <div class="radio"><label>
                                            	<input type="radio" name="promo_materials" 
                                            	value="N"
                                            	${requestScope.CustomerModel.promo_materials eq 'N' || 
                                                requestScope.CustomerModel.promo_materials eq null || 
                                                requestScope.CustomerModel.promo_materials eq ''?'checked':''}
                                                ${requestScope.isView?'disabled':''} 
                                                ${!requestScope.isView?'required':''}>No
                                            </label></div>
                                        </div>
		                               	<div class="form-group">
                                            <label>Disclosure of Information</label>
                                            <div class="radio"><label>
                                            	<input type="radio" name="disclosure_info" 
                                            	value="Y"
                                            	${requestScope.CustomerModel.disclosure_info eq 'Y' || 
                                            	requestScope.CustomerModel.disclosure_info eq ''?'checked':''}
                                            	${requestScope.isView?'disabled':''}
                                            	${!requestScope.isView?'required':''}>Yes
                                            </label></div>
                                            <div class="radio"><label>
                                            	<input type="radio" name="disclosure_info" 
                                            	value="N"
                                            	${requestScope.CustomerModel.disclosure_info eq 'N' || 
                                                requestScope.CustomerModel.disclosure_info eq null || 
                                                requestScope.CustomerModel.disclosure_info eq ''?'checked':''}
                                            	${requestScope.isView?'disabled':''}
                                            	${!requestScope.isView?'required':''}>No
                                            </label></div>
                                            <br/>
                                            <input type="submit" class="btn btn-lg btn-success btn-block" name="Submit"
                                            value="Update" ${requestScope.isView?'disabled':''}>
                                        </div>
		                            </div>
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