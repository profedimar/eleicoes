/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author edimar
 */
public class BensCandidato {

    private String siglaUF;
    private Long nrSequencial;
    private Integer  codigoTipoBem;
    private String descricao;
    private Double valor;

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

    public Integer getCodigoTipoBem() {
        return codigoTipoBem;
    }

    public void setCodigoTipoBem(Integer codigoTipoBem) {
        this.codigoTipoBem = codigoTipoBem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    
}
