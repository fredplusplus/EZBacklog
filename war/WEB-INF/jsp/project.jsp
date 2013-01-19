<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<ez:itemAggregation />
<script type="text/javascript" src="/static/js/Item.js" ></script>
<div class="container-fluid">
	<div class="row-fluid" id="itemCollectionContainer">
	</div>
</div>
${Items}
<script type="text/template" id="itemCollectionTemplate" >
	<ez:itemList/>
	<div class="span6">test</div>
</script>
<script type="text/javascript">
	var itemCollection = new ItemCollection(${Items});
	var itemCollectionView = new ItemCollectionView({el : $("#itemCollectionContainer"), collection: itemCollection});
</script>