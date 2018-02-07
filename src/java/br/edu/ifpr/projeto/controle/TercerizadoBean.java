/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.projeto.controle;

import br.edu.ifpr.projeto.dao.ProfessorDAO;
import br.edu.ifpr.projeto.dao.TercerizadoDAO;
import br.edu.ifpr.projeto.entidades.Professor;
import br.edu.ifpr.projeto.entidades.Tercerizado;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author edinho
 */
@ManagedBean
@SessionScoped
public class TercerizadoBean extends GenericoBean<Tercerizado, TercerizadoDAO> {
    
    private TercerizadoDAO tercerizadoDAO;

    @Override
    public TercerizadoDAO getDao() {
    if(tercerizadoDAO == null){
        tercerizadoDAO = new TercerizadoDAO();
    }
    return tercerizadoDAO;
    }
    
    

    @Override
    public Tercerizado criarNovaEntidade() {
    return new Tercerizado();
    }
    
}
