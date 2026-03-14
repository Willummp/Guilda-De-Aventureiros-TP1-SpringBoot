package br.com.guilda.aventureiros.dto;

import br.com.guilda.aventureiros.domain.Classe;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Representa os dados necessários para registrar um novo aventureiro.
 */
public class AventureiroRequest {

    /**
     * O nome do aventureiro. Obrigatório e não pode ser vazio.
     */
    @NotBlank(message = "nome do aventureiro é obrigatório e não pode ser vazio")
    private String nome;

    /**
     * Classe escolhida. Deve ser uma das permitidas pela guilda (GUERREIRO, MAGO, etc).
     */
    @NotNull(message = "classe inválida ou obrigatória")
    private Classe classe;

    /**
     * O nível do aventureiro, que deve ser positivo (>= 1).
     */
    @NotNull(message = "nível é obrigatório")
    @Min(value = 1, message = "nivel deve ser maior ou igual a 1")
    private Integer nivel;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
}
