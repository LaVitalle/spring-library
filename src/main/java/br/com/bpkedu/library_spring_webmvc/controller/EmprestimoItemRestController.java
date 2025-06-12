package br.com.bpkedu.library_spring_webmvc.controller;

import br.com.bpkedu.library_spring_webmvc.domain.EmprestimoItem;
import br.com.bpkedu.library_spring_webmvc.dto.EmprestimoItemDTO;
import br.com.bpkedu.library_spring_webmvc.domain.Livro;
import br.com.bpkedu.library_spring_webmvc.domain.Emprestimo;
import br.com.bpkedu.library_spring_webmvc.repository.LivroRepository;
import br.com.bpkedu.library_spring_webmvc.repository.EmprestimoRepository;
import br.com.bpkedu.library_spring_webmvc.service.EmprestimoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/emprestimo-itens")
public class EmprestimoItemRestController {
    @Autowired
    private EmprestimoItemService emprestimoItemService;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @GetMapping
    public List<EmprestimoItemDTO> listar() {
        return emprestimoItemService.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoItemDTO> buscar(@PathVariable Long id) {
        return emprestimoItemService.findById(id)
                .map(e -> ResponseEntity.ok(toDTO(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmprestimoItemDTO> criar(@RequestBody EmprestimoItemDTO dto) {
        Optional<Livro> livroOpt = livroRepository.findById(dto.getLivroId());
        if (livroOpt.isEmpty())
            return ResponseEntity.badRequest().build();
        EmprestimoItem item = new EmprestimoItem();
        item.setLivro(livroOpt.get());
        if (dto.getId() != null) {
            Optional<Emprestimo> empOpt = emprestimoRepository.findById(dto.getId());
            empOpt.ifPresent(item::setEmprestimo);
        }
        EmprestimoItem salvo = emprestimoItemService.save(item);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (emprestimoItemService.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        emprestimoItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private EmprestimoItemDTO toDTO(EmprestimoItem item) {
        EmprestimoItemDTO dto = new EmprestimoItemDTO();
        dto.setId(item.getId());
        dto.setLivroId(item.getLivro() != null ? item.getLivro().getId() : null);
        return dto;
    }
}
