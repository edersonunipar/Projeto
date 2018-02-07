/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.projeto.dao;

import br.edu.ifpr.projeto.entidades.Administrativo;
import br.edu.ifpr.projeto.entidades.Tercerizado;
import br.edu.ifpr.projeto.util.FabricaConexao;
import br.edu.ifpr.projeto.util.erroSistema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edinho
 */
public class TercerizadoDAO implements GenericoDAO<Tercerizado>{
    
    @Override
    public void salvar(Tercerizado entidade) throws erroSistema {
     
         try {
        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement ps;
        if(entidade.getId() == null){
            ps = conexao.prepareCall("INSERT INTO `tercerizado`(`nome`,`rg`,`cargo`,`empresa`) VALUES(?,?,?,?)"); 
        } else{
            ps = conexao.prepareStatement("update tercerizado set nome=?, rg=?, cargo=?, empresa=? where id=?");
            ps.setInt(5, entidade.getId());
        } 
            ps.setString(1,entidade.getNome());
            ps.setInt(2,entidade.getRg());
            ps.setString(3, entidade.getCargo());
            ps.setString(4, entidade.getEmpresa());
           
            ps.execute();
            FabricaConexao.fecharConexao();
        } 
        catch (SQLException ex) {
            throw new erroSistema("Erro ao busca funcionario", ex);
        }


    }

    @Override
    public void deletar(Tercerizado entidade) throws erroSistema {
    
     Connection conexao = FabricaConexao.getConexao();
     PreparedStatement ps;
        try {
            ps = conexao.prepareStatement("delete from tercerizado where id=?");
              ps.setInt(1, entidade.getId());
              ps.execute();
        } catch (SQLException ex) {
            throw new erroSistema("Erro ao deletar o funcionario", ex);
        }
   
    }


    

    @Override
    public List<Tercerizado> buscar() throws erroSistema {
    try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from tercerizado");
            ResultSet resultSet = ps.executeQuery();
            List<Tercerizado> tercerizados = new ArrayList<>();
            while(resultSet.next()){
                Tercerizado tercerizado = new Tercerizado();
                tercerizado.setId(resultSet.getInt("id"));
                tercerizado.setNome(resultSet.getString("nome"));
                tercerizado.setRg(resultSet.getInt("rg"));
                tercerizado.setCargo(resultSet.getString("cargo"));
                tercerizado.setEmpresa(resultSet.getString("empresa"));
                
                tercerizados.add(tercerizado);
            }
            FabricaConexao.fecharConexao();
            return tercerizados;
        } catch (SQLException ex) {
           throw new erroSistema("Erro ao salvar o funcionario", ex);
                
}

    
    
    
}
    
}
