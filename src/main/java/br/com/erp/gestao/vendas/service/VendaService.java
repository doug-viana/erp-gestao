package br.com.erp.gestao.vendas.service;

import br.com.erp.gestao.estoque.model.Produto;
import br.com.erp.gestao.estoque.repository.ProdutoRepository;
import br.com.erp.gestao.estoque.service.EstoqueService;
import br.com.erp.gestao.vendas.model.Venda;
import br.com.erp.gestao.vendas.repository.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final EstoqueService estoqueService;
    private final ProdutoRepository produtoRepository;

    public VendaService(VendaRepository vendaRepository, EstoqueService estoqueService, ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.estoqueService = estoqueService;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public void registrarVenda(Long produtoId, Integer quantidade) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        // Lógica de negócio: baixa no estoque e depois registra a venda
        estoqueService.darBaixa(produto.getId(), quantidade);

        Venda venda = new Venda();
        venda.setProduto(produto);
        venda.setQuantidade(quantidade);
        venda.setValorTotal(produto.getPrecoUnitario().multiply(new BigDecimal(quantidade)));

        vendaRepository.save(venda);
    }
}