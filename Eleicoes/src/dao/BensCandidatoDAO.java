/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import config.Conexao;
import model.BensCandidato;

/**
 *
 * @author edimar
 */
public class BensCandidatoDAO {

    public void adicionar(BensCandidato objeto) throws SQLException, ClassNotFoundException { //alterar a classe do parâmetro
        String sql = "INSERT INTO bens_candidato (sigla_uf_candidato, nr_sequencial_candidato, cod_tipo_bem, descricao_bem, valor_bem) VALUES (?, ?, ?, ?, ?)"; 
       
        PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
        pstmt.setString(1, objeto.getSiglaUF()); 
        pstmt.setLong(2, objeto.getNrSequencial());
        pstmt.setInt(3, objeto.getCodigoTipoBem());
        pstmt.setString(4, objeto.getDescricao()); 
        pstmt.setDouble(5, objeto.getValor()); 
        pstmt.executeUpdate(); //executando
    }
}
