package com.loja.loja.shared;

import com.loja.loja.model.TamanhoHamburguer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record LojaCompletoDto(String id,
    @NotEmpty(message = "Campo sabor da Carne deve ser informado!")
    @NotBlank(message = "Tá com caracteres em branco mas sem o sabor da Carne.")
    String saborCarne,
    List<String> ingredientes,
    @NotNull(message = "Valores válidos: Turista, Aventureiro, Heroi ")
    TamanhoHamburguer tamanho,
                              @Positive(message = "Informe um valor positivo para a pizza")
                              Double valor) {
}
