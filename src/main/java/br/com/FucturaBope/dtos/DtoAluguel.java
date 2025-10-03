package br.com.FucturaBope.dtos;

import br.com.FucturaBope.models.Aluguel;
import java.util.Date;

public class DtoAluguel {

    private Integer id;
    private Double valor;
    private Date dataVencimento;
    private Integer imovelId;
    private Integer inquilinoId;
    private Boolean pago = false;
    private long diasAtraso;


    public  DtoAluguel(){};

    public DtoAluguel(Integer id, Double valor, Date dataVencimento, Integer imovelId, Integer inquilinoId, Boolean pago) {
        this.id = id;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.imovelId = imovelId;
        this.inquilinoId = inquilinoId;
        this.pago = pago;
    }
    public DtoAluguel(Aluguel aluguel) {
        this.id = aluguel.getId();
        this.valor = aluguel.getValor();
        this.dataVencimento = aluguel.getDataVencimento();
        this.imovelId = aluguel.getImovel() != null ? aluguel.getImovel().getId() : null;
        this.inquilinoId = aluguel.getInquilino() != null ? aluguel.getInquilino().getId() : null;
        this.pago = aluguel.getPago();
        this.diasAtraso = aluguel.getDiasAtraso();
    }

    public DtoAluguel(Aluguel aluguel, long diasAtraso) {
        this.id = aluguel.getId();
        this.valor = aluguel.getValor();
        this.dataVencimento = aluguel.getDataVencimento();
        this.pago = aluguel.getPago();
        this.diasAtraso = diasAtraso;
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

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Integer getImovelId() {
        return imovelId;
    }

    public void setImovelId(Integer imovelId) {
        this.imovelId = imovelId;
    }

    public Integer getInquilinoId() {
        return inquilinoId;
    }

    public void setInquilinoId(Integer inquilinoId) {
        this.inquilinoId = inquilinoId;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public long getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(long diasAtraso) {
        this.diasAtraso = diasAtraso;
    }
}
