package br.com.guilda.aventureiros.dto;

import br.com.guilda.aventureiros.domain.Aventureiro;
import br.com.guilda.aventureiros.domain.Classe;
import br.com.guilda.aventureiros.domain.Companheiro;

/**
 * Representa os dados completos de um Aventureiro quando consultado por ID.
 */
public class AventureiroResponse {

    private Long id;
    private String nome;
    private Classe classe;
    private Integer nivel;
    private boolean ativo;
    private Companheiro companheiro;

    public AventureiroResponse(Aventureiro aventureiro) {
        this.id = aventureiro.getId();
        this.nome = aventureiro.getNome();
        this.classe = aventureiro.getClasse();
        this.nivel = aventureiro.getNivel();
        this.ativo = aventureiro.isAtivo();
        this.companheiro = aventureiro.getCompanheiro();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Classe getClasse() {
        return classe;
    }

    public Integer getNivel() {
        return nivel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Companheiro getCompanheiro() {
        return companheiro;
    }
}
