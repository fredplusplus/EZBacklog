<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag  pageEncoding="utf-8"%>
<%@attribute name="id" required="true"%>
<%@attribute name="labelKey" required="true"%>
<%@attribute name="textKey" required="true"%>

<div id="${id}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="${id}Label" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="${id}Label"><spring:message code="${labelKey}" /></h3>
	</div>
	<div class="modal-body">
		<p><spring:message code="${textKey}" /></p>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="modal.btn.close" /></button>
		<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true"><spring:message code="modal.btn.ok" /></button>
	</div>
</div>