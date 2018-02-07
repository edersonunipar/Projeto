/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.projeto.controle;

import br.edu.ifpr.projeto.dao.AdministrativoDAO;
import br.edu.ifpr.projeto.dao.GenericoDAO;
import br.edu.ifpr.projeto.entidades.Administrativo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author edinho
 */
@ManagedBean
@SessionScoped
public class AdministrativoBean extends GenericoBean<Administrativo, AdministrativoDAO> {
    
    private AdministrativoDAO administrativoDAO;
    

    @Override
    public AdministrativoDAO getDao() {
    if(administrativoDAO == null){
        administrativoDAO = new AdministrativoDAO();
    }
    return administrativoDAO;
    }

    @Override
    public Administrativo criarNovaEntidade() {
    
        return new Administrativo();
    }
    
}
