/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.projeto.dao;

import br.edu.ifpr.projeto.entidades.Professor;
import br.edu.ifpr.projeto.util.FabricaConexao;
import br.edu.ifpr.projeto.util.erroSistema;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edinho
 */
public class ProfessorDAO implements GenericoDAO<Professor>{
    
    @Override
    public void salvar(Professor professor) throws erroSistema{
         try {
        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement ps;
        if(professor.getId() == null){
            ps = conexao.prepareCall("INSERT INTO `professor`(`nome`,`rg`,`cargo`,`setor`,`nivel , id_qualificacao`) VALUES(?,?,?,?,?,?)"); 
        } else{
            ps = conexao.prepareStatement("update professor set nome=?, rg=?, cargo=?, setor=?, nivel=?, id_qualificacao=? where id=?");
            ps.setInt(6, professor.getId());
        } 
            ps.setString(1,professor.getNome());
            ps.setString(2,professor.getRg());
            ps.setString(3,professor.getCargo());
            ps.setString(4, professor.getSetor());
            ps.setString(5, professor.getNivel());
            ps.setInt(6, professor.getQualificacao().getId());
            ps.execute();
            FabricaConexao.fecharConexao();
        } 
        catch (SQLException ex) {
            throw new erroSistema("Erro ao buscar o funcionario", ex);
        }
    }
    @Override
    public List<Professor> buscar() throws erroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from professor");
            ResultSet resultSet = ps.executeQuery();
            List<Professor> professores = new ArrayList<>();
            while(resultSet.next()){
                Professor professor = new Professor();
                professor.setId(resultSet.getInt("id"));
                professor.setNome(resultSet.getString("nome"));
                professor.setRg(resultSet.getString("rg"));
                professor.setCargo(resultSet.getString("cargo"));
                professor.setSetor(resultSet.getString("setor"));
                professor.setNivel(resultSet.getString("nivel"));
                professor.setQualificacao(resultSet.getInt("id_estado"));
                professores.add(professor);
            }
            FabricaConexao.fecharConexao();
            return professores;
        } catch (SQLException ex) {
           throw new erroSistema("Erro ao salvar o professor", ex);
                
        }

    }
    
    @Override
    public void deletar(Professor professor) throws erroSistema{
     Connection conexao = FabricaConexao.getConexao();
     PreparedStatement ps;
        try {
            ps = conexao.prepareStatement("delete from professor where id=?");
              ps.setInt(1, professor.getId());
              ps.execute();
        } catch (SQLException ex) {
            throw new erroSistema("Erro ao deletar o funcionario", ex);
        }
   
    }
    
    
            
    
}
