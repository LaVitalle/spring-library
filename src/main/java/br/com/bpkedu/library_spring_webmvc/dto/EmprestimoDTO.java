package br.com.bpkedu.library_spring_webmvc.dto;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoDTO {
    private Long id;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Long usuarioId;
    private List<EmprestimoItemDTO> itens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<EmprestimoItemDTO> getItens() {
        return itens;
    }

    public void setItens(List<EmprestimoItemDTO> itens) {
        this.itens = itens;
    }
}
