<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>

<script src="/static/js/updateItemModal.js" type="text/javascript"></script>
<div id="updateItemModalContainer"></div>
<script type="text/template" id="updateItemModalTemplate">
<div id="updateItemModal" data-keyboard="false" class="updateItemModal modal hide fade" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="$updateItemModalLabel" aria-hidden="true">
	<div class="modal-header">
		<h3 id="updateItemModalLabel">
			<@ if (typeof(id) =='undefined') { @>
				<item:itemType key="itemLevel" displayPrefix="itemAggregation.create" />
			<@ } else { @>
				<spring:message code="modal.title.edit" />
			<@} @>
		</h3>
	</div>
	<div class="modal-body">
		<dl class="dl-horizontal">
			<dt><spring:message code="itemDetail.level" /></dt>
			<dd>
				<item:itemType key="itemLevel" displayPrefix="itemLevel" />
				<@ if (typeof(id) != 'undefined') { @>
					&nbsp;-&nbsp<@=id@>
				<@ } @>
			</dd>
			<dt><spring:message code="modal.input.label.parent" /></dt>
			<dd>
				<@ if (typeof(parentId)!='undefined' && parentId != 0) { @>
					<@= parentId @>
				<@ } else {@>
					<spring:message code="modal.input.field.none" />
				<@ } @>
			</dd>
		</dl>
		<div class="form-horizontal">
			<fc:textfieldControl labelKey="modal.input.label.title" id="shortDescription" validationKey="modal.validation.maxLength.40" />
			<fc:textfieldControl labelKey="modal.input.label.point" id="point" validationKey="modal.validation.non.negative.number" width="3"/>			
			<fc:textfieldControl labelKey="itemDetail.rank" id="rank" validationKey="modal.validation.numeric.number" width="3" />
			<fc:textareaControl labelKey="itemDetail.description" id="longDescription" rows="5"/>
		</div>
	</div>
	<div class="modal-footer">
		<button id="cancelUpdate" class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="modal.btn.close" /></button>
		<button id="submitUpdate" class="btn btn-primary" data-dismiss="modal" aria-hidden="true"><spring:message code="modal.btn.ok" /></button>
	</div>
</div>
</script>