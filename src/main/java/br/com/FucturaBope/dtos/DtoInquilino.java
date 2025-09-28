package br.com.FucturaBope.dtos;

import br.com.FucturaBope.models.Imovel;
import br.com.FucturaBope.models.Inquilino;
import java.util.ArrayList;
import java.util.List;

public class DtoInquilino {
    private Integer id;
    private String nome;
    private String email;
    private List<Imovel> imovel = new ArrayList<>();

    public DtoInquilino() {
    }

    public DtoInquilino(Integer id, String nome, String email, List<Imovel> imovel) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.imovel = imovel;
    }

    public DtoInquilino(Inquilino inquilino) {
        this.id = inquilino.getId();
        this.nome = inquilino.getNome();
        this.email = inquilino.getEmail();
        this.imovel = inquilino.getImovel();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Imovel> Getimovel() {
        return imovel;
    }

    public void setImovel(List<Imovel> imovel) {
        this.imovel = imovel;
    }
}
