package br.com.bpkedu.library_spring_webmvc.controller;

import br.com.bpkedu.library_spring_webmvc.domain.Emprestimo;
import br.com.bpkedu.library_spring_webmvc.domain.EmprestimoItem;
import br.com.bpkedu.library_spring_webmvc.domain.Usuario;
import br.com.bpkedu.library_spring_webmvc.domain.Livro;
import br.com.bpkedu.library_spring_webmvc.dto.EmprestimoDTO;
import br.com.bpkedu.library_spring_webmvc.dto.EmprestimoItemDTO;
import br.com.bpkedu.library_spring_webmvc.repository.UsuarioRepository;
import br.com.bpkedu.library_spring_webmvc.repository.LivroRepository;
import br.com.bpkedu.library_spring_webmvc.service.EmprestimoService;
import br.com.bpkedu.library_spring_webmvc.service.EmprestimoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoRestController {
    @Autowired
    private EmprestimoService emprestimoService;
    @Autowired
    private EmprestimoItemService emprestimoItemService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public List<EmprestimoDTO> listar() {
        return emprestimoService.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> buscar(@PathVariable Long id) {
        return emprestimoService.findById(id)
                .map(e -> ResponseEntity.ok(toDTO(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmprestimoDTO> criar(@RequestBody EmprestimoDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(dto.getUsuarioId());
        if (usuarioOpt.isEmpty())
            return ResponseEntity.badRequest().build();
        Emprestimo emp = new Emprestimo();
        emp.setDataEmprestimo(dto.getDataEmprestimo());
        emp.setDataDevolucao(dto.getDataDevolucao());
        emp.setUsuario(usuarioOpt.get());
        Emprestimo salvo = emprestimoService.save(emp);
        if (dto.getItens() != null) {
            for (EmprestimoItemDTO itemDTO : dto.getItens()) {
                Optional<Livro> livroOpt = livroRepository.findById(itemDTO.getLivroId());
                if (livroOpt.isPresent()) {
                    EmprestimoItem item = new EmprestimoItem();
                    item.setEmprestimo(salvo);
                    item.setLivro(livroOpt.get());
                    emprestimoItemService.save(item);
                }
            }
        }
        // Buscar o empréstimo novamente para garantir que os itens estejam atualizados
        Optional<Emprestimo> emprestimoComItens = emprestimoService.findById(salvo.getId());
        return emprestimoComItens
                .map(e -> ResponseEntity.ok(toDTO(e)))
                .orElse(ResponseEntity.ok(toDTO(salvo)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (emprestimoService.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        emprestimoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private EmprestimoDTO toDTO(Emprestimo e) {
        EmprestimoDTO dto = new EmprestimoDTO();
        dto.setId(e.getId());
        dto.setDataEmprestimo(e.getDataEmprestimo());
        dto.setDataDevolucao(e.getDataDevolucao());
        dto.setUsuarioId(e.getUsuario() != null ? e.getUsuario().getId() : null);
        if (e.getItens() != null) {
            List<EmprestimoItemDTO> itens = e.getItens().stream().map(this::toItemDTO).collect(Collectors.toList());
            dto.setItens(itens);
        }
        return dto;
    }

    private EmprestimoItemDTO toItemDTO(EmprestimoItem item) {
        EmprestimoItemDTO dto = new EmprestimoItemDTO();
        dto.setId(item.getId());
        dto.setLivroId(item.getLivro() != null ? item.getLivro().getId() : null);
        return dto;
    }
}
