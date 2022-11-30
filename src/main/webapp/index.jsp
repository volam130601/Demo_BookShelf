<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Store</title>
   	<jsp:include page="common/head.jsp"></jsp:include>
   	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
   	 
	
	<style type="text/css">
		.title-book {
		    height: 40px;
		    display: -webkit-box;
		    -webkit-line-clamp: 2;
		    -webkit-box-orient: vertical;
		    overflow: hidden;
		}
	</style>
</head>

<body class="bg-light">
 <jsp:include page="common/header.jsp"></jsp:include>

 
 <jsp:include page="common/slider.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <jsp:include page="common/sidebar.jsp"></jsp:include>
        </div>
        <div class="col-lg-9">
            <div class="album py-5 bg-light rounded shadow">
            	<form class="nav-item d-flex ms-4 mb-2" action="home">
                    <input class="form-control me-auto" type="search" name="search" placeholder="Search"
                           aria-label="Search">
                    <button class="btn btn-outline-success ms-2 me-2" type="submit">Search</button>
                </form>
                <hr>	
                <div class="container">
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                        <c:forEach var="s" items="${books}">
                            <div class="col">
                                <div class="card shadow-sm">
                                    <img class="bd-placeholder-img card-img-top" height="250"
                                         src="${s.getAnh()}"
                                         alt="Image Book">
                                    <div class="card-body" style="height: 160px;">
                                        <h6 class="card-title text-primary title-book">${s.getTenSach()}
                                        </h6>
                                        <div class="card-text d-flex align-items-center justify-content-between">
                                                <span class="text-bolder" style="font-size: 20px;font-weight: 500;">$${s.getGia()}
                                                </span>
                                            <em class="text-muted">--${s.getTacGia()}--</em>
                                        </div>
                                        <div class="d-flex justify-content-between align-items-center mt-2">
                                            <div class="btn-group">
                                                <a href="handle-cart?ms=${s.getMaSach()}&ts=${s.getTenSach()}&gia=${s.getGia()}&anh=${s.getAnh()}"
                                                   class="btn btn-sm btn-outline-secondary"><i class="fa-solid fa-cart-shopping me-2"></i>Add to cart </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <c:if test="${empty search}">
	            <div class="d-flex justify-content-center mt-4">
	                    <nav aria-label="Page navigation example">
	                      <ul class="pagination"> 
	                        
	                        <c:if test="${not empty categoryId}">
		                        <li class="page-item <c:if test="${p == 1}">disabled</c:if>">
		                          <a class="page-link " href="home?page=${p-1}&cId=${categoryId}" aria-label="Previous">
		                            <span aria-hidden="true">&laquo;</span>
		                          </a>
		                        </li>
	                        	<c:forEach begin="${begin}" end="${end}" var="number">
	                        	<li class="page-item <c:if test="${number == p}">active</c:if>">
	                        		<a class="page-link" href="home?page=${number}&cId=${categoryId}">${number}</a></li>
		                    	</c:forEach>
		                    	<li class="page-item <c:if test="${p == end}">disabled</c:if>">
		                          <a class="page-link" href="home?page=${p+1}&cId=${categoryId}" aria-label="Next">
		                            <span aria-hidden="true">&raquo;</span>
		                          </a>
	                        	</li>
	                        </c:if>
	                        <c:if test="${empty categoryId }">
			                     <li class="page-item <c:if test="${p == 1}">disabled</c:if>">
		                          <a class="page-link " href="home?page=${p-1}" aria-label="Previous">
		                            <span aria-hidden="true">&laquo;</span>
		                          </a>
		                        </li>
	                        	<c:forEach begin="${begin}" end="${end}" var="number">
	                        	<li class="page-item <c:if test="${number == p}">active</c:if>">
	                        		<a class="page-link" href="home?page=${number}">${number}</a></li>
		                    	</c:forEach>
		                    	<li class="page-item <c:if test="${p == end}">disabled</c:if>">
		                          <a class="page-link" href="home?page=${p+1}" aria-label="Next">
		                            <span aria-hidden="true">&raquo;</span>
		                          </a>
	                        	</li>
	                        </c:if>
	                        
	                      </ul>
	                    </nav>
	                  </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
    <jsp:include page="common/footer.jsp"></jsp:include>
      <script>
        $(document).ready(function () {
          var myCarousel = document.querySelector('#myCarousel')
          var carousel = new bootstrap.Carousel(myCarousel, {
            interval: 2000,
            wrap: true
          })
        });
      </script>
</body>
</html>