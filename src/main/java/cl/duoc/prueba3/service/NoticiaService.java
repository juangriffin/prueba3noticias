package cl.duoc.prueba3.service;

import cl.duoc.prueba3.entity.Noticia;
import cl.duoc.prueba3.exception.NoticiaNoEncontradaException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class NoticiaService implements Serializable{
    
    static final long serialVersionUID = 12L;
    
    @PersistenceContext
    EntityManager em;    
    
    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    // Constructores
    public NoticiaService() {
    }

    // métodos
    public Noticia crearNoticia(Noticia noticia) {
        em.persist(noticia);
        return noticia;
    }
    
    public Noticia getNoticiaById(Long noticiaId) {
        Noticia noticia = em.find(Noticia.class, noticiaId);
        return noticia;
    }
    
    public List<Noticia> getNoticias() {
        TypedQuery<Noticia> query = em.createQuery("SELECT n FROM Noticia n", Noticia.class);
        return query.getResultList();
    }
    
    public void eliminarNoticia(Long noticiaId) throws NoticiaNoEncontradaException {
        Noticia n = getNoticiaById(noticiaId);
        if (n == null) {
            String mensajeException = String.format("Noticia con ID %s no encontrada para ser eliminada", noticiaId);
            logger.log(Level.SEVERE, mensajeException);
            throw new NoticiaNoEncontradaException(mensajeException);
        }
        em.remove(n);
    }
    
    public List<Noticia> buscarNoticia(String nombreNoticia, Long categoriaId) {
        if (nombreNoticia != null && !nombreNoticia.isEmpty() && categoriaId != null && categoriaId > 0) {
            String jpql = "SELECT n FROM Noticia n WHERE LOWER(n.titulo) LIKE :titulo AND n.categoria.id = :categoriaId";
            TypedQuery<Noticia> query = em.createQuery(jpql, Noticia.class);
            query.setParameter("titulo", "%" + nombreNoticia + "%");
            query.setParameter("categoriaId", categoriaId);
            return query.getResultList();
        }

        if (nombreNoticia != null && !nombreNoticia.isEmpty()) {
            String jpql = "SELECT n FROM Noticia n WHERE LOWER(n.titulo) LIKE :titulo";
            TypedQuery<Noticia> query = em.createQuery(jpql, Noticia.class);
            query.setParameter("nombre", "%" + nombreNoticia + "%");
            return query.getResultList();
        }

        if (nombreNoticia == null || nombreNoticia.isEmpty()) {
            if (categoriaId != null && categoriaId > 0) {
                String jpql = "SELECT n FROM Noticia n WHERE n.categoria.id = :categoriaId";
                TypedQuery<Noticia> query = em.createQuery(jpql, Noticia.class);                
                query.setParameter("categoriaId", categoriaId);
                return query.getResultList();
            }

        }

        // sino devuelve la lista completa de productos
        return getNoticias();
    }
}
