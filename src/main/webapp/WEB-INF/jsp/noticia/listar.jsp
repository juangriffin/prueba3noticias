<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Noticias</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <jsp:include page="/WEB-INF/jspf/header.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />

            <!-- formulario de búsqueda -->
            <form method="get" action="catalogo">
                <input type="hidden" name="op" value="buscar" />
                <div class="form-row align-items-center">
                    <div class="col-6">
                        <label class="sr-only" for="noticia">Noticia</label>
                        <input type="text" name="noticia" id="noticia" value="${fn:escapeXml(noticiaBuscadsa)}" class="form-control form-control-lg mb-2 mb-sm-0" placeholder="Ingrese el nombre de la noticia a buscar">
                    </div>
                    <div class="col-auto">
                        <label class="sr-only" for="categoria">Categoría</label>
                        <select name="categoria" id="categoria" class="custom-select mb-2 mr-sm-2 mb-sm-0">
                            <option selected>Escoja una categoría</option>
                            <c:forEach items="${categorias}" var="c">
                                <option value="${c.id}" ${c.id == categoriaBuscada?'selected="true"':''}>${c.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-warning">Buscar</button>
                    </div>
                </div>
            </form>
            <!-- END formulario de búsqueda -->


            <jsp:include page="/WEB-INF/jspf/mensajes.jsp" />

            <div class="row">
                <div class="col">
                    <a href="catalogo?op=crear" class="btn btn-success fa fa-plus"> Crear</a>
                </div>
            </div>
            
            <c:if test="${empty noticias}">
                No hay noticias para mostrar.
            </c:if>            

            <c:if test="${!empty noticias}">
                <!-- tabla con noticias -->
                <table class="table table-striped">
                    <thead class="thead-inverse">
                        <tr>
                            <th>ID</th>
                            <th>Imagen</th>
                            <th>Titulo</th>
                            <th>Desarrollo</th>
                            <th>Categoría</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${noticias}" var="n">
                            <tr>
                                <th>${n.id}</th>
                                <td>
                                    <img src="${n.imagen}" alt="${n.nombre}" style="height: 100px; width: auto;" />
                                </td>
                                <td>${n.nombre}</td>
                                
                                <td>${n.categoria.nombre}</td>

                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>

        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>