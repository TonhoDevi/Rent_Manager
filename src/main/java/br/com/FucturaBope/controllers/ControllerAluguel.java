package br.com.FucturaBope.controllers;

import br.com.FucturaBope.dtos.DtoAluguel;
import br.com.FucturaBope.models.Aluguel;
import br.com.FucturaBope.services.ServiceAluguel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alugueis")
public class ControllerAluguel {

    @Autowired
    private ServiceAluguel serviceAluguel;

    @GetMapping
    public ResponseEntity<List<DtoAluguel>> findAll() {
        List<Aluguel> lista = serviceAluguel.findAll();
        List<DtoAluguel> listaDto = lista.stream().map(DtoAluguel::new).collect(Collectors.toList());
        return ResponseEntity.ok(listaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoAluguel> findById(@PathVariable Integer id) {
        Aluguel aluguel = serviceAluguel.findById(id);
        return ResponseEntity.ok(new DtoAluguel(aluguel));
    }

    @PostMapping
    public ResponseEntity<DtoAluguel> create(@RequestBody DtoAluguel dto) {
        Aluguel novoAluguel = serviceAluguel.save(dto);
        return ResponseEntity.ok(new DtoAluguel(novoAluguel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoAluguel> update(@PathVariable Integer id, @RequestBody DtoAluguel dto) {
        Aluguel aluguelAtualizado = serviceAluguel.update(id, dto);
        return ResponseEntity.ok(new DtoAluguel(aluguelAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        serviceAluguel.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/maior-valor")
    public ResponseEntity<List<DtoAluguel>> findAllOrderByValorDesc() {
        List<Aluguel> lista = serviceAluguel.findAllOrderByValorDesc();
        List<DtoAluguel> listaDto = lista.stream().map(DtoAluguel::new).collect(Collectors.toList());
        return ResponseEntity.ok(listaDto);
    }

    @GetMapping("/atrasados")
    public ResponseEntity<List<DtoAluguel>> findAtrasados() {
        List<Aluguel> lista = serviceAluguel.findAtrasados();
        List<DtoAluguel> listaDto = lista.stream().map(DtoAluguel::new).collect(Collectors.toList());
        return ResponseEntity.ok(listaDto);
    }
    @PutMapping("/{id}/pagar")
    public ResponseEntity<DtoAluguel> pagar(@PathVariable Integer id) {
        Aluguel aluguelPago = serviceAluguel.pagar(id);
        return ResponseEntity.ok(new DtoAluguel(aluguelPago));
    }

    @GetMapping("/pagos")
    public ResponseEntity<List<DtoAluguel>> findAllPagos() {
        List<Aluguel> lista = serviceAluguel.findAllPagos();
        List<DtoAluguel> listaDto = lista.stream().map(DtoAluguel::new).collect(Collectors.toList());
        return ResponseEntity.ok(listaDto);
    }

    @GetMapping("/nao-pagos")
    public ResponseEntity<List<DtoAluguel>> findAllNaoPagos() {
        List<Aluguel> lista = serviceAluguel.findAllNaoPagos();
        List<DtoAluguel> listaDto = lista.stream().map(DtoAluguel::new).collect(Collectors.toList());
        return ResponseEntity.ok(listaDto);
    }

}