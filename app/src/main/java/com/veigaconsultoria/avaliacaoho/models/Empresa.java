package com.veigaconsultoria.avaliacaoho.models;

public class Empresa {

    private  String nome;
    private  String razaoSocial;
    private  String endereco;
    private  String cidade;
    private  String estado;
    private  Integer qtdEmpregados;
    private  String cnae;




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
