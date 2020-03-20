package br.com.cartao.controller;

import br.com.cartao.model.AnaliseCredito;
import br.com.cartao.model.enumeration.StatusAnalise;
import br.com.cartao.model.util.ResultadoAnalise;
import br.com.cartao.payload.AnaliseCreditoRequest;
import br.com.cartao.payload.ApiResponse;
import br.com.cartao.service.AnaliseCreditoService;
import br.com.cartao.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by andre on 15/03/20.
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
@Api(value="API REST Análise de Crédito")
public class AnaliseCreditoController {

    @Autowired
    private AnaliseCreditoService analiseCreditoService;

    private static final Logger logger = LoggerFactory.getLogger(AnaliseCreditoController.class);

    @ApiOperation(value="Retorna uma lista de Análise de Créditos, ordenado por data decrescente")
    @GetMapping("/analise-credito")
    public List<AnaliseCredito> getAnalises() {
        return  analiseCreditoService.getAnalises();
    }


    @ApiOperation(value="Retorna uma lista de Análise de Créditos com base no CPF, ordenado por data decrescente")
    @GetMapping("/analise-credito/consultar")
    public List<AnaliseCredito> getAnalisesCpf(@RequestParam(value = "cpf") String cpf) {
        if (StringUtils.isEmpty(cpf)) {
            return  analiseCreditoService.getAnalises();
        }
        return  analiseCreditoService.getAnalises(cpf);
    }

    @ApiOperation(value="Retorna uma Análise de Crédito referente ao id informado")
    @GetMapping("/analise-credito/{id}")
    public ResponseEntity<AnaliseCredito> getAnalisesById(@PathVariable(value = "id") Long analiseId)
            throws ResourceNotFoundException {
        AnaliseCredito analiseCredito = analiseCreditoService.findById(analiseId);
        return ResponseEntity.ok().body(analiseCredito);
    }

    @ApiOperation(value="Salva uma Análise de Crédito")
    @PostMapping("/analise-credito")
    public AnaliseCredito salvar(@Valid @RequestBody AnaliseCreditoRequest request) {
        AnaliseCredito analiseCredito = new AnaliseCredito();
        analiseCredito.setDataAnalise(LocalDateTime.now());
        analiseCredito.setCpf(request.getCpf());
        analiseCredito.setNome(request.getNome());
        analiseCredito.setIdade(request.getIdade());
        analiseCredito.setSexo(request.getSexo());
        analiseCredito.setEstadoCivil(request.getEstadoCivil());
        analiseCredito.setDependentes(request.getDependentes());
        analiseCredito.setRenda(request.getRenda());
        analiseCredito.setLimiteMaximo(request.getLimiteMaximo());


        ResultadoAnalise resultadoAnalise = analiseCreditoService.calcularProposta(analiseCredito);
        analiseCredito.setStatus(resultadoAnalise.getStatusAnalise());
        analiseCredito.setLimiteMinimo(resultadoAnalise.getLimiteMinimo());
        analiseCredito.setLimiteMaximo(resultadoAnalise.getLimiteMaximo());

        AnaliseCredito result = analiseCreditoService.salvar(analiseCredito);
        return  result;
    }

    @ApiOperation(value="Deleta uma Análise de Crédito refere ao id")
    @DeleteMapping("/analise-credito/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        AnaliseCredito analiseCredito = analiseCreditoService.findById(id);
        analiseCreditoService.deletar(analiseCredito);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ApiResponse(true, "Analise deletada com sucesso"));
    }

    @ApiOperation(value="Retorna uma Análise de Crédito referente ao id informado")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(analiseCreditoService.findById(id));
    }




}
