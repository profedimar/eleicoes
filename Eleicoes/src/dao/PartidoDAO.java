/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import config.Conexao;
import model.Partido;

/**
 *
 * @author edimar
 */
public class PartidoDAO {

    public void adicionar(Partido objeto) throws SQLException, ClassNotFoundException { //alterar a classe do parâmetro
        String sql = "INSERT INTO partido (numero, nome, sigla) VALUES (?, ?, ?)"; 

        PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
        pstmt.setInt(1, objeto.getNumero()); 
        pstmt.setString(2, objeto.getNome());
        pstmt.setString(3, objeto.getSigla());
        pstmt.executeUpdate(); //executando
    }
}
