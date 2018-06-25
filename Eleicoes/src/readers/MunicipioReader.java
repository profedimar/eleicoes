/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readers;

import dao.MunicipioDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Municipio;

/**
 *
 * @author edimar
 */
public class MunicipioReader {

    private MunicipioDAO dao = new MunicipioDAO();

    public void read(String dir) {
        File[] files = new File(dir).listFiles();
        for (File file : files) {
            if (file.getName().endsWith(".txt")) {
                System.out.println("Lendo o arquivo: "+file.getName());
                read(file);
            }
        }
    }

    private void read(File file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Latin1"))) {
            String line;
            int nr = 0;
            while ((line = reader.readLine()) != null) {
                try {
                    insertLine(line);
                    nr++;
                } catch (SQLException | ClassNotFoundException ex) {
                    // não faz nada pq já inseriu esse município
                }
            }
            System.out.println("Municípios inseridos: " + nr);
        } catch (IOException ex) {
            Logger.getLogger(MunicipioReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertLine(String line) throws SQLException, ClassNotFoundException {
        line = line.replaceAll("\"", "");
        String[] partes = line.split(";");

        Municipio objeto = new Municipio();
        objeto.setCodigo(Integer.parseInt(partes[6]));
        objeto.setNome(partes[7]);
        objeto.setUf(partes[5]);

        dao.adicionar(objeto);
    }

    public static void main(String[] args) {
        MunicipioReader cr = new MunicipioReader();
        cr.read("/home/edimar/Área de Trabalho/eleicoes_2016/consulta_cand_2016");
    }
}
