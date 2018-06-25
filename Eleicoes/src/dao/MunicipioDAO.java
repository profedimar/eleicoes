/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import config.Conexao;
import model.Municipio;

/**
 *
 * @author edimar
 */
public class MunicipioDAO {

    public void adicionar(Municipio objeto) throws SQLException, ClassNotFoundException { //alterar a classe do parâmetro
        String sql = "INSERT INTO municipio (codigo, nome, uf) VALUES (?, ?, ?)"; 

        PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
        pstmt.setInt(1, objeto.getCodigo()); 
        pstmt.setString(2, objeto.getNome());
        pstmt.setString(3, objeto.getUf());
        pstmt.executeUpdate(); //executando
    }
}
