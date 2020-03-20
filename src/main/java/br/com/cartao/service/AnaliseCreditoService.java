package br.com.cartao.service;

import br.com.cartao.model.AnaliseCredito;
import br.com.cartao.exception.ResourceNotFoundException;
import br.com.cartao.model.enumeration.EstadoCivil;
import br.com.cartao.model.enumeration.StatusAnalise;
import br.com.cartao.model.util.ResultadoAnalise;
import br.com.cartao.repository.AnaliseCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Created by andre on 15/03/20.
 */

@Service
public class AnaliseCreditoService {

    @Autowired
    private AnaliseCreditoRepository analiseCreditoRepository;


    public AnaliseCredito findById(Long id) {
        return analiseCreditoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Analise", "id", id));
    }

    public Page<AnaliseCredito> getPagedResults(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "dataAnalise");

        return analiseCreditoRepository.findAll( pageable);
    }


    public List<AnaliseCredito> getAnalises() {
        return analiseCreditoRepository.findAll(Sort.by(Sort.Direction.DESC,"dataAnalise"));
    }
    public List<AnaliseCredito> getAnalises(String cpf) {
        List<AnaliseCredito> LISTTESTE = analiseCreditoRepository.findByCpf(cpf);
        return LISTTESTE;
    }


    public boolean existsById(Long id) {
        return analiseCreditoRepository.existsById(id);
    }

    public AnaliseCredito salvar(AnaliseCredito analiseCredito) {
        return analiseCreditoRepository.save(analiseCredito);
    }

    public void deletar(AnaliseCredito analiseCredito) {
        analiseCreditoRepository.delete(analiseCredito);
    }


    public ResultadoAnalise calcularProposta(AnaliseCredito analiseCredito) {
        Double pontuacao = getPontuacao(analiseCredito);

        ResultadoAnalise resultadoAnalise = new ResultadoAnalise();

        if (pontuacao <= 750D) {
            resultadoAnalise.setStatusAnalise(StatusAnalise.REPROVADO);
        } else if (pontuacao > 750D && pontuacao <= 1000D) {
            resultadoAnalise.setStatusAnalise(StatusAnalise.APROVADO);
            resultadoAnalise.setLimiteMinimo(BigDecimal.ZERO);
            resultadoAnalise.setLimiteMaximo(BigDecimal.valueOf(600));
        } if (pontuacao > 1000D && pontuacao <= 2100D) {
            resultadoAnalise.setStatusAnalise(StatusAnalise.APROVADO);
            resultadoAnalise.setLimiteMinimo(BigDecimal.ZERO);
            resultadoAnalise.setLimiteMaximo(BigDecimal.valueOf(1500));
        } if (pontuacao > 2100D && pontuacao <= 4000D) {
            resultadoAnalise.setStatusAnalise(StatusAnalise.APROVADO);
            resultadoAnalise.setLimiteMinimo(BigDecimal.valueOf(1200));
            resultadoAnalise.setLimiteMaximo(BigDecimal.valueOf(3000));
        } if (pontuacao > 4000 && pontuacao <= 7000) {
            resultadoAnalise.setStatusAnalise(StatusAnalise.APROVADO);
            resultadoAnalise.setLimiteMinimo(BigDecimal.valueOf(3000));
            resultadoAnalise.setLimiteMaximo(BigDecimal.valueOf(10000));
        } if (pontuacao > 7000 && pontuacao <= 10000) {
            resultadoAnalise.setStatusAnalise(StatusAnalise.APROVADO);
            resultadoAnalise.setLimiteMinimo(BigDecimal.valueOf(10000));
            resultadoAnalise.setLimiteMaximo(BigDecimal.valueOf(20000));
        } if (pontuacao > 10000) {
            resultadoAnalise.setStatusAnalise(StatusAnalise.APROVADO);
            resultadoAnalise.setLimiteMinimo(BigDecimal.valueOf(20000));
            resultadoAnalise.setLimiteMaximo(BigDecimal.valueOf(35000));
        }

        return resultadoAnalise;

    }

    private Double getPontuacao(AnaliseCredito analiseCredito) {
        Double pontuacao = 0D;

        if (analiseCredito.getIdade() < 18) {
            return 0D;
        } else if (analiseCredito.getIdade() > 18 && analiseCredito.getIdade() < 25 ) {
            pontuacao += 300D;
        } else if (analiseCredito.getIdade() >= 25 && analiseCredito.getIdade() < 50 ) {
            pontuacao += 500D;
        }else if (analiseCredito.getIdade() >= 50 ) {
            pontuacao += 2000D;
        }

        if (EstadoCivil.SOLTEIRO.equals(analiseCredito.getEstadoCivil())) {
            pontuacao += 300D;
        } else if (EstadoCivil.CASADO.equals(analiseCredito.getEstadoCivil())) {
            pontuacao += 600D;
        }


if (analiseCredito.getDependentes() != null ) {
    if (analiseCredito.getDependentes() == 0) {
        pontuacao += 500D;
    } else if (analiseCredito.getDependentes() <= 2) {
        pontuacao += 300;
    }
}

        pontuacao +=  analiseCredito.getRenda().doubleValue() / 3D;

        /*
        if (analiseCredito.getRenda() <= 500) {
            pontuacao += 100;
        } if (analiseCredito.getRenda() > 500 && analiseCredito.getRenda() <= 1000) {
            pontuacao += 800;
        } else if (analiseCredito.getRenda() > 1000 && analiseCredito.getRenda() <= 2000) {
            pontuacao += 1000;
        } else if (analiseCredito.getRenda() > 2000 && analiseCredito.getRenda() <= 5000) {
            pontuacao += 2000;
        } else if (analiseCredito.getRenda() > 5000 && analiseCredito.getRenda() <= 10000) {
            pontuacao += 4000;
        } else if (analiseCredito.getRenda() > 10000 && analiseCredito.getRenda() <= 30000) {
            pontuacao += 8000;
        }  else if (analiseCredito.getRenda() > 30000 ) {
            pontuacao += 15000;
        }
    */

        return  pontuacao;
    }


}
