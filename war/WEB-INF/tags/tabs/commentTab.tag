<%@include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>
<script type="text/javascript" src="/static/js/tabs/commentTab.js"></script>
<script type="text/template" id="commentTabTemplate">

<div class="row-fluid">
	<textarea class="span12" rows="6" id="commentInput"></textarea>
</div>
<div class="row-fluid">
	<button class="btn btn-primary pull-right" id="addAComment"><spring:message code="tab.comment.btn.add" /></button>
</div>
<@ _.each(comments, function(comment) { @>
	<div class="row-fluid comment">
		<span class="commentUser"><@= comment.userEmail @><span>
		<pre class="offset1 span11"><@=comment.description@></pre>
	</div>
<@ })@>
</script>