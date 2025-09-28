package br.com.FucturaBope.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import javax.xml.crypto.Data;
import java.sql.Date;

@Entity
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double valor;
    private String dataVencimento;
    private Imovel imovelId;
    private Inquilino inquilinoId;


    public  Aluguel(){};

    public Aluguel(Integer id, Double valor, String dataVencimento, Imovel imovelId, Inquilino inquilinoId) {
        this.id = id;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.imovelId = imovelId;
        this.inquilinoId = inquilinoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Imovel getImovelId() {
        return imovelId;
    }

    public void setImovelId(Imovel imovelId) {
        this.imovelId = imovelId;
    }

    public Inquilino getInquilinoId() {
        return inquilinoId;
    }

    public void setInquilinoId(Inquilino inquilinoId) {
        this.inquilinoId = inquilinoId;
    }
}
