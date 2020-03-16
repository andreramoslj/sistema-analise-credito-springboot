package br.com.cartao.model;


import br.com.cartao.model.enumeration.Sexo;
import br.com.cartao.model.enumeration.StatusAnalise;
import br.com.cartao.model.enumeration.EstadoCivil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by andre on 15/03/20.
 */

@Entity
@Table(name = "analise_credito")
@SequenceGenerator(name = "prop_seq", sequenceName = "common_seq", allocationSize = 1)
public class AnaliseCredito  {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prop_seq")
    private Long id;

    @NotNull
    @Column(name = "data_analise")
    @Getter
    @Setter
    private LocalDateTime dataAnalise;

    @NotBlank
    @Size(max = 14)
    @Getter
    @Setter
    private String cpf;

    @Column(name = "nome", length = 200)
    @Getter
    @Setter
    private String nome;

    @NotNull
    @Getter
    @Setter
    private Long idade;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "bpchar(10)")
    @NotNull
    @Getter
    @Setter
    private Sexo sexo;


    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "bpchar(10)")
    @NotNull
    @Getter
    @Setter
    private EstadoCivil estadoCivil;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "bpchar(10)")
    @NotNull
    @Getter
    @Setter
    private StatusAnalise status;


    @Getter
    @Setter
    private Long dependentes;

    @NotNull
    @Column(name = "renda", precision = 20, scale = 8)
    @Getter
    @Setter
    private BigDecimal renda;



    @Column(name = "limite_min", precision = 12, scale = 2)
    @Getter
    @Setter
    private BigDecimal limiteMinimo;

    @Column(name = "limite_max", precision = 12, scale = 2)
    @Getter
    @Setter
    private BigDecimal limiteMaximo;



    private String firstName;
    private String lastName;
    private String emailId;

    public AnaliseCredito() {

    }

    public AnaliseCredito(String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }


    @Column(name = "first_name", nullable = true)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = true)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email_address", nullable = true)
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "AnaliseCredito{" +
                "id=" + id +
                ", dataAnalise=" + dataAnalise +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", sexo=" + sexo +
                ", estadoCivil=" + estadoCivil +
                ", status=" + status +
                ", dependentes=" + dependentes +
                ", renda=" + renda +
                ", limiteMinimo=" + limiteMinimo +
                ", limiteMaximo=" + limiteMaximo +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
