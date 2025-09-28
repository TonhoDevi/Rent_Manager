package br.com.FucturaBope.dtos;

import br.com.FucturaBope.models.Aluguel;
import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.models.Inquilino;

public class DtoAluguel {
    private Long id;
    private Double valor;
    private String dataVencimento;
    private Imovel imovelId;
    private Inquilino inquilinoId;


    public  DtoAluguel(){};

    public DtoAluguel(Long id, Double valor, String dataVencimento, Imovel imovelId, Inquilino inquilinoId) {
        this.id = id;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.imovelId = imovelId;
        this.inquilinoId = inquilinoId;
    }
    public DtoAluguel(Aluguel aluguel) {
        this.id = aluguel.getId();
        this.valor = aluguel.getValor();
        this.dataVencimento = aluguel.getDataVencimento();
        this.imovelId = aluguel.getImovelId();
        this.inquilinoId = aluguel.getInquilinoId();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
