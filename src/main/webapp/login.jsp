<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
         <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Login | Book Store</title>
       	<jsp:include page="common/head.jsp"></jsp:include>
</head>
<body>
<section class="vh-100" style="background-color: #9A616D;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col col-xl-10">
        <div class="card" style="border-radius: 1rem;">
          <div class="row g-0">
            <div class="col-md-6 col-lg-5 d-none d-md-block">
              <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/img1.webp"
                alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
            </div>
            <div class="col-md-6 col-lg-7 d-flex align-items-center">
              <div class="card-body p-4 p-lg-5 text-black">

                <form action="login" method="post" class="form-login">
	                  <div class="d-flex align-items-center mb-3 pb-1">
	                    <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
	                    <span class="h1 fw-bold mb-0">Logo</span>
	                  </div>
	
	                  <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign into your account</h5>
					 <c:if test="${report != null}">
					 	<div class="alert alert-danger">Username or password is invalid!</div>
					 </c:if>	
					 <c:if test="${ not empty message and not empty status }">
					 	<div class="alert alert-${status }">${message }</div>
					 </c:if>
					 
	                  <div class="form-outline mb-4">
	                    <label class="form-label" for="form2Example17">Username</label>
	                    <input type="text" id="form2Example17" name="username" placeholder="Enter Username" required class="form-control form-control-lg" />
	                  </div>
	
	                  <div class="form-outline mb-4">
	                    <label class="form-label" for="form2Example27">Password</label>
	                    <input type="password" id="form2Example27" name="password" placeholder="Enter Password" required class="form-control form-control-lg" />
	                  </div>
	
	                  <div class="pt-1 mb-4">
	                    <button class="btn btn-dark btn-lg btn-block" type="submit">Login</button>
	                  </div>
	
	                  <a class="small text-muted" href="#!">Forgot password?</a>
	                  <p class="pb-lg-2" style="color: #393f81;">Don't have an account? <button type="button" onclick="showRegister()"
	                      class="bg-transparent border-0 text-primary fw-bold text-decoration-underline">Register here</button></p>
                </form>
                <form action="register" method="post" class="form-register d-none">
                    <div class="d-flex align-items-center  pb-1">
                      <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                      <span class="h1 fw-bold mb-0">Logo</span>
                    </div>
  
                    <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Create New Account</h5>
  
                    <div class="form-outline mb-4">
                      <label class="form-label" for="form2Example17">Full Name</label>
                      <input type="text" id="form2Example17" name="fullname" class="form-control form-control-lg" required />
                    </div>
                    <div class="form-outline mb-4">
                      <label class="form-label" for="username">Username</label>
                      <input type="text" id="username" name="username" class="form-control form-control-lg"  required/>
                    </div>
                    <div class="form-outline mb-4">
                      <label class="form-label" for="form2Example17">Password</label>
                      <input type="password" id="form2Example17" name="password" class="form-control form-control-lg" required/>
                    </div>
                    <div class="pt-1 mb-4">
                      <button class="btn btn-dark btn-lg btn-block" type="submit">Submit</button>
                    </div>
                    
                    <p class="pb-lg-2" style="color: #393f81;"><button type="button" onclick="showLogin()"
	                      class="bg-transparent border-0 text-primary fw-bold text-decoration-underline">Login here</button></p>
                 </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<script type="text/javascript">
	function showRegister() {
	    $('.form-login').addClass('d-none')
	    $('.form-register').removeClass('d-none')
	}
	function showLogin() {
	    $('.form-login').removeClass('d-none')
	    $('.form-register').addClass('d-none')
	}
</script>
</body>
</html>