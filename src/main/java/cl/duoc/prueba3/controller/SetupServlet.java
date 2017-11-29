package cl.duoc.prueba3.controller;

import cl.duoc.prueba3.entity.Categoria;
import cl.duoc.prueba3.entity.Periodista;
import cl.duoc.prueba3.entity.Noticia;
import cl.duoc.prueba3.service.CategoriaService;
import cl.duoc.prueba3.service.PeriodistaService;
import cl.duoc.prueba3.service.NoticiaService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SetupServlet", urlPatterns = {"/setup"})
public class SetupServlet extends HttpServlet {

    @EJB
    CategoriaService categoriaService;

    @EJB
    PeriodistaService periodistaService;
    
    @EJB
    NoticiaService noticiaService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> mensajes = new ArrayList<>();
        List<String> errores = new ArrayList<>();

        try {
            Calendar fechaNacimiento = Calendar.getInstance();
            fechaNacimiento.add(Calendar.YEAR, -20);
            Periodista periodista = new Periodista("Juan Galaz", 13456987, 'k', "lord cochrane 1411","1234",fechaNacimiento,"facha9302","5416658");
            periodistaService.crearPeriodista(periodista);
            mensajes.add( String.format("Periodista %s creado con ID %s", periodista.getUsuario()+":"+periodista.getContrasena(), periodista.getId()) );
 
            
            String titulo="Calule";
            String imagen = "https://i.ytimg.com/vi/QkDSpL3W6qA/hqdefault.jpg";
            String descripcion = "El “Calule” reflexionó sobre la situación de Iquique como líder e invicto en la Copa Chile. Según el ídolo de Colo-Colo y Cobreloa el partido contra Arica será vital..";

            String categoria = "deportes";
            Noticia n = new Noticia(titulo, imagen, descripcion, categoria);
            n = noticiaService.crearNoticia(n);
            Noticia calule = n;
            mensajes.add( String.format("Noticia %s creado con ID %s", n.getTitulo(), n.getId()) );

            
            
        } catch (Exception e) {
            errores.add(e.getMessage());    
        }

        request.setAttribute("mensajes", mensajes);
        request.setAttribute("errores", errores);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

}
