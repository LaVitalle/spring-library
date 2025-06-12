package br.com.bpkedu.library_spring_webmvc.repository;

import br.com.bpkedu.library_spring_webmvc.domain.EmprestimoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoItemRepository extends JpaRepository<EmprestimoItem, Long> {
}
