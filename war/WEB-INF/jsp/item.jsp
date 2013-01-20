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
<ez:bender />
<ez:itemList/>
<ez:itemDetail/>
<script type="text/javascript">
	var itemCollection = new ItemCollection(${Items});
	var itemCollectionView = new ItemCollectionView({el : $("#itemCollectionContainer"), collection: itemCollection});
	var benderView = new BenderView({collection: itemCollection});
</script>