<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="utf-8" %>

<div class="stickyFooter">
	<h5>
	<a href="https://github.com/fredplusplus/EZBacklog/issues"><spring:message code="footer.sticky.issue"/></a>
	</h5>
</div>
<footer class="footer">
	<div class="container">
		<p><spring:message code="footer.browser"/></p>
		<p><spring:message code="footer.tools"/></p>
		<p><spring:message code="footer.copyright"/></p>
	</div>
</footer>

<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-37820703-1']);
  _gaq.push(['_setSiteSpeedSampleRate', 100]);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>