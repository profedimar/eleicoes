/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import config.Conexao;
import java.sql.Date;
import model.Candidato;

/**
 *
 * @author edimar
 */
public class CandidatoDAO {

    public void adicionar(Candidato objeto) throws SQLException, ClassNotFoundException { //alterar a classe do parâmetro
        String sql = "INSERT INTO candidato (sigla_uf, nr_sequencial, cpf, nome, nome_urna, data_nasc, cod_cargo, cod_municipio, nr_partido, cod_sexo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; //alterar a tabela, os atributos e o número de interrogações, conforme o número de atributos

        PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
        //definindo as interrogações (uma linha para cada ? do SQL)
        pstmt.setString(1, objeto.getSiglaUF());
        pstmt.setLong(2, objeto.getNrSequencial()); // alterar o primeiro parâmetro indica a interrogação, começando em 1
        pstmt.setLong(3, objeto.getCpf());
        pstmt.setString(4, objeto.getNome());
        pstmt.setString(5, objeto.getNomeUrna());
        pstmt.setDate(6, new Date(objeto.getDataNascimento().getTime()));
        pstmt.setInt(7, objeto.getCodCargo());
        pstmt.setInt(8, objeto.getCodMunicipio());
        pstmt.setInt(9, objeto.getNrPartido());
        pstmt.setInt(10, objeto.getCodSexo());
        pstmt.executeUpdate(); //executando
    }
}
