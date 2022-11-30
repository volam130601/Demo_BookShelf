<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Purchase | Book Store</title>
    	<jsp:include page="head.jsp"></jsp:include>
</head>
<body class="bg-light" style="height:100%;">
 <jsp:include page="header.jsp"></jsp:include>
 <div style="margin-top:86px;"></div>
 <div class="container-fluid h-100" >
 	<section class="h-100 h-custom">
        <div class="container py-5 h-100">
          <div class="row d-flex justify-content-center h-100">
            <div class="col">
              <div class="card">
                <div class="card-body p-4">
      
                  <div class="row">
                    <h2>Order History</h2>
                    <table class="table table-striped">
                        <thead>
                          <tr>
                            <th>#</th>
                            <th>Customer Name</th>
                            <th>Order Date</th>
                            <th>Sub Total</th>
                            <th>Status</th>
                            <th>Action</th>
                          </tr>
                        </thead>
                        <tbody>  
                        	
                        	<c:forEach var="item" items="${orders}" varStatus="loop">
	                          <tr>
	                            <td>${loop.index + 1 }</td>
	                            <td>${item.getCustomerName() }</td>
	                            <td>${item.getOrderDate()}</td>
	                            <td>$ ${item.getSubTotal()}</td>
	                            <td>
	                            	<c:if test="${item.isOrdered() == true }">
	                            		<span class="badge bg-success">Is-paid</span>
	                            	</c:if>
	                            	<c:if test="${item.isOrdered() == false }">
	                            		<span class="badge bg-danger">Un-paid</span>
	                            	</c:if>
								</td>
								<td>
									<a href="order-detail?orderId=${item.getOrderId()}" class="btn btn-primary">View</a>
								</td>
	                          </tr>
                        	</c:forEach>
                        </tbody>
                      </table>
                         <c:if test="${orders.size() < 1}">
                        		<p class="d-flex align-item-center text-danger fw-bold">History Purchase is empty!</p>
                         </c:if>
                  </div>
      
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>  
 </div>
 <!-- Toast -->
 <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
  <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-bs-delay="2000">
    <div class="toast-header text-${status }">
      <strong><i class="fa-solid fa-check"></i></strong>
      <strong class="me-auto">Order Message</strong>
      <small>11 mins ago</small>
      <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
    <div class="toast-body bg-${status } text-light">
       <strong>${message }</strong> 
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