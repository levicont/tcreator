<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-lg-12 text-center tcreator-print-hide">
		<p class="h3">Предварительный просмотр</p>
	</div>
</div>


<form action="/">
	<div class="panel panel-success">
		<div class="panel-heading">
			<div class="row">
				<div class="col-lg-10 text-left">
					<label><h4>Метод НК: ${order.ndtMethod.toString()}</h4></label>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label for="raspNumber">Распоряжение №</label> <input
							id="raspNumber" class="form-control" type="text"
							value="${order.number }" disabled="disabled" />
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label for="raspDate">Дата</label> <input id="raspDate"
							class="form-control" type="text" value="${dateOrder }"
							disabled="disabled" />
					</div>
				</div>
				<div class="col-lg-3">
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
							<label><i class="fa fa-check lvg-checked"></i> Общий
								экзамен</label>
						</c:if>
					</div>
					<div class="form-group">
						<c:if test="${order.isSpecTest}">
							<label><i class="fa fa-check lvg-checked"></i>
								Специальный экзамен</label>
						</c:if>
					</div>
					<div class="form-group">
						<c:if test="${order.is6sector}">
							<label><i class="fa fa-check lvg-checked"></i> 6-й сектор</label>
						</c:if>
					</div>
					<div class="form-group">
						<c:if test="${order.is7sector}">
							<label><i class="fa fa-check lvg-checked"></i> 7-й сектор</label>
						</c:if>
					</div>
					<div class="form-group">
						<c:if test="${order.is8sector}">
							<label><i class="fa fa-check lvg-checked"></i> 8-й сектор</label>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row tcreator-print-hide">
		<div class="col-lg-3">
			<input class="btn btn-primary" type="submit" value="Скачать" disabled="disabled" />
		</div>
	</div>
	<div class="row tcreator-print-hide">
		<div class="col-lg-12">
			<div id="lvg-separator"></div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<c:forEach var="test" items="${tests}">
				<div class="panel panel-info">
					<div class="panel-heading">
						<p class="h3">${test.title}&nbsp;&nbsp;&nbsp;&nbsp;${order.ndtMethod.toString()}</p>
						<p class="h4 text-right">Вариант: ${test.variantNumber}</p>
					</div>
					<div class="panel-body">
						<c:forEach var="question" items="${test.questions }">
							<dl>
								<c:forEach var="line" varStatus="loop" items="${question.text}">
									<c:if test="${loop.index == 0 }">
										<dt>${line }</dt>
									</c:if>
									<c:if test="${loop.index != 0 }">
										<dd>${line }</dd>
									</c:if>

								</c:forEach>
							</dl>
						</c:forEach>
					</div>
					<div class="panel-footer">
						<p>id:${test.id } Распоряжение № ${order.number}</p>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</form>

