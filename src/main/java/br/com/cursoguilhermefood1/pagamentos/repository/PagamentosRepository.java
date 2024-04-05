package br.com.cursoguilhermefood1.pagamentos.repository;

import br.com.cursoguilhermefood1.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentosRepository extends JpaRepository<Pagamento, Long> {
}
