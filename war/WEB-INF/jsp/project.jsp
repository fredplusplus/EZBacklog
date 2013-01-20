<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<ez:itemAggregation />
<script type="text/javascript" src="/static/js/Item.js" ></script>
<div class="container-fluid">
	<div class="row-fluid" >
		<div class="span5" id="itemCollectionContainer"></div>
		<div class="span6" id="itemDetailContainer"></div>
	</div>
</div>
<script type="text/template" id="itemCollectionTemplate" >
<ez:itemList/>
</script>
<script type="text/template" id="itemDetailTemplate">
<ez:itemDetail/>
</script>
<script type="text/javascript">
	var itemCollection = new ItemCollection(${Items});
	var itemCollectionView = new ItemCollectionView({el : $("#itemCollectionContainer"), collection: itemCollection});
</script>