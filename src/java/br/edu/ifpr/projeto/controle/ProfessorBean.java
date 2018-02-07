/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.projeto.controle;

import br.edu.ifpr.projeto.dao.ProfessorDAO;
import br.edu.ifpr.projeto.dao.GenericoDAO;
import br.edu.ifpr.projeto.entidades.Professor;
import br.edu.ifpr.projeto.util.erroSistema;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author edinho
 */
@ManagedBean
@SessionScoped
public class ProfessorBean extends GenericoBean<Professor, ProfessorDAO>{
    
    private ProfessorDAO professorDAO;

    @Override
    public ProfessorDAO getDao() {
    if(professorDAO == null){
        professorDAO = new ProfessorDAO();
    }
    return professorDAO;
    }
    
    

    @Override
    public Professor criarNovaEntidade() {
    return new Professor();
    }

  
    
   
    
    
    
}
