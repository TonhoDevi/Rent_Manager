package br.com.FucturaBope.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "alugueis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double valor;

    private Date dataVencimento;

    @Column(name = "imovel_id", nullable = false)
    private Integer imovelId;

    @Column(name = "inquilino_id")
    private Integer inquilinoId;

    private Boolean pago = false;
}
