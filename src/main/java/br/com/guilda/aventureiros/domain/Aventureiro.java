package br.com.guilda.aventureiros.domain;

/**
 * Representa um Aventureiro registrado na Guilda.
 */
public class Aventureiro {

    private Long id;
    private String nome;
    private Classe classe;
    private Integer nivel;
    private boolean ativo;
    private Companheiro companheiro;

    /**
     * Construtor padrão da JRE para desserializações.
     */
    public Aventureiro() {
    }

    /**
     * Construtor principal para o momento do Registro de um aventureiro.
     * Ao ser criado, por regra de negócios, o aventureiro deve estar ativo.
     *
     * @param nome   o nome do aventureiro
     * @param classe a classe de combate do aventureiro
     * @param nivel  o nível inicial do aventureiro (mínimo 1)
     */
    public Aventureiro(String nome, Classe classe, Integer nivel) {
        this.nome = nome;
        this.classe = classe;
        this.nivel = nivel;
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Companheiro getCompanheiro() {
        return companheiro;
    }

    public void setCompanheiro(Companheiro companheiro) {
        this.companheiro = companheiro;
    }
}
