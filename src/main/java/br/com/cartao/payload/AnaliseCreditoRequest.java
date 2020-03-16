package br.com.cartao.payload;

import br.com.cartao.model.enumeration.Sexo;
import br.com.cartao.model.enumeration.StatusAnalise;
import br.com.cartao.model.enumeration.EstadoCivil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by andre on 15/03/20.
 */

public class AnaliseCreditoRequest {

    private Long id;
    private LocalDateTime dataAnalise;
    private String cpf;
    private String nome;
    private Long idade;
    private Long dependentes;
    private Sexo sexo;
    private EstadoCivil estadoCivil;
    private BigDecimal renda;
    private StatusAnalise status;
    private BigDecimal limiteMinimo;
    private BigDecimal limiteMaximo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(LocalDateTime dataAnalise) {
        this.dataAnalise = dataAnalise;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }


    public Long getDependentes() {
        return dependentes;
    }

    public void setDependentes(Long dependentes) {
        this.dependentes = dependentes;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }

    public StatusAnalise getStatus() {
        return status;
    }

    public void setStatus(StatusAnalise status) {
        this.status = status;
    }

    public BigDecimal getLimiteMinimo() {
        return limiteMinimo;
    }

    public void setLimiteMinimo(BigDecimal limiteMinimo) {
        this.limiteMinimo = limiteMinimo;
    }

    public BigDecimal getLimiteMaximo() {
        return limiteMaximo;
    }

    public void setLimiteMaximo(BigDecimal limiteMaximo) {
        this.limiteMaximo = limiteMaximo;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
}
