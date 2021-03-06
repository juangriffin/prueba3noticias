<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Creación y Edición de Productos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <jsp:include page="/WEB-INF/jspf/header.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />

            <div class="row">
                <div class="col col-lg-6">
                    <h1>Cree y Edite Noticias</h1>
                    
                    <jsp:include page="/WEB-INF/jspf/mensajes.jsp" />
                    
                    <form method="post" action="catalogo">
                        <div class="form-group">
                            <label for="id">ID</label>
                            <input type="number" class="form-control" id="id" name="id" readonly="readonly" aria-describedby="id-help">
                            <small id="id-help" class="form-text text-muted">El ID del producto se autogenera, solo se cargará cuando se edita una noticia de manera informativa</small>
                        </div>
                        <div class="form-group">
                            <label for="noticia">Noticia</label>
                            <input type="text" class="form-control" id="noticia" name="noticia" placeholder="Ingrese el nombre de su noticia" aria-describedby="noticia-help">
                            <small id="noticia-help" class="form-text text-muted">Ejemplo: Perro roba empanada</small>
                        </div>
                        <div class="form-group">
                            <label for="categoria">Categoría</label>
                            <select class="form-control" name="categoria" id="categoria">
                                <option value="0">Seleccione una categoría</option>
                                <c:forEach items="${categorias}" var="c">
                                    <option value="${c.id}">${c.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                         <div class="form-group">
                            <label>Titulo</label>
                            <textarea name="titulo" class="form-control"></textarea>
                            <small class="form-text text-muted">titulo de la notici</small>
                        </div>
                        
                        <div class="form-group">
                            <label for="producto">Imagen</label>
                            <div class="input-group">
                                <span class="input-group-addon">http://</span>
                                <input type="text" class="form-control" id="imagen" name="imagen" placeholder="Ingrese la URL de la imagen" aria-describedby="imagen-help">
                            </div><!-- end input-group-->
                            <small id="imagen-help" class="form-text text-muted">Ejemplo: http://www.site.com/a/imagen.jpg</small>
                        </div>
                        <div class="form-group">
                            <label>Desarrollo</label>
                            <textarea name="desarrollo" class="form-control"></textarea>
                            <small class="form-text text-muted">Desarrollo de la noticia</small>
                        </div>

                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </form>
                </div>
            </div><!-- end col-->
        </div><!-- end row-->

        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>