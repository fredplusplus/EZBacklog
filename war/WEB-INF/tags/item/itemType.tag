<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>
<%@attribute name="displayPrefix" required="true"%>
<%@attribute name="key" required="true"%>

<@ if (${key}==1) {@> <spring:message code="${displayPrefix}.1" /> <@} @>
<@ if (${key}==2) {@> <spring:message code="${displayPrefix}.2" /> <@} @>
<@ if (${key}==3) {@> <spring:message code="${displayPrefix}.3" /> <@} @>
