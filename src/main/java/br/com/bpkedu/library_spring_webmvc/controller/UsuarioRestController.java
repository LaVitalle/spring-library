package br.com.bpkedu.library_spring_webmvc.controller;

import br.com.bpkedu.library_spring_webmvc.domain.Usuario;
import br.com.bpkedu.library_spring_webmvc.dto.UsuarioDTO;
import br.com.bpkedu.library_spring_webmvc.dto.EmprestimoSimplesDTO;
import br.com.bpkedu.library_spring_webmvc.dto.EmprestimoItemSimplesDTO;
import br.com.bpkedu.library_spring_webmvc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

//    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioDTO> getUsuarios(){
        return usuarioService.listarTodos().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = fromDTO(usuarioDTO);
        Usuario salvo = usuarioService.salvar(usuario);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(u -> ResponseEntity.ok(toDTO(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.atualizar(id, fromDTO(usuarioDTO))
                .map(u -> ResponseEntity.ok(toDTO(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        if (usuarioService.deletar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setSenha(usuario.getSenha());
        dto.setEmail(usuario.getEmail());
        dto.setEndereco(usuario.getEndereco());
        dto.setTelefone(usuario.getTelefone());
        if (usuario.getEmprestimos() != null) {
            List<EmprestimoSimplesDTO> emprestimos = usuario.getEmprestimos().stream().map(e -> {
                EmprestimoSimplesDTO edto = new EmprestimoSimplesDTO();
                edto.setId(e.getId());
                edto.setDataEmprestimo(e.getDataEmprestimo());
                edto.setDataDevolucao(e.getDataDevolucao());
                if (e.getItens() != null) {
                    List<EmprestimoItemSimplesDTO> itens = e.getItens().stream().map(item -> {
                        EmprestimoItemSimplesDTO idto = new EmprestimoItemSimplesDTO();
                        idto.setId(item.getId());
                        idto.setLivroId(item.getLivro() != null ? item.getLivro().getId() : null);
                        return idto;
                    }).collect(Collectors.toList());
                    edto.setItens(itens);
                }
                return edto;
            }).collect(Collectors.toList());
            dto.setEmprestimos(emprestimos);
        }
        return dto;
    }

    private Usuario fromDTO(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setSenha(dto.getSenha());
        usuario.setEmail(dto.getEmail());
        usuario.setEndereco(dto.getEndereco());
        usuario.setTelefone(dto.getTelefone());
        return usuario;
    }

}
