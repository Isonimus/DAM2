<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Ganadores</h1>

<ol>
  <c:forEach items="${nombreganador}" var="ng">
  <li>${ng}</li>
  </c:forEach>
</ol>

<h2>Premios</h2>

<ol>
  <c:forEach items="${premios}" var="pre">
  <li>${pre.nombre} - ${pre.valor} &euro; </li>
  </c:forEach>
</ol>

<a href="${pageContext.request.contextPath}">Ir a la pagina principal</a>	