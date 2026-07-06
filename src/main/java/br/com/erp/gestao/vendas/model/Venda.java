package br.com.erp.gestao.vendas.model;

import br.com.erp.gestao.estoque.model.Produto;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "vendas")
@Data
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Muitas vendas para um produto
    private Produto produto;

    private Integer quantidade;
    private BigDecimal valorTotal;
}
