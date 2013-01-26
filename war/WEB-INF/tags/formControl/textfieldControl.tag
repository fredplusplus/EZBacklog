<%@include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>
<%@attribute name="id" required="true"%>
<%@attribute name="labelKey" required="true"%>
<%@attribute name="validationKey" %>
<%@attribute name="width" description="span size"%>
<c:if test="${empty width}">
	<c:set var="width" value="12"/>
</c:if>
<div class="row-fluid">
<div class="control-group" id="${id}Group">
	<label class="control-label"><spring:message code="${labelKey}" /></label>
	<div class="controls">
		<input id="${id}" type="text" class="span${width}"
			value="<@ print(typeof(${id})=='undefined'?'':${id}) @>">
		<c:if test="${!empty validationKey}">
			<div class="help-block hide">
				<spring:message code="${validationKey}" />
			</div>
		</c:if>
	</div>
</div>
</div>