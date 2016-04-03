<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-lg-10 text-center">
		<p class="h3">Генератор вопросов</p>
		<p class="h4">Метод НК: ${ndtMethod}</p>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
	
		<form:form modelAttribute="order" method="POST">
			<div class="row">
				<div class="col-lg-4">
					<div class="form-group">
						<label for="raspNumber">Номер распоряжения</label> 
						<form:input path="number" cssClass="form-control" id="raspNumber" title="OK"/>
					</div>

				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label for="raspDate">Дата распоряжения</label> <input type="date"
							class="form-control" id="raspDate"
							placeholder="Дата распоряжения" />
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label for="variantCount">Количество вариантов</label> <input
							type="text" class="form-control" id="variantCount"
							placeholder="Количество вариантов" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4">
					<div class="checkbox">
						<label> <input type="checkbox" />Общий экзамен
						</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox" />Специальный экзамен
							общая часть
						</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox" />6-й сектор
						</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox" />7-й сектор
						</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox" />8-й сектор
						</label>
					</div>					
					<label class="control-label" for="selectReportType">Формат
						отчета</label> <select class="form-control" id="selectReportType">
						<option>Excel</option>
						<option>PDF</option>
					</select>
					<p style="margin-top: 10px">
						<input class="btn btn-primary" type="submit" value="Создать">
					</p>

				</div>
			</div>
		</form:form>
	</div>
</div>