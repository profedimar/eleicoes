/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readers;

import config.Conexao;
import dao.CargoDAO;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cargo;

/**
 *
 * @author edimar
 */
public class CargoReader {

    private Map<Integer, String> cargos = new HashMap<>();
    private CargoDAO dao = new CargoDAO();

    public void read(String file) throws SQLException, ClassNotFoundException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Latin1"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\"", "");
                String[] partes = line.split(";");
                cargos.put(Integer.parseInt(partes[8]), partes[9]);
            }
        } catch (IOException ex) {
            Logger.getLogger(CargoReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        makeinsertions();

    }

    private void makeinsertions() throws SQLException, ClassNotFoundException {
        Conexao.getConexao().setAutoCommit(false);
        int nr = 0;
        for (Integer key : cargos.keySet()) {
            Cargo objeto = new Cargo();
            objeto.setCodigo(key);
            objeto.setDescricao(cargos.get(key));

            dao.adicionar(objeto);
            nr++;
        }
        Conexao.getConexao().commit();
        System.out.println("Cargos inseridos: " + nr);
    }

    public static void main(String[] args) {
        CargoReader cr = new CargoReader();
        try {
            cr.read("/home/edimar/Área de Trabalho/eleicoes_2016/consulta_cand_2016/consulta_cand_2016_AC.csv");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CargoReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
