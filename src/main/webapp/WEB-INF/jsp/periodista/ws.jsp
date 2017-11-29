<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
{
    "id": ${periodista.id}
    , "rut": ${periodista.rut}
    , "dv": "${periodista.dv}"
    , "nombre": "${periodista.nombre}"
    , "fechaNacimiento": "<fmt:formatDate value="${periodista.fechaNacimiento.time}" pattern="yyyy-MM-dd" />"
    , "comuna": "${periodista.comuna}"
    , "direccion": "${periodista.direccion}"
}
                       
                                