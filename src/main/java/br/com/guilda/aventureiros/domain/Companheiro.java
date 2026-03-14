package br.com.guilda.aventureiros.domain;

/**
 * Representa um Companheiro vinculado a um Aventureiro.
 * <p>
 * O companheiro existe apenas como parte do aventureiro.
 * Não pode existir isoladamente nem ser compartilhado entre aventureiros.
 * </p>
 */
public class Companheiro {

    /**
     * O nome do companheiro.
     */
    private String nome;

    /**
     * A espécie do companheiro (LOBO, CORUJA, GOLEM, DRAGAO_MINIATURA).
     */
    private Especie especie;

    /**
     * Nível de lealdade do companheiro, de 0 a 100.
     */
    private Integer lealdade;

    /**
     * Construtor padrão necessário para frameworks de desserialização (como o Jackson do Spring).
     */
    public Companheiro() {
    }

    /**
     * Construtor completo do companheiro.
     *
     * @param nome o nome do companheiro
     * @param especie a espécie do companheiro
     * @param lealdade nível de lealdade (0 a 100)
     */
    public Companheiro(String nome, Especie especie, Integer lealdade) {
        this.nome = nome;
        this.especie = especie;
        this.lealdade = lealdade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Integer getLealdade() {
        return lealdade;
    }

    public void setLealdade(Integer lealdade) {
        this.lealdade = lealdade;
    }
}
