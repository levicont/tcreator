<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-lg-10 text-center">
		<p class="h3">Предварительный просмотр</p>
		<p class="h4">Метод НК: ${order.ndtMethod.toString()}</p>
	</div>
</div>


<form action="/print">
	<div class="row">
		<div class="col-lg-3">
			<div class="form-group">
				<label for="raspNumber">Распоряжение №</label> <input
					id="raspNumber" class="form-control" type="text"
					value="${order.number }" disabled="disabled" />
			</div>
			<div class="form-group">
				<label for="raspDate">Дата</label> <input id="raspDate"
					class="form-control" type="text" value="${dateOrder }"
					disabled="disabled" />
			</div>
			<div class="form-group">
				<label for="variantCount">Количество вариантов</label> <input
					id="variantCount" class="form-control" type="text"
					value="${order.variantCount }" disabled="disabled" />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3">
			<div class="form-group">
				<c:if test="${order.isTotalTest}">
					<label><i class="fa fa-check"></i> Общий экзамен</label>
				</c:if>
			</div>
			<div class="form-group">
				<c:if test="${order.isSpecTest}">
					<label><i class="fa fa-check"></i> Специальный экзамен</label>
				</c:if>
			</div>
			<div class="form-group">
				<c:if test="${order.is6sector}">
					<label><i class="fa fa-check"></i> 6-й сектор</label>
				</c:if>
			</div>
			<div class="form-group">
				<c:if test="${order.is7sector}">
					<label><i class="fa fa-check"></i> 7-й сектор</label>
				</c:if>
			</div>
			<div class="form-group">
				<c:if test="${order.is8sector}">
					<label><i class="fa fa-check"></i> 8-й сектор</label>
				</c:if>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3">
			<input class="btn btn-primary" type="submit" value="Скачать" />
		</div>
	</div>
	<div class="row">
		<div class="col-lg-10">
			<c:forEach var="test" items="${tests}">
				<p class="h3">${test.title}</p>
				<p class="h4 text-right">Вариант: ${test.variantNumber}</p>
				<c:forEach var="question" items="${test.questions }">
					<c:forEach var="line" varStatus="loop" items="${question.text}">
						<dl>
							<c:if test="${loop.index == 0 }">
								<dt >${line }</dt>
							</c:if>
							<c:if test="${loop.index != 0 }">
								<dd >${line }</dd>
							</c:if>							
						</dl>
					</c:forEach>
				</c:forEach>
				<p>${test.id }</p>
			</c:forEach>
		</div>
	</div>
</form>

