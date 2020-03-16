package br.com.cartao.model.util;

import br.com.cartao.model.enumeration.StatusAnalise;

import java.math.BigDecimal;

/**
 * Created by andre on 15/03/20.
 */
public class ResultadoAnalise {
    private StatusAnalise statusAnalise;
    private BigDecimal limiteMinimo;
    private BigDecimal limiteMaximo;

    public ResultadoAnalise(StatusAnalise statusAnalise, BigDecimal limiteMinimo, BigDecimal limiteMaximo) {
        this.statusAnalise = statusAnalise;
        this.limiteMinimo = limiteMinimo;
        this.limiteMaximo = limiteMaximo;
    }

    public ResultadoAnalise() {
    }

    public StatusAnalise getStatusAnalise() {
        return statusAnalise;
    }

    public void setStatusAnalise(StatusAnalise statusAnalise) {
        this.statusAnalise = statusAnalise;
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
}
