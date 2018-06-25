/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readers;

import config.Conexao;
import dao.CandidatoDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Candidato;

/**
 *
 * @author edimar
 */
public class CandidatoReader {

    private CandidatoDAO dao = new CandidatoDAO();
    
    
    public void read(String dir) {
        File[] files = new File(dir).listFiles();
        for (File file : files) {
            if (file.getName().endsWith(".txt")) {
                System.out.println("Lendo o arquivo: "+file.getName());
                read(file);
            }
        }
    }

    public void read(File file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Latin1"))) {
            String line;
            int nr = 0;
            int nrErros = 0;
            while ((line = reader.readLine()) != null) {
                try {
                    insertLine(line);
                    nr++;
                } catch (SQLException | ClassNotFoundException ex) {
                    nrErros++;
                    Logger.getLogger(CandidatoReader.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            System.out.println("Candidados inseridos: " + nr);
            System.out.println("Número de erros: " + nrErros);
        } catch (IOException ex) {
            Logger.getLogger(CandidatoReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertLine(String line) throws SQLException, ClassNotFoundException {
        line = line.replaceAll("\"", "");
        String[] partes = line.split(";");

        Candidato objeto = new Candidato();
        objeto.setSiglaUF(partes[5]);
        objeto.setNrSequencial(Long.parseLong(partes[11]));
        objeto.setCpf(Long.parseLong(partes[13]));
        objeto.setNome(partes[10]);
        objeto.setNomeUrna(partes[14]);
        objeto.setDataNascimento(partes[26]);
        objeto.setCodCargo(Integer.parseInt(partes[8]));
        objeto.setCodMunicipio(Integer.parseInt(partes[6]));
        objeto.setNrPartido(Integer.parseInt(partes[17]));
        objeto.setCodSexo(Integer.parseInt(partes[29]));
        

        dao.adicionar(objeto);
    }

    public static void main(String[] args) {
        CandidatoReader cr = new CandidatoReader();
        cr.read("/home/edimar/Área de Trabalho/eleicoes_2016/consulta_cand_2016");

    }
}
