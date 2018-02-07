/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.projeto.dao;

import br.edu.ifpr.projeto.util.erroSistema;
import java.util.List;

/**
 *
 * @author edinho
 */
public interface GenericoDAO<T> {// T quer dizer que representa a endidade
    
    public void salvar(T entidade) throws erroSistema;
    public void deletar(T entidade) throws erroSistema;
    public List<T> buscar() throws erroSistema;
    
    
    
}
