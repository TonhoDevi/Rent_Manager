package br.com.FucturaBope.dtos;

import br.com.FucturaBope.models.Aluguel;
import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.models.Inquilino;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DtoImovel {

    @Autowired
    private ModelMapper modelMapper;

    private Integer id;
    private String endereco;
    private String descricao;
    private Inquilino inquilino;
    private Aluguel aluguel;

    public DtoImovel() {
    }

    public DtoImovel(Integer id, String descricao, String endereco, Inquilino inquilino, Aluguel aluguel) {
        this.id = id;
        this.descricao = descricao;
        this.endereco = endereco;
        this.inquilino = inquilino;
        this.aluguel = aluguel;
    }
    public DtoImovel(Imovel imovel) {
        this.id = imovel.getId();
        this.endereco = imovel.getEndereco();
        this.descricao = imovel.getDescricao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }
}
