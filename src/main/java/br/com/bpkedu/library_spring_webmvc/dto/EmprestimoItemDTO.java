package br.com.bpkedu.library_spring_webmvc.dto;

public class EmprestimoItemDTO {
    private Long id;
    private Long livroId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }
}
