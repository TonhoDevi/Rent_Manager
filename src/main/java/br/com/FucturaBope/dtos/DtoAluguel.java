package br.com.FucturaBope.dtos;

import br.com.FucturaBope.models.Aluguel;
import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.models.Inquilino;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import java.util.Date;

public class DtoAluguel {

    @Autowired
    private ModelMapper modelMapper;

    private Integer id;
    private Double valor;
    private Date dataVencimento;
    private Imovel imovelId;
    private Inquilino inquilinoId;


    public  DtoAluguel(){};

    public DtoAluguel(Integer id, Double valor, Date dataVencimento, Imovel imovelId, Inquilino inquilinoId) {
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
        this.imovelId = aluguel.getImovel();
        this.inquilinoId = aluguel.getInquilino();
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
