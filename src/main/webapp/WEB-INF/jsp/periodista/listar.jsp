<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Periodistas</title>
        
        <jsp:include page="/WEB-INF/jspf/header.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />
            <jsp:include page="/WEB-INF/jspf/mensajes.jsp" />


            <div class="row">
                <div class="col">
                    <a href="periodista?op=crear" class="btn btn-success fa fa-plus"> Crear</a>
                </div>
            </div>


            <c:if test="${empty periodistas}">
                No hay periodista para mostrar.
            </c:if>            

            <c:if test="${!empty periodistas}">
                <!-- tabla con categorías -->
                <table class="table table-striped">
                    <thead class="thead-inverse">
                        <tr>
                            <th>ID</th>
                            <th>RUT</th>
                            <th>Nombre</th>
                            <th>Fecha Nacimiento</th>
                            <th>Comuna</th>
                            <th>Dirección</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${periodistas}" var="p">
                            <tr>
                                <td>${p.id}</td>
                                <td>${p.rut}-${c.dv}</td>
                                <td>${p.nombre}</td>
                                <td>
                                    <fmt:formatDate value="${p.fechaNacimiento.time}" pattern="dd MMMM yyyy" />
                                </td>
                                <td>${p.comuna}</td>
                                <td>${p.direccion}</td>
                                <td>
                                    <form method="get" action="periodistas">
                                        <input type="hidden" name="op" value="eliminar" />
                                        <input type="hidden" name="id" value="${p.id}" />
                                        <button type="submit" class="btn btn-danger">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>


        </div>

        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>