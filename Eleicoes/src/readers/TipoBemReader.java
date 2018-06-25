/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readers;

import dao.TipoBemDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TipoBem;

/**
 *
 * @author edimar
 */
public class TipoBemReader {

    private TipoBemDAO dao = new TipoBemDAO();

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
            System.out.println("Tipos de bens inseridos: " + nr);
        } catch (IOException ex) {
            Logger.getLogger(TipoBemReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertLine(String line) throws SQLException, ClassNotFoundException {
        line = line.replaceAll("\"", "");
        String[] partes = line.split(";");

        TipoBem objeto = new TipoBem();
        objeto.setCodigo(Integer.parseInt(partes[6]));
        objeto.setDescricao(partes[7]);

        dao.adicionar(objeto);
    }

    public static void main(String[] args) {
        TipoBemReader cr = new TipoBemReader();
        cr.read("C:\\Users\\Administrador\\Desktop\\eleicoes\\bem_candidato_2016");
    }
}
