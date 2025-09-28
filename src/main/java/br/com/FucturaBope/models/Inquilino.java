package br.com.FucturaBope.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Inquilino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "imovel")
    private List<Imovel> imovel = new ArrayList<>();

    public Inquilino() {
    }

    public Inquilino(Integer id, String nome, String email, List<Imovel> imovel) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.imovel = imovel;
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

    public List<Imovel> getImovel() {
        return imovel;
    }

    public void setImovel(List<Imovel> imovel) {
        this.imovel = imovel;
    }
}