<%@include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>
<%@attribute name="id" required="true"%>
<%@attribute name="labelKey" required="true"%>
<%@attribute name="validationKey" %>
<%@attribute name="width" description="span size"%>
<%@attribute name="rows"%>
<c:if test="${empty width}">
	<c:set var="width" value="12"/>
</c:if>
<c:if test="${empty rows}">
	<c:set var="width" value="3"/>
</c:if>
<div class="row-fluid">
<div class="control-group" id="${id}Group">
	<label class="control-label"><spring:message code="${labelKey}" /></label>
	<div class="controls">
		<textarea id="${id}" rows="${rows}" class="span${width}"><@ print(typeof(${id})=='undefined'?'':${id}) @></textarea>
		<c:if test="${!empty validationKey}">
			<div class="help-block hide">
				<spring:message code="${validationKey}" />
			</div>
		</c:if>
	</div>
</div>
</div>