/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import config.Conexao;
import model.TipoBem;

/**
 *
 * @author edimar
 */
public class TipoBemDAO {

    public void adicionar(TipoBem objeto) throws SQLException, ClassNotFoundException { //alterar a classe do parâmetro
        String sql = "INSERT INTO tipo_bem (codigo, descricao) VALUES (?, ?)"; 

        PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
        pstmt.setLong(1, objeto.getCodigo()); 
        pstmt.setString(2, objeto.getDescricao());
        pstmt.executeUpdate(); //executando
    }
}
