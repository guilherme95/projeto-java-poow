package br.ufsm.csi.model;

public class Usuario {

    private int id_usuario;
    private String nome_usuario;
    private String rg_usuario;
    private String cpf_usuario;
    private String tel_usuario;
    private String email_usuario;
    private String senha_usuario;

    public Usuario() {}

    public Usuario(String nome_usuario, String rg_usuario, String cpf_usuario, String tel_usuario, String email_usuario, String senha_usuario) {
        this.nome_usuario = nome_usuario;
        this.rg_usuario = rg_usuario;
        this.cpf_usuario = cpf_usuario;
        this.tel_usuario = tel_usuario;
        this.email_usuario = email_usuario;
        this.senha_usuario = senha_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getRg_usuario() {
        return rg_usuario;
    }

    public void setRg_usuario(String rg_usuario) {
        this.rg_usuario = rg_usuario;
    }

    public String getCpf_usuario() {
        return cpf_usuario;
    }

    public void setCpf_usuario(String cpf_usuario) {
        this.cpf_usuario = cpf_usuario;
    }

    public String getTel_usuario() {
        return tel_usuario;
    }

    public void setTel_usuario(String tel_usuario) {
        this.tel_usuario = tel_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }
}
