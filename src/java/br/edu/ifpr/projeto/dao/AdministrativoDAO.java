/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.projeto.dao;

import br.edu.ifpr.projeto.entidades.Administrativo;
import br.edu.ifpr.projeto.entidades.Professor;
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
public class AdministrativoDAO  implements GenericoDAO<Administrativo>{

    @Override
    public void salvar(Administrativo entidade) throws erroSistema {
     
         try {
        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement ps;
        if(entidade.getId() == null){
            ps = conexao.prepareCall("INSERT INTO `administrativo`(`nome`,`cpf`,`rg`,`Curso`,`nivel`, `carga_horaria`, id_qualificacao) VALUES(?,?,?,?,?,?)"); 
        } else{
            ps = conexao.prepareStatement("update administrativo set nome=?, cpf=?, rg=?, Curso=?, nivel=?, carga_horaria=?, id_qualificacao=? where id=?");
            ps.setInt(6, entidade.getId());
        } 
            ps.setString(1,entidade.getNome());
            ps.setInt(2,entidade.getCpf());
            ps.setInt(3,entidade.getRg());
            ps.setString(4, entidade.getCurso());
            ps.setString(5, entidade.getNivel());
            ps.setString(6, entidade.getCarga_horaria());
            ps.setInt(7, entidade.getQualificacao().getId());
            
            ps.execute();
            FabricaConexao.fecharConexao();
        } 
        catch (SQLException ex) {
            throw new erroSistema("Erro ao busca funcionario", ex);
        }


    }

    @Override
    public void deletar(Administrativo entidade) throws erroSistema {
    
     Connection conexao = FabricaConexao.getConexao();
     PreparedStatement ps;
        try {
            ps = conexao.prepareStatement("delete from administrativo where id=?");
              ps.setInt(1, entidade.getId());
              ps.execute();
        } catch (SQLException ex) {
            throw new erroSistema("Erro ao deletar o funcionario", ex);
        }
   
    }


    

    @Override
    public List<Administrativo> buscar() throws erroSistema {
    try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from administrativo");
            ResultSet resultSet = ps.executeQuery();
            List<Administrativo> administrativos = new ArrayList<>();
            while(resultSet.next()){
                Administrativo administrativo = new Administrativo();
                administrativo.setId(resultSet.getInt("id"));
                administrativo.setNome(resultSet.getString("nome"));
                administrativo.setCpf(resultSet.getInt("cpf"));
                administrativo.setRg(resultSet.getInt("rg"));
                administrativo.setCurso(resultSet.getString("Curso"));
                administrativo.setNivel(resultSet.getString("nivel"));
                administrativo.setCarga_horaria(resultSet.getString("carga_horaria"));
                administrativo.setQualificacao(resultSet.getInt("id_estado"));
                administrativos.add(administrativo);
            }
            FabricaConexao.fecharConexao();
            return administrativos;
        } catch (SQLException ex) {
           throw new erroSistema("Erro ao salvar o funcionario", ex);
                
}

    
    
    
}
    
    
    
}