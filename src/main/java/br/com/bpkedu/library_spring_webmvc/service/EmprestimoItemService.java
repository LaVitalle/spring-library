package br.com.bpkedu.library_spring_webmvc.service;

import br.com.bpkedu.library_spring_webmvc.domain.EmprestimoItem;
import br.com.bpkedu.library_spring_webmvc.repository.EmprestimoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoItemService {
    @Autowired
    private EmprestimoItemRepository emprestimoItemRepository;

    public List<EmprestimoItem> findAll() {
        return emprestimoItemRepository.findAll();
    }

    public Optional<EmprestimoItem> findById(Long id) {
        return emprestimoItemRepository.findById(id);
    }

    public EmprestimoItem save(EmprestimoItem item) {
        return emprestimoItemRepository.save(item);
    }

    public void deleteById(Long id) {
        emprestimoItemRepository.deleteById(id);
    }
}
