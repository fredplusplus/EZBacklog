<%@include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>

<ul class="nav nav-tabs">
	<li class="active"><a href="#descriptionContainer"
		data-toggle="tab"><spring:message code="itemDetail.description" /></a></li>
	<li><a href="#progressContainer" data-toggle="tab"><spring:message
				code="tab.label.progress" /></a></li>
	<li><a href="#commentContainer" data-toggle="tab"><spring:message
				code="tab.label.comment" /></a></li>
	<li><a href="#relatedItemContainer" data-toggle="tab"> <spring:message
				code="tab.label.relatedItem" /></a></li>
	<li><a href="#auditContainer" data-toggle="tab"><spring:message
				code="tab.label.audit" /></a></li>
</ul>
<div class="tab-content">
	<div class="tab-pane fade in active" id="descriptionContainer"></div>
	<div class="tab-pane fade" id="progressContainer"></div>
	<div class="tab-pane fade" id="commentContainer"></div>
	<div class="tab-pane fade" id="relatedItemContainer"></div>
	<div class="tab-pane fade" id="auditContainer"></div>
</div>