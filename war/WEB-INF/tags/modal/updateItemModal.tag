<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>
<%@attribute name="id" required="true"%>

<div id="${id}" class="modal hide fade" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="${id}Label" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="${id}Label"><spring:message code="modal.title.createItem" /></h3>
	</div>
	<div class="modal-body">
		<h4>blah </h4>
		<dl class="dl-horizontal">
			<dt><spring:message code="itemDetail.originalpoint" /></dt>
			<dd>blah</dd>
			<dt><spring:message code="itemDetail.resolvedpoint" /></dt>
			<dd>blat less dias</dd>
		</dl>
		<form class="form-horizontal">
		<div class="control-group">
			<label class="control-label">a label</label>
			<div class="controls">
				<input type="text" class="input-mini">
				<div class="help-block hide">a help text</div>
			</div>
		</div>
		</form>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="modal.btn.close" /></button>
		<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true"><spring:message code="modal.btn.ok" /></button>
	</div>
</div>