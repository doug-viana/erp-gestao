package br.com.erp.gestao.vendas.controller;

import br.com.erp.gestao.vendas.dto.VendaDTO;
import br.com.erp.gestao.vendas.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    private final VendaService service;

    // Injeção de dependência via construtor
    public VendaController(VendaService service) {
        this.service = service;
    }

    // A anotação @PostMapping abaixo é a chave para evitar o erro 405
    @PostMapping
    public ResponseEntity<String> registrarVenda(@Valid @RequestBody VendaDTO dto) {
        service.registrarVenda(dto.getProdutoId(), dto.getQuantidade());
        return ResponseEntity.ok("Venda processada com sucesso!");
    }
}