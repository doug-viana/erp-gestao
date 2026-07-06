package br.com.erp.gestao.estoque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor // <<-- ISSO resolve o erro do "new Produto()"
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    @Column(nullable = false)
    private Integer quantidadeEstoque;

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    @Column(nullable = false)
    private BigDecimal precoUnitario;
}