package br.com.FucturaBope.controllers;

import br.com.FucturaBope.dtos.DtoAluguel;
import br.com.FucturaBope.models.Aluguel;
import br.com.FucturaBope.services.ServiceAluguel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/alugueis")
@Tag(name = "Aluguel", description = "Endpoints para gerenciar aluguéis")
public class ControllerAluguel {

    @Autowired
    private ServiceAluguel serviceAluguel;

    @GetMapping
    @Operation(summary = "Listar todos os aluguéis", description = "Retorna uma lista de todos os aluguéis cadastrados")
    public ResponseEntity<List<DtoAluguel>> findAll() {
        List<Aluguel> lista = serviceAluguel.findAll();
        List<DtoAluguel> listaDto = lista.stream().map(DtoAluguel::new).collect(Collectors.toList());
        return ResponseEntity.ok(listaDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar aluguel por ID", description = "Retorna os detalhes de um aluguel específico pelo seu ID")
    public ResponseEntity<DtoAluguel> findById(@PathVariable Integer id) {
        Aluguel aluguel = serviceAluguel.findById(id);
        return ResponseEntity.ok(new DtoAluguel(aluguel));
    }

    @PostMapping
    @Operation(summary = "Criar novo aluguel", description = "Cadastra um novo aluguel no sistema")
    public ResponseEntity<DtoAluguel> create(@RequestBody DtoAluguel dto) {
        Aluguel novoAluguel = serviceAluguel.save(dto);
        return ResponseEntity.ok(new DtoAluguel(novoAluguel));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aluguel", description = "Atualiza os dados de um aluguel existente pelo seu ID")
    public ResponseEntity<DtoAluguel> update(@PathVariable Integer id, @RequestBody DtoAluguel dto) {
        Aluguel aluguelAtualizado = serviceAluguel.update(id, dto);
        return ResponseEntity.ok(new DtoAluguel(aluguelAtualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar aluguel", description = "Remove um aluguel do sistema pelo seu ID")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        serviceAluguel.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/maior-valor")
    @Operation(summary = "Listar aluguéis por valor decrescente", description = "Retorna uma lista de aluguéis ordenados pelo valor em ordem decrescente")
    public ResponseEntity<List<DtoAluguel>> findAllOrderByValorDesc() {
        List<Aluguel> lista = serviceAluguel.findAllOrderByValorDesc();
        List<DtoAluguel> listaDto = lista.stream().map(DtoAluguel::new).collect(Collectors.toList());
        return ResponseEntity.ok(listaDto);
    }

    @GetMapping("/atrasados")
    @Operation(summary = "Listar aluguéis atrasados", description = "Retorna uma lista de aluguéis que estão atrasados")
    public ResponseEntity<List<DtoAluguel>> findAtrasados() {
        List<Aluguel> lista = serviceAluguel.findAtrasados();
        List<DtoAluguel> listaDto = lista.stream().map(DtoAluguel::new).collect(Collectors.toList());
        return ResponseEntity.ok(listaDto);
    }
    @PutMapping("/{id}/pagar")
    @Operation(summary = "Pagar aluguel", description = "Marca um aluguel como pago pelo seu ID")
    public ResponseEntity<DtoAluguel> pagar(@PathVariable Integer id) {
        Aluguel aluguelPago = serviceAluguel.pagar(id);
        return ResponseEntity.ok(new DtoAluguel(aluguelPago));
    }

    @GetMapping("/pagos")
    @Operation(summary = "Listar aluguéis pagos", description = "Retorna uma lista de aluguéis que foram pagos")
    public ResponseEntity<List<DtoAluguel>> findAllPagos() {
        List<Aluguel> lista = serviceAluguel.findAllPagos();
        List<DtoAluguel> listaDto = lista.stream().map(DtoAluguel::new).collect(Collectors.toList());
        return ResponseEntity.ok(listaDto);
    }

    @GetMapping("/nao-pagos")
    @Operation(summary = "Listar aluguéis não pagos", description = "Retorna uma lista de aluguéis que não foram pagos")
    public ResponseEntity<List<DtoAluguel>> findAllNaoPagos() {
        List<Aluguel> lista = serviceAluguel.findAllNaoPagos();
        List<DtoAluguel> listaDto = lista.stream().map(DtoAluguel::new).collect(Collectors.toList());
        return ResponseEntity.ok(listaDto);
    }

}