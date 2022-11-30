<%@page import="bean.CartBean"%>
<%@page import="bo.CartBo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head >
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart | Book store</title>
    <link rel="icon" type="image/x-icon" href="image/favicon.ico">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
          integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

    <style>
         .quantity-input {
	      border-top-left-radius: 10px;
	      border-bottom-left-radius: 10px;
	      border: 1px solid rgba(0, 0, 0, 0.5);
	      width: 50px;
	      height: 30px;
	      padding-left:5px;
	    }
	    .quantity-input:focus-visible {outline: none;}
	
	    input::-webkit-outer-spin-button,
	    input::-webkit-inner-spin-button {
	      -webkit-appearance: none;
	      margin: 0;
	    }
	    .quantity-update {
	      border: none;
	      height: 30px;
	      border-top-right-radius: 10px;
	      border-bottom-right-radius: 10px;
	    }
	    .quantity-update:active {
	      border: 2px solid rgba(0, 0, 0, 0.5);
	      border-left: none;
	      font-size: 0.8rem;
	    }
    </style>
</head>
<body class="bg-light">
 <jsp:include page="common/header.jsp"></jsp:include>
 <div style="margin-top:86px;"></div>
<section class="h-100 h-custom" style="background-color: #eee;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col">
                <div class="card">
                    <div class="card-body p-4">

                        <div class="row">
				
                            <div class="col-lg-9">
                                <h5 class="mb-3">Shopping Cart</h5>
                                <hr>

                                <div class="d-flex justify-content-between align-items-center mb-4">
                                    <div>
                                        <p class="mb-0">You have <b>${count }</b> Items in your cart</p>
                                    </div>

                                    <!-- <div>
                                        <p class="mb-0"><span class="text-muted">Sort by:</span> <a href="#!"
                                                                                                    class="text-body">price
                                            <i class="fas fa-angle-down mt-1"></i></a></p>
                                    </div> -->
                                </div>
                                <form action="save-delete" method="post">
                                    <div class="ms-auto mb-3">
                                        <button type="submit" name="clear" class="btn btn-outline-primary me-2">Clear
                                            Cart
                                        </button>
                                    </div>
                                    <c:forEach var="item" items="${cartItems}">
                                    <div class="card mb-3">
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between">
                                                <div class="d-flex flex-row align-items-center">
                                                    <input type="checkbox" name="check" value="${item.getMasach()}"
                                                           class="form-check-input me-4">
                                                    <div>
                                                        <img
                                                                src="${item.getAnh()}"
                                                                class="img-fluid rounded-3" alt="Shopping item"
                                                                style="width: 65px;">
                                                    </div>
                                                    <div class="ms-3">
                                                        <h5>${item.getTensach() }
                                                        </h5>
                                                    </div>
                                                </div>

                                                <div class="d-flex flex-row align-items-center">
                                                    <div class="d-flex align-items-center justify-content-center me-4">
                                                        <input type="number" class="quantity-input"
                                                               name="${item.getMasach()}"
                                                               value="${item.getSoluong()}"
                                                               min="0">
                                                        <button type="submit" name="update-quantity" class="quantity-update bg-primary text-white fw-bold"
                                                                value="${item.getMasach()}">Update
                                                        </button>
                                                    </div>
                                                    <div style="width: 150px;">
                                                        <h5 class="mb-0">$ ${item.getThanhtien() }
                                                        </h5>
                                                    </div>
                                                    <button type="submit" name="delete" value="${item.getMasach()}"
                                                            class="btn text-danger" style="color: #cecece;"><i
                                                            class="fas fa-trash-alt"></i></button>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    </c:forEach>
                                </form>
                                <a href="home" class="btn fw-bolder mt-5"><i class="fa fa-arrow-alt-circle-left"></i> Back to Shop</a>
                            </div>
                            <div class="col-lg-3">
		                      <div class="card">
		                        <div class="card-header bg-dark text-white">
		                          <h5>Summary</h5>
		                        </div>
		                        <div class="card-body">
		                            <div class="form-group">
		                                <label for="coupon">Have coupon?</label>
		                                  <input type="text"
		                                    class="form-control ml-auto" name="coupon" id="coupon" aria-describedby="couponId" placeholder="Coupon code">
		                                  <button class="btn btn-primary mt-1">Apply</button>
		                            </div>
		                            <hr>
		                            <div class="d-flex align-content-center justify-content-between">
		                              <label class="fw-bold">Total price:</label>
		                              <span>$ ${totalPrice }</span>
		                            </div>
		                            <div class="d-flex align-content-center justify-content-between">
		                              <label class="fw-bold">Discount:</label>
		                              <span>$ ${discount }</span>
		                            </div>
		                            <hr>
		                            <div class="d-flex align-content-center justify-content-between">
		                              <label class="fw-bold">SubTotal:</label>
		                              <span class="fw-bold text-danger">$ ${subTotal }</span>
		                            </div>
		                            <hr>
		                            <a href="check-out" class="btn btn-primary">Checkout <i class="fa fa-arrow-circle-right" ></i></a>
		                        </div>
		                      </div>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>