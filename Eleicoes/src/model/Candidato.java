/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edimar
 */
public class Candidato {

    private String siglaUF;
    private Long nrSequencial;
    private Long cpf;
    private String nome;
    private String nomeUrna;
    private Date dataNascimento;
    private Integer codCargo;
    private Integer codMunicipio;
    private Integer nrPartido;
    private Integer codSexo;

    public String getSiglaUF() {
        return siglaUF;
    }

    public void setSiglaUF(String siglaUF) {
        this.siglaUF = siglaUF;
    }

    public Long getNrSequencial() {
        return nrSequencial;
    }

    public void setNrSequencial(Long nrSequencial) {
        this.nrSequencial = nrSequencial;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeUrna() {
        return nomeUrna;
    }

    public void setNomeUrna(String nomeUrna) {
        this.nomeUrna = nomeUrna;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            setDataNascimento(format.parse(dataNascimento));
        } catch (ParseException ex) {
            Logger.getLogger(Candidato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getCodCargo() {
        return codCargo;
    }

    public void setCodCargo(Integer codCargo) {
        this.codCargo = codCargo;
    }

    public Integer getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(Integer codMunicipio) {
        this.codMunicipio = codMunicipio;
    }

    public Integer getNrPartido() {
        return nrPartido;
    }

    public void setNrPartido(Integer nrPartido) {
        this.nrPartido = nrPartido;
    }

    public Integer getCodSexo() {
        return codSexo;
    }

    public void setCodSexo(Integer codSexo) {
        this.codSexo = codSexo;
    }
    
    

    @Override
    public String toString() {
        return "Candidato{" + "nrSequencial=" + nrSequencial + ", cpf=" + cpf + ", nome=" + nome + ", nomeUrna=" + nomeUrna + ", dataNascimento=" + dataNascimento + ", codCargo=" + codCargo + ", codMunicipio=" + codMunicipio + ", nrPartido=" + nrPartido + '}';
    }

}
