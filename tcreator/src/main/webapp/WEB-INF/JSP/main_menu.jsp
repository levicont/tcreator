<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" 
				data-toggle="collapse" 
				data-target="#bs-example-navbar-collapse-1" 
				aria-expanded="false">
					 <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href='<spring:url value="/"/>'>TCreator</a>
		</div>
		 <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href='<spring:url value="/"/>'>Home <span class="sr-only">(current)</span></a></li>       
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">NTD Methods<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<spring:url value="/generator?method=VT"/>">VT</a></li>
            <li><a href="<spring:url value="/generator?method=PT"/>">PT</a></li>
            <li><a href="<spring:url value="/generator?method=MT"/>">MT</a></li>
            <li><a href="<spring:url value="/generator?method=UT"/>">UT</a></li>
            <li><a href="<spring:url value="/generator?method=RT"/>">RT</a></li>            
          </ul>
        </li>        
      </ul>      
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Settings</a></li>        
      </ul>
    </div><!-- /.navbar-collapse -->
	</div>
</nav>
