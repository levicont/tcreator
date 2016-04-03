<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-lg-10 text-center">
		<p class="h3">Предварительный просмотр</p>
		<p class="h4">Метод НК: ${ndtMethod}</p>
	</div>
</div>
<div class="row">
	<div class="col-lg-3">
		<form action="/print">
			<div class="form-group" >
				<label for="raspNumber">Распоряжение №</label>
				<input id="raspNumber" class="form-control" type="text" value="${order.number }" disabled="disabled"/>
			</div>
			<div class="form-group" >
				<label for="raspDate">Дата</label>
				<input id="raspDate" class="form-control" type="text" value="${order.date }" disabled="disabled"/>
			</div>
			<div class="form-group" >
				<label for="variantCount">Количество вариантов</label>
				<input id="variantCount" class="form-control" type="text" value="${order.variantCount }" disabled="disabled"/>
			</div>
		</form>
	</div>
</div>