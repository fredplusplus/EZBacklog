<%@include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag  pageEncoding="utf-8"%>
<c:set var="deleteModalId" value="deleteModal" />
<script type="text/template" id="itemDetailTemplate">
<div class="row-fluid">
	<h3><@if (typeof(shortDescription) != 'undefined') { print(shortDescription); }@></h3>
	<div class="btn-toolbar span5">
		<div class="btn-group">
			<@if (itemLevel < 3) { @>
				<@if (status == 'Open') {@>
					<a href="#" class="btn" id="createChild"><spring:message code="itemDetail.action.addChild" /></a>
				<@ } @>
				<a href="/f/item/<@=id@>" class="btn" id="seeChildren"><spring:message code="itemDetail.action.seeChildren" /></a>
			<@ } @>
			<@ if (status != 'Deleted') { @>
				<a href="#" class="btn" id="edit"><spring:message code="itemDetail.action.edit"/></a>
				<@if(status=='Open') { @>
					<a href="#" class="btn" id="updateProgressBtn"><spring:message code="itemDetail.action.updateProgress"/></a>					
					<a href="#" class="btn" id="resolveBtn"><spring:message code="itemDetail.action.resolve"/></a>
				<@ } else if (status=='Resolved'){ @>
					<a href="#" class="btn" id="reopenBtn"><spring:message code="itemDetail.action.reopen"/></a>
				<@ } @>
				<a href="#" class="btn btn-danger" data-toggle="modal" data-target="#${deleteModalId}"><spring:message code="itemDetail.action.delete"/></a>
			<@ } @>
		</div>
	</div>
</div>
<div class="row-fluid">
	<div class="span4">
		<dl class="dl-horizontal">
			<dt><spring:message code="itemDetail.level"/></dt>
			<dd><item:itemType key="itemLevel" displayPrefix="itemLevel" /></dd>
			<dt><spring:message code="itemDetail.createdate"/></dt>
			<dd><@ if (typeof(creationDate) != 'undefined') {print(formatDate(creationDate));} @></dd>
			<dt><spring:message code="itemDetail.status"/></dt>
			<dd>
				<@ if (status =='Open') {@>
					<spring:message code="dashboard.openproject" />
				<@} else if (status == 'Resolved'){@>
					<spring:message code="dashboard.resolvedproject" />
				<@} else if (status == 'Deleted') {@>
					<spring:message code="dashboard.deletedproject" />
				<@} @>
			</dd>
		</dl>
	</div>
	<div class="span4">
		<dl class="dl-horizontal">
			<dt><spring:message code="itemDetail.id"/></dt>
			<dd><@=id@></dd>
			<dt><spring:message code="itemDetail.modifieddate"/></dt>
			<dd><@ if (typeof(modifyDate) != 'undefined') {print(formatDate(modifyDate));} @>&nbsp;</dd>
			<dt><spring:message code="itemDetail.originalpoint"/></dt>
			<dd><@= point @></dd>
		</dl>
	</div>
	<div class="span4">
		<dl class="dl-horizontal">
			<dt><spring:message code="itemDetail.rank"/></dt>
			<dd><@ if (typeof(rank)!='undefined') {print(rank);} @></dd>
			<dt><spring:message code="itemDetail.resolveddate"/></dt>
			<dd><@ if (typeof(resolveDate) != 'undefined') {print(formatDate(resolveDate));} @>&nbsp;</dd>
			<dt><spring:message code="itemDetail.resolvedpoint"/></dt>
			<dd><@ if (typeof(resolvedPoint) != 'undefined') {print(resolvedPoint);} @></dd>
		</dl>
	</div>
</div>
<div class="row-fluid">
	<div class="span1"><strong><spring:message code="itemDetail.progress" /></strong></div>
	<div class="span8 offset1">
		<div class="progress">
			<@if (point == 0) { point = 1; } @>
			<@if (resolvedPoint <= point) { @>
				<div class="bar bar-success" style="width: <@= (resolvedPoint * 100) / point@>%" />
			<@ } else { @>
				<div class="bar bar-success" style="width: <@= (point * 100) / resolvedPoint@>%" />
				<div class="bar bar-warning" style="width: <@= ((resolvedPoint - point) * 100) / resolvedPoint@>%" />
			<@ } @>
		</div>
	</div>
</div>
<@ if (longDescription) { @>
<div class="row-fluid">
	<strong><spring:message code="itemDetail.description" /></strong>
</div>
<div class="row-fluid">
	<pre class="offset1 span11"><@= longDescription @></pre>
</div>
<@ } @>
<modal:simpleModal id="${deleteModalId}" textKey="modal.text.deleteItem" labelKey="modal.title.confirm"/>
</script>