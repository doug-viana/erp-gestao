package br.com.erp.gestao.estoque.service;

import br.com.erp.gestao.estoque.dto.ProdutoDTO;
import br.com.erp.gestao.estoque.model.Produto;
import br.com.erp.gestao.estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
public class EstoqueService {

    public Page<Produto> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    private final ProdutoRepository repository;

    public EstoqueService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void cadastrarProduto(Produto produto) {
        repository.save(produto);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public void darEntrada(Long id, Integer quantidade) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
        repository.save(produto);
    }

    @Transactional
    public void darBaixa(Long id, Integer quantidade) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        if (produto.getQuantidadeEstoque() < quantidade) {
            throw new RuntimeException("Estoque insuficiente!");
        }
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        repository.save(produto);
    }
    public ProdutoDTO converterParaDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        dto.setPrecoUnitario(produto.getPrecoUnitario());
        return dto;
    }
}