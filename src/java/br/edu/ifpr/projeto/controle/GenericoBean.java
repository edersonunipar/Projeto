/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.projeto.controle;

import br.edu.ifpr.projeto.dao.GenericoDAO;
import br.edu.ifpr.projeto.util.erroSistema;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author edinho
 */
public abstract class GenericoBean<T, D extends GenericoDAO> {
    private String estadoTela = "buscar"; // inserir, editar, buscar
    
    private T entidade;
    private List<T> entidades;
    
    
  //Getters e setters
    public T getEntidade() {
        return entidade;
    }

    public void setEntidade(T entidade) {
        this.entidade = entidade;
    }

    public List<T> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<T> entidades) {
        this.entidades = entidades;
    }
    
    
    //------------------------------
    
    public void novo(){
        entidade = criarNovaEntidade();
        mudarParaInseri();
    }
    
    public void salvar(){
        try {
            getDao().salvar(entidade);
            entidade = criarNovaEntidade();
            adicionarMensagem("Salvo com sucesso", FacesMessage.SEVERITY_INFO);
            mudarParaBusca();
        } catch (erroSistema ex) {
            Logger.getLogger(GenericoBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void editar(T endidade){
        this.entidade = entidade;
        mudarParaEdita();
        
    }
    
    public void deletar(T entidade){
        try {
            getDao().deletar(entidade);
            entidades.remove(entidade);
            adicionarMensagem("Deletado com sucesso", FacesMessage.SEVERITY_INFO);
        } catch (erroSistema ex) {
            Logger.getLogger(GenericoBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    }
    
    public void buscar(){
        if(isBusca() == false){
            mudarParaBusca();
            return;
        }
        try {
            entidades = getDao().buscar();
            if(entidades == null || entidades.size() < 1 ){
                adicionarMensagem("nao tem nada cadastrado", FacesMessage.SEVERITY_WARN);
            }
        } catch (erroSistema ex) {
            Logger.getLogger(GenericoBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro){
        FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    //responsavel por criar medotos na classe bean
    public abstract D getDao();
    public abstract T criarNovaEntidade();
    
    //responsavel por controle da tela
    public boolean isInseri(){
        return "inserir".equals(estadoTela);
    }
    
    public boolean isEdita(){
        return "editar".equals(estadoTela);
    }
    
    public boolean isBusca(){
        return "buscar".equals(estadoTela);
    }
    
    public void mudarParaInseri(){
        estadoTela = "inserir";
    }
    
    public void mudarParaEdita(){
        estadoTela = "editar";
    }
    
    public void mudarParaBusca(){
        estadoTela = "buscar";
    }
}
