package cl.duoc.prueba3.controller;

import cl.duoc.prueba3.entity.Categoria;
import cl.duoc.prueba3.entity.Noticia;
import cl.duoc.prueba3.exception.CategoriaNoEncontradaException;
import cl.duoc.prueba3.exception.NoticiaNoEncontradaException;
import cl.duoc.prueba3.service.CategoriaService;
import cl.duoc.prueba3.service.NoticiaService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/catalogo"})
public class NoticiaServlet extends HttpServlet {

    @EJB
    NoticiaService noticiaService;
    @EJB
    CategoriaService categoriaService;

    private final String JSP_LISTA_NOTICIAS = "/WEB-INF/jsp/noticia/listar.jsp";
    private final String JSP_CREAR = "/WEB-INF/jsp/noticia/crear.jsp";
    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacion = request.getParameter("op");
        operacion = operacion != null ? operacion : "";
        switch (operacion) {
            case "crear":
                request.setAttribute("categorias", categoriaService.getCategorias());
                request.getRequestDispatcher(JSP_CREAR).forward(request, response);
                break;
            case "buscar":
                buscar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            case "listar":
            default:
                listar(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response, List<Noticia> noticias) throws ServletException, IOException {
        List<Categoria> categorias = categoriaService.getCategorias();

        request.setAttribute("noticias", noticias);
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher(JSP_LISTA_NOTICIAS).forward(request, response);
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Noticia> noticias = noticiaService.getNoticias();
        listar(request, response, noticias);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errores = new ArrayList<>();
        List<String> mensajes = new ArrayList<>();
        String error = "";
        String mensaje = "";

        String stringId = request.getParameter("id");
        Long noticiaId = 0L;
        try {
            noticiaId = Long.parseLong(stringId);
            noticiaService.eliminarNoticia(noticiaId);
            mensaje = String.format("Se ha eliminado correctamente la noticia con ID %s", noticiaId);
            logger.log(Level.INFO, mensaje);
            request.setAttribute("mensajes", mensajes);
            mensajes.add(mensaje);
        } catch (NumberFormatException nfe) {
            error = String.format("Formato de ID inválido");
            logger.log(Level.SEVERE, error);
            errores.add(error);
        } catch (NoticiaNoEncontradaException ex) {
            error = String.format("No se pudo encontrar la noticia con el ID especificado");
            logger.log(Level.SEVERE, error);
            errores.add(error);
        }

        listar(request, response);
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String noticiaBuscada = request.getParameter("noticia");
        String stringCategoria = request.getParameter("categoria");
        Long categoriaId = null;
        try {
            if (stringCategoria != null) {
                categoriaId = Long.parseLong(stringCategoria);
            }
        } catch (NumberFormatException nfe) {
            logger.log(Level.INFO, "La Categoría ID entregada no corresponde a un ID válido");
        }
        List<Noticia> productos = noticiaService.buscarNoticia(noticiaBuscada, categoriaId);
        listar(request, response, productos);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errores = new ArrayList<>();
        List<String> mensajes = new ArrayList<>();
        String error = "";
        String mensaje = "";
        
        String titulo = request.getParameter("titulo");
        String stringCategoria =request.getParameter("categoria");
        String imagen = request.getParameter("imagen");
        String descripcion = request.getParameter("descripcion");
        Categoria categoria = null;
        try {
            Long categoriaId = Long.parseLong(stringCategoria);
            categoria = categoriaService.getCategoriaById(categoriaId);
            if(categoria == null) throw new CategoriaNoEncontradaException("No se encontró la categoría asignada al producto");
            Noticia noticia = new Noticia(imagen,titulo,descripcion,stringCategoria);
            noticia = noticiaService.crearNoticia(noticia);
            mensaje = String.format("Noticia %s creada correctamente con ID %s", noticia.getTitulo(),noticia.getId());
            mensajes.add(mensaje);
        } catch(NumberFormatException nfe) {
            errores.add("Formato numérico incompatible");
        } catch (CategoriaNoEncontradaException ex) {
            errores.add(ex.getMessage());
        }
        
        
        request.setAttribute("errores", errores);
        request.setAttribute("mensajes", mensajes);
        request.getRequestDispatcher(JSP_CREAR).forward(request, response);
    }

}
