<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<html>
<head>
  <title>JSTL: XML Support -- Parse / Filter / Out</title>
</head>
<body bgcolor="#FFFFFF">
<h3>Parse / Filter / Out</h3>

<x:parse var="a" filter="${spath}">
 <nope>
  <a>
   <b>
    <c>
     foo
    </c>
   </b>
   <d>
     bar
   </d>
  </a>
 </nope>
</x:parse>

<x:out select="$a//c"/>
<x:out select="$a/a/d"/>

<hr />

</body>
</html>
