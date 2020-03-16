package br.com.cartao.controller;

import br.com.cartao.model.AnaliseCredito;
import br.com.cartao.model.enumeration.StatusAnalise;
import br.com.cartao.model.util.ResultadoAnalise;
import br.com.cartao.payload.AnaliseCreditoRequest;
import br.com.cartao.payload.ApiResponse;
import br.com.cartao.service.AnaliseCreditoService;
import br.com.cartao.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class AnaliseCreditoController {

    @Autowired
    private AnaliseCreditoService analiseCreditoService;

    private static final Logger logger = LoggerFactory.getLogger(AnaliseCreditoController.class);

    @GetMapping("/analise-credito")
    public List<AnaliseCredito> getAnalises() {
        return  analiseCreditoService.getAnalises();
    }

    @GetMapping("/analise-credito/{id}")
    public ResponseEntity<AnaliseCredito> getAnalisesById(@PathVariable(value = "id") Long analiseId)
            throws ResourceNotFoundException {
        AnaliseCredito analiseCredito = analiseCreditoService.findById(analiseId);
        return ResponseEntity.ok().body(analiseCredito);
    }

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

    @DeleteMapping("/analise-credito/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        AnaliseCredito analiseCredito = analiseCreditoService.findById(id);
        analiseCreditoService.deletar(analiseCredito);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ApiResponse(true, "Analise deletada com sucesso"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(analiseCreditoService.findById(id));
    }





}
