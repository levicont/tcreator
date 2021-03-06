<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="INVALID_TEST_MSG" value="<%=com.lvg.tcreator.config.R.OrderProps.INVALID_DATE_MESSAGE %>"/>
<div class="row">
	<div class="col-lg-12 text-center lvg-title-text">
		<p class="h3">Генератор вопросов</p>

	</div>
</div>
<div class="row">
	<div class="col-lg-12">


		<form:form modelAttribute="order" method="POST">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<p class="h4">Метод НК: ${ndtMethod}</p>
				</div>
				<div class="panel-body">
					<form:hidden path="ndtMethod" />
					<div class="row">
						<div class="col-lg-4">
							<div class="form-group">
								<label for="raspNumber">Номер распоряжения</label>
								<form:input path="number" cssClass="form-control"
									id="raspNumber" placeholder="Номер распоряжения" />
									<form:errors path="number" cssClass="lvg-error"></form:errors>
							</div>

						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label for="raspDate">Дата распоряжения</label>
								<fmt:formatDate pattern='dd.MM.yyyy' type='date'
									value="${order.date }" var="formattedDate" />
								<form:input path="date" data-format="dd.MM.yyyy"
									value='${formattedDate }' cssClass="form-control" id="raspDate"
									placeholder="Дата распоряжения" />
								<form:errors path="date">
									<span class="lvg-error">
										<c:out value="${INVALID_TEST_MSG }"/>
									</span>
								</form:errors>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label for="variantCount">Количество вариантов</label>
								<form:input path="variantCount" cssClass="form-control"
									id="variantCount" placeholder="Количество вариантов" />
									<form:errors path="variantCount" cssClass="lvg-error"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-4">
							<div class="checkbox">
								<label> <form:checkbox path="isTotalTest" />Общий
									экзамен
								</label>
							</div>
							<div class="checkbox">
								<label> <form:checkbox path="isSpecTest" />Специальный
									экзамен общая часть
								</label>
							</div>
							<div class="checkbox">
								<label> <form:checkbox path="is6sector" />6-й сектор
								</label>
							</div>
							<div class="checkbox">
								<label> <form:checkbox path="is7sector" />7-й сектор
								</label>
							</div>
							<div class="checkbox">
								<label> <form:checkbox path="is8sector" />8-й сектор
								</label>
							</div>
							<label class="control-label" for="selectReportType">Формат
								отчета</label> <select class="form-control" id="selectReportType" disabled="disabled">
								<option>Excel</option>
								<option>PDF</option>
							</select>


						</div>
					</div>

				</div>
				<div class="panel-footer">
					<input class="btn btn-primary" type="submit" value="Создать">
				</div>
			</div>
		</form:form>


	</div>
</div> 