/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.prueba3.exception;

public class CategoriaNoEncontradaException extends Exception {

    public CategoriaNoEncontradaException() {
    }
   
    public CategoriaNoEncontradaException(String msg) {
        super(msg);
    }
}
