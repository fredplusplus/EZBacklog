<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>
<%@attribute name="id" required="true"%>
<%@attribute name="labelKey" required="true"%>
<%@attribute name="validationKey" %>


<div class="control-group" id="${id}Group">
	<label class="control-label"><spring:message code="${labelKey}" /></label>
	<div class="controls">
		<input id="${id}" type="text"
			value="<@ print(typeof(${id})=='undefined'? '' : shortDescription) @>">
		<c:if test="${!empty validationKey}">
			<div class="help-block hide">
				<spring:message code="${validationKey}" />
			</div>
		</c:if>
	</div>
</div>