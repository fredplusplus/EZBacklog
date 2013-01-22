<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>

<div id="updateProgressModal" class="updateProgressModal modal hide fade" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="updateProgressModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="updateProgressModalLabel"><spring:message code="modal.title.updateProgress" /></h3>
	</div>
	<div class="modal-body">
		<h4><@= shortDescription @></h4>
		<dl class="dl-horizontal">
			<dt><spring:message code="itemDetail.originalpoint" /></dt>
			<dd><@= point @></dd>
			<dt><spring:message code="itemDetail.resolvedpoint" /></dt>
			<dd><@= resolvedPoint @></dd>
		</dl>
		<form class="form-horizontal">
		<div class="control-group" id="burndownPointGroup">
			<label class="control-label"><spring:message code="itemDetail.burndownpoint" /></label>
			<div class="controls">
				<input type="text" id="burndownPoint" class="input-mini">
				<div class="help-block hide" id="burndownPointHelp"><spring:message code="modal.validation.integer" /></div>
			</div>
		</div>
		</form>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="modal.btn.close" /></button>
		<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true"><spring:message code="modal.btn.ok" /></button>
	</div>
</div>