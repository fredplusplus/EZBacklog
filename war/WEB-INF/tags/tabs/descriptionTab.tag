<%@include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag  pageEncoding="utf-8"%>
<script type="text/javascript" src="/static/js/tabs/descriptionTab.js" ></script>

<script type="text/template" id="descriptionTemplate" >
<@ if (longDescription) { @>
<div class="row-fluid comment">
	<pre class="span12"><@= longDescription @></pre>
</div>
<@ } @>
</script>