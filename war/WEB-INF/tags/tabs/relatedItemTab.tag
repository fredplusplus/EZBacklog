<%@include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag  pageEncoding="utf-8"%>
<script type="text/javascript" src="/static/js/tabs/relatedItemTab.js" ></script>
<script type="text/template" id="relatedItemTemplate">
<div class="row-fluid">
	<h5 class="offset1"><spring:message code="tab.relatedItem.relatedLinks" /></h5>
</div>
<div class="input-append">
	<input class="span7 offset1" id="addALinkInput" type="text" >
	<button class="btn" type="button" id="addALink"><spring:message code="tab.relatedItem.addALink" /></button>
</div>
<ul class="offset2">
<@ _.each(relatedItems, function(relatedItem) { @>
	<li>
		<a target="_blank" href="<@=relatedItem.relatedItem @>"><@=relatedItem.relatedItem @></a>
	</li>
<@ })@>
</ul>
</script>