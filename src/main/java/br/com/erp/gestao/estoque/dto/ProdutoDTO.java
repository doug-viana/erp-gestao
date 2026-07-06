package br.com.erp.gestao.estoque.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProdutoDTO {
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "A quantidade é obrigatória")
    private Integer quantidadeEstoque;

    @NotNull(message = "O preço é obrigatório")
    private BigDecimal precoUnitario;
}
