package br.com.FucturaBope.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
public class Inquilino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Imovel imovel;

    public Inquilino() {
    }

    public Inquilino(Long id, String nome, String email, Imovel imovel) {
        this.id = id;
        this.nome = nome;
        this.email = email;

        this.imovel = imovel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Imovel imovel() {
        return imovel;
    }

    public void setCategoria(Imovel imovel) {
        this.imovel = imovel;
    }
}