<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My account| Book Store</title>

	<jsp:include page="common/head.jsp"></jsp:include>
</head>
<body>
	 <jsp:include page="common/header.jsp"></jsp:include>
 <div style="margin-top:86px;"></div>
 <section class="h-custom" style="background-color: #eee;">
        <div class="container py-5">
          <div class="row d-flex justify-content-center align-items-center">
            <div class="col">
              <div class="card">
                <div class="card-body p-4">
      
                  <h2>My Account</h2>
                        
                  <div class="row">
                    <div class="col">
                    	<c:if test="${not empty message }">
                    	<div class="row m-1">
                        	<span class="alert alert-${status }">${message }</span>
                    	</div>
                        </c:if>
                      <form action="my-account" method="post">
                        <div class="form-group">
                          <label for="fullname">Full Name</label>
                          <input type="text"
                            class="form-control" value="${customer.getFullName()}" name="fullname" id="fullname" aria-describedby="helpFullName" placeholder="Enter Full Name">
                          <small id="helpFullName" class="form-text text-muted"></small>
                        </div>
                        <div class="form-group">
                          <label for="username">User Name</label>
                          <input type="text" value="${customer.getUsername()}" disabled="disabled" name="username" id="username" class="form-control" placeholder="Enter username" aria-describedby="usernameHekp">
                          <small id="usernameHekp" class="text-muted"></small>
                        </div>
                        <div class="form-group">
                          <label for="password">Password</label>
                          <input type="password"
                            class="form-control"  value="${customer.getPassword()}" name="password" id="password" aria-describedby="helpPassword" placeholder="Enter Password">
                          <small id="helpPassword" class="form-text text-muted"></small>
                        </div>
                        <div class="form-group">
                          <label for="address">Address</label>
                          <input type="text"
                            class="form-control" name="address"  value="${customer.getAddress()}" id="address" aria-describedby="helpAddress" placeholder="Enter Address">
                          <small id="helpAddress" class="form-text text-muted"></small>
                        </div>
                        <div class="form-group">
                          <label for="phoneNumber">Phone Number</label>
                          <input type="number"
                            class="form-control" value="${customer.getPhoneNumber()}" name="phoneNumber" id="phoneNumber" aria-describedby="hPhoneNumber" placeholder="Enter Phone Number">
                          <small id="hPhoneNumber" class="form-text text-muted"></small>
                        </div>
                        <div class="form-group">
                          <label for="email">Email</label>
                          <input type="email"
                            class="form-control" value="${customer.getEmail()}" name="email" id="email" aria-describedby="hEmail" placeholder="Enter Email">
                          <small id="hEmail" class="form-text text-muted"></small>
                        </div>
                        <button type="submit" class="btn btn-primary">Save</button>
                      </form>

                    </div>
                  </div>
      
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>  
      
      <!-- Toast -->
 <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
  <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-bs-delay="2000">
    <div class="toast-header text-success">
      <strong><i class="fa-solid fa-check"></i></strong>
      <strong class="me-auto">My account Message</strong>
      <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
    <div class="toast-body bg-success">
      ${message }
    </div>
  </div>
</div>

	<c:set var="message" value="${message}"/> 
<script type="text/javascript">
	var liveToast = document.getElementById('liveToast')
	var message = '${message}'
	if (message != '') {
	    var toast = new bootstrap.Toast(liveToast)
	    toast.show()
	}
</script>	
</body>
</html>