package br.com.FucturaBope.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inquilino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "inquilino", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Imovel> imovel = new ArrayList<>();

    @OneToMany(mappedBy = "inquilino", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Aluguel> aluguel = new ArrayList<>();
}
