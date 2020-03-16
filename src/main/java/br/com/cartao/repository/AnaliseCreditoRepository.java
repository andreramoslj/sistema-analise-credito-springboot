package br.com.cartao.repository;

import br.com.cartao.model.AnaliseCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andre on 15/03/20.
 */

@Repository
public interface AnaliseCreditoRepository extends JpaRepository<AnaliseCredito, Long> {



}
