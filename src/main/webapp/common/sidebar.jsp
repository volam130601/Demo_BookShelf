<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="d-flex flex-column flex-shrink-0 p-3 bg-light rounded shadow h-100" style="width: 280px;">
                <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
                    <svg class="bi me-2" width="40" height="32">
                        <use xlink:href="#bootstrap"></use>
                    </svg>
                    <span class="fs-4 fw-bold">Category</span>
                </a>
                <hr>
                <ul class="nav nav-pills flex-column mb-auto">
                    <c:forEach var="item" items="${categories}">
                        <li class="nav-item"> 
                            <a href="home?cId=${item.getMaLoai()}"
                               class="nav-link <c:if test="${item.getMaLoai().equals(categoryId)}"> active </c:if>"
                               aria-current="page">
                                    ${item.getTenLoai()}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
