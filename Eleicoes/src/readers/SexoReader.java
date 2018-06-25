/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readers;

import config.Conexao;
import dao.SexoDAO;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Sexo;

/**
 *
 * @author edimar
 */
public class SexoReader {

    private Map<Integer, String> sexos = new HashMap<>();
    private SexoDAO dao = new SexoDAO();

    public void read(String file) throws SQLException, ClassNotFoundException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Latin1"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\"", "");
                String[] partes = line.split(";");
                sexos.put(Integer.parseInt(partes[29]), partes[30]);
            }
        } catch (IOException ex) {
            Logger.getLogger(SexoReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        makeinsertions();

    }

    private void makeinsertions() throws SQLException, ClassNotFoundException {
        Conexao.getConexao().setAutoCommit(false);
        int nr = 0;
        for (Integer key : sexos.keySet()) {
            Sexo objeto = new Sexo();
            objeto.setCodigo(key);
            objeto.setDescricao(sexos.get(key));

            dao.adicionar(objeto);
            nr++;
        }
        Conexao.getConexao().commit();
        System.out.println("Sexo inseridos: " + nr);
    }

    public static void main(String[] args) {
        SexoReader cr = new SexoReader();
        try {
            cr.read("C:\\Users\\Administrador\\Desktop\\eleicoes\\consulta_cand_2016/consulta_cand_2016_AC.txt");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SexoReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
