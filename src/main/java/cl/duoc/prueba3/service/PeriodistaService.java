/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.prueba3.service;

import cl.duoc.prueba3.entity.Periodista;
import cl.duoc.prueba3.exception.PeriodistaNoEncontradoException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class PeriodistaService implements Serializable{
    
    static final long serialVersionUID = 12L;
    
    @PersistenceContext
    EntityManager em;    
    
    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    // Constructores
    public PeriodistaService() {
    }

    // métodos
    public Periodista crearPeriodista(Periodista periodista) {
        em.persist(periodista);
        return periodista;
    }
    
    public Periodista getPeriodistaId(Long periodistaId) {
        Periodista periodista = em.find(Periodista.class, periodistaId);
        return periodista;
    }
        
    public Periodista findPeriodista(String usuario, String password) {
        String jpql = "SELECT p FROM Periodista p WHERE p.usuario = :usuario AND p.contrasena = :contrasena";
        TypedQuery<Periodista> query = em.createQuery(jpql, Periodista.class);
        query.setParameter("usuario", usuario);
        query.setParameter("contrasena", password);
        try {
            Periodista periodista = query.getSingleResult();
            return periodista;
        } catch (NoResultException nre) {
            return null;
        }
    }
    
    public List<Periodista> getPeriodistas() {
        TypedQuery<Periodista> query = em.createQuery("SELECT p FROM Periodista n", Periodista.class);
        return query.getResultList();
    }
    
    public void eliminarPeriodista(Long periodistaId) throws PeriodistaNoEncontradoException {
        Periodista p = getPeriodistaId(periodistaId);
        if (p == null) {
            String mensajeException = String.format("Periodista con ID %s no encontrada para ser eliminada", periodistaId);
            logger.log(Level.SEVERE, mensajeException);
            throw new PeriodistaNoEncontradoException(mensajeException);
        }
        em.remove(p);
    }
}
