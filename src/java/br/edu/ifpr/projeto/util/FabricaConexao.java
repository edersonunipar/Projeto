/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.projeto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author edinho
 */
public class FabricaConexao {
    
    private static Connection conexao;
    private static final String URL_CONEXAO = "jdbc:mysql://localhost/projeto_ifpr";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";
    

    
    public static Connection getConexao() throws erroSistema{
        if(conexao == null){
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conexao = DriverManager.getConnection(URL_CONEXAO , USUARIO , SENHA);
                    
                } catch (SQLException ex) {
            throw new erroSistema("Nao foi possivel conexão com o banco", ex);
                }     
   
                 catch (ClassNotFoundException ex) {
                throw new erroSistema("O driver nao foi encontrado", ex);
                 }
 
        }
        return conexao;
    }
    public static void fecharConexao() throws erroSistema{
        if(conexao != null){
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                throw new erroSistema("Erro ao fechar conexão com bd", ex);
            }
            
        }
    }
}
    
    

