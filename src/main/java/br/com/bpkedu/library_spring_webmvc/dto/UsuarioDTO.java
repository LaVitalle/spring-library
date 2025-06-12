package br.com.bpkedu.library_spring_webmvc.dto;

import java.util.List;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private String senha;
    private String email;
    private String endereco;
    private String telefone;
    private List<EmprestimoSimplesDTO> emprestimos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public List<EmprestimoSimplesDTO> getEmprestimos() { return emprestimos; }
    public void setEmprestimos(List<EmprestimoSimplesDTO> emprestimos) { this.emprestimos = emprestimos; }
}
