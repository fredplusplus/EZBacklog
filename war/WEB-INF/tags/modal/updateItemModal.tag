<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>

<script src="/static/js/updateItemModal.js" type="text/javascript"></script>
<div id="updateItemModalContainer"></div>
<script type="text/template" id="updateItemModalTemplate">
<div id="updateItemModal" class=" updateItemModal modal hide fade" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="$updateItemModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="updateItemModalLabel">
			<@ if (typeof(id) =='undefined') { @>
				<@ if (itemLevel==1) {@> <spring:message code="itemAggregation.create.1" /> <@} @>
				<@ if (itemLevel==2) {@> <spring:message code="itemAggregation.create.2" /> <@} @>
				<@ if (itemLevel==3) {@> <spring:message code="itemAggregation.create.3" /> <@} @>
			<@ } else { @>
				<spring:message code="modal.title.edit" />
			<@} @>
		</h3>
	</div>
	<div class="modal-body">
		<dl class="dl-horizontal">
			<dt><spring:message code="itemDetail.level" /></dt>
			<dd>
				<@ if (itemLevel==1) {@> <spring:message code="itemLevel.1" /> <@} @>
				<@ if (itemLevel==2) {@> <spring:message code="itemLevel.2" /> <@} @>
				<@ if (itemLevel==3) {@> <spring:message code="itemLevel.3" /> <@} @>
			</dd>
			<dt><spring:message code="modal.input.label.parent" /></dt>
			<dd>
				<@ if (typeof(parentId)!='undefined') { @>
				<@= parentId @>
				<@ } @>
			</dd>
		</dl>
		<form class="form-horizontal">
			<fc:textfieldControl labelKey="modal.input.label.title" id="shortDescription" validationKey="modal.validation.maxLength.40" />
		</form>
	</div>
	<div class="modal-footer">
		<button id="cancelUpdate" class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="modal.btn.close" /></button>
		<button id="submitUpdate" class="btn btn-primary" data-dismiss="modal" aria-hidden="true"><spring:message code="modal.btn.ok" /></button>
	</div>
</div>
</script>