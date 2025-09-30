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

    private String nome;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "inquilino") // Corrigido: mappedBy deve apontar para o atributo na entidade Imovel
    private List<Imovel> imovel = new ArrayList<>();
}
