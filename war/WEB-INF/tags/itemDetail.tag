<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag  pageEncoding="utf-8"%>
<c:set var="deleteModalId" value="deleteModal" />
<script type="text/template" id="itemDetailTemplate">
<div class="row-fluid">
	<h3><@if (typeof(shortDescription) != 'undefined') { print(shortDescription); }@></h3>
	<div class="btn-toolbar span5">
		<div class="btn-group">
			<@ if (status != 'Deleted') { @>
				<a href="#" class="btn"><spring:message code="itemDetail.action.edit"/></a>
				<a href="#" class="btn">Add a child</a>
				<@if(status=='Open') { @>
					<a href="#" class="btn"><spring:message code="itemDetail.action.resolve"/></a>
				<@ } else if (status=='Resolved'){ @>
					<a href="#" class="btn"><spring:message code="itemDetail.action.reopen"/></a>
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
			<dd>
				<@if (itemLevel == 1) { @>
					<spring:message code="itemLevel.1" />
				<@} else if (itemLevel == 2) { @>
					<spring:message code="itemLevel.2" />
				<@} else if (itemLevel == 3) { @>
					<spring:message code="itemLevel.3" />
				<@} @>
			</dd>
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
			<dd><@ if (typeof(point) != 'undefined') {print(point);} @></dd>
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
			<@if (typeof(resolvedPoint) == 'undefined') { resolvedPoint = 0 } @>
			<div class="bar bar-success" style="width: <@=resolvedPoint/point@>%" />
		</div>
	</div>
</div>
<div class="row-fluid">
	<dl>
		<dt>Description</dt>
		<dd><@if(typeof(longDescription) != 'undefined') { print(longDescription);} @></dd>
	</dl>
</div>
<ez:simpleModal id="deleteModal" textKey="modal.text.deleteItem" labelKey="modal.title.confirm"/>
</script>