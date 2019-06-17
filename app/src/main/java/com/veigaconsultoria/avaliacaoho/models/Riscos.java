package com.veigaconsultoria.avaliacaoho.models;

import java.io.Serializable;

public class Riscos implements Serializable {

    private String RiscoEsocialRiscos;
    private String descricaoRiscos;
    private String meioDePropagacaoRiscos;
    private String grupoRiscos;
    private String tipoRisco;
    private String habtualEventualRiscos;
    private String continuoIntermitenteRiscos;
    private Boolean quantitativoRiscos;
    private Float intensidadeRiscos;
    private Float limiteToleranciaRiscos;
    private String epiRiscos;
    private String epcRiscos;
    private String comentarioRiscos;
    private String photoRiscos;
    private String idRisco;

    public String getTipoRisco() {
        return tipoRisco;
    }

    public void setTipoRisco(String tipoRisco) {
        this.tipoRisco = tipoRisco;
    }

    public String getRiscoEsocialRiscos() {
        return RiscoEsocialRiscos;
    }

    public void setRiscoEsocialRiscos(String riscoEsocialRiscos) {
        RiscoEsocialRiscos = riscoEsocialRiscos;
    }

    public String getDescricaoRiscos() {
        return descricaoRiscos;
    }

    public void setDescricaoRiscos(String descricaoRiscos) {
        this.descricaoRiscos = descricaoRiscos;
    }

    public String getMeioDePropagacaoRiscos() {
        return meioDePropagacaoRiscos;
    }

    public void setMeioDePropagacaoRiscos(String meioDePropagacaoRiscos) {
        this.meioDePropagacaoRiscos = meioDePropagacaoRiscos;
    }

    public String getGrupoRiscos() {
        return grupoRiscos;
    }

    public void setGrupoRiscos(String grupoRiscos) {
        this.grupoRiscos = grupoRiscos;
    }

    public String getHabtualEventualRiscos() {
        return habtualEventualRiscos;
    }

    public void setHabtualEventualRiscos(String habtualEventualRiscos) {
        this.habtualEventualRiscos = habtualEventualRiscos;
    }

    public String getContinuoIntermitenteRiscos() {
        return continuoIntermitenteRiscos;
    }

    public void setContinuoIntermitenteRiscos(String continuoIntermitenteRiscos) {
        this.continuoIntermitenteRiscos = continuoIntermitenteRiscos;
    }

    public Boolean getQuantitativoRiscos() {
        return quantitativoRiscos;
    }

    public void setQuantitativoRiscos(Boolean quantitativoRiscos) {
        this.quantitativoRiscos = quantitativoRiscos;
    }

    public Float getIntensidadeRiscos() {
        return intensidadeRiscos;
    }

    public void setIntensidadeRiscos(Float intensidadeRiscos) {
        this.intensidadeRiscos = intensidadeRiscos;
    }

    public Float getLimiteToleranciaRiscos() {
        return limiteToleranciaRiscos;
    }

    public void setLimiteToleranciaRiscos(Float limiteToleranciaRiscos) {
        this.limiteToleranciaRiscos = limiteToleranciaRiscos;
    }

    public String getEpiRiscos() {
        return epiRiscos;
    }

    public void setEpiRiscos(String epiRiscos) {
        this.epiRiscos = epiRiscos;
    }

    public String getEpcRiscos() {
        return epcRiscos;
    }

    public void setEpcRiscos(String epcRiscos) {
        this.epcRiscos = epcRiscos;
    }

    public String getComentarioRiscos() {
        return comentarioRiscos;
    }

    public void setComentarioRiscos(String comentarioRiscos) {
        this.comentarioRiscos = comentarioRiscos;
    }

    public String getPhotoRiscos() {
        return photoRiscos;
    }

    public void setPhotoRiscos(String photoRiscos) {
        this.photoRiscos = photoRiscos;
    }


    public String getIdRisco() {
        return idRisco;
    }

    public void setIdRisco(String idRisco) {
        this.idRisco = idRisco;
    }
}

