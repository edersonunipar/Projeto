/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.projeto.util;

/**
 *
 * @author edinho
 */
public class erroSistema extends Exception{

    public  erroSistema(String message) {
        super(message);
    }
    
    public erroSistema(String message, Throwable cause){
        super(message, cause);
    }
    
}
