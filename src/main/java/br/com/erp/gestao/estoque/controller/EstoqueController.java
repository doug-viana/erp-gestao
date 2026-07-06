package br.com.erp.gestao.estoque.controller;

import br.com.erp.gestao.estoque.dto.ProdutoDTO;
import br.com.erp.gestao.estoque.model.Produto;
import br.com.erp.gestao.estoque.service.EstoqueService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@Valid @RequestBody ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produto.setPrecoUnitario(dto.getPrecoUnitario());

        service.cadastrarProduto(produto);
        return ResponseEntity.ok("Produto '" + produto.getNome() + "' cadastrado!");
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(service.listarTodos(pageable).map(this::converterParaDTO));
    }

    @PostMapping("/{id}/entrada")
    public ResponseEntity<String> darEntrada(@PathVariable Long id, @RequestParam Integer quantidade) {
        service.darEntrada(id, quantidade);
        return ResponseEntity.ok("Entrada realizada!");
    }

    @PostMapping("/{id}/baixa")
    public ResponseEntity<String> darBaixa(@PathVariable Long id, @RequestParam Integer quantidade) {
        service.darBaixa(id, quantidade);
        return ResponseEntity.ok("Baixa realizada!");
    }

    private ProdutoDTO converterParaDTO(Produto p) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setQuantidadeEstoque(p.getQuantidadeEstoque());
        dto.setPrecoUnitario(p.getPrecoUnitario());
        return dto;
    }
    @PostMapping("/lote")
    public ResponseEntity<String> cadastrarEmLote(@Valid @RequestBody List<ProdutoDTO> listaDto) {
        int totalCadastrado = service.cadastrarEmLote(listaDto);
        return ResponseEntity.ok(totalCadastrado + " produtos cadastrados com sucesso!");
    }
}