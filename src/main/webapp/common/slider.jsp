<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <!-- Carousel -->
       <div style="margin-top:86px;"></div>
     <div class="container">
     <div id="myCarousel" class="carousel slide mb-5" style="height: 400px;" data-bs-ride="carousel">
  <!-- Indicators/dots -->
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active"></button>
    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1"></button>
    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2"></button>
  </div>
  
  <!-- The slideshow/carousel -->
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="image/kayaks.jpg" alt="Los Angeles" class="d-block" style="width:100%; height: 430px;">
    </div>
    <div class="carousel-item">
      <img src="image/mountain-view.jpg" alt="Chicago" class="d-block" style="width:100%;height: 430px;">
    </div>
    <div class="carousel-item">
      <img src="image/riverside-city.jpg" alt="New York" class="d-block" style="width:100%;height: 430px;">
    </div>
  </div>
  
  <!-- Left and right controls/icons -->
  <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
    <span class="carousel-control-next-icon"></span>
  </button>
</div>
     </div>
