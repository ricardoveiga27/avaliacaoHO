package com.veigaconsultoria.avaliacaoho.models;

import java.io.Serializable;
import java.util.List;

public class Empresa implements Serializable {

    private  String nome;
    private  String razaoSocial;
    private  String endereco;
    private String cnpj;
    private  String cidade;
    private  String estado;
    private  Integer qtdEmpregados;
    private  String cnae;

    private String idEmpresa;




    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getQtdEmpregados() {
        return qtdEmpregados;
    }

    public void setQtdEmpregados(Integer qtdEmpregados) {
        this.qtdEmpregados = qtdEmpregados;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }
}
