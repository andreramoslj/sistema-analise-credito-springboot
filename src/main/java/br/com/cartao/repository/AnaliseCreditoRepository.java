package br.com.cartao.repository;

import br.com.cartao.model.AnaliseCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by andre on 15/03/20.
 */

@Repository
public interface AnaliseCreditoRepository extends JpaRepository<AnaliseCredito, Long> {


    List<AnaliseCredito> findByCpf(String cpf);



}
