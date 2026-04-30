package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente {
    String nome;
    String email;
    Date DataDeNascimento;

    public Cliente(){}
    public Cliente(String nome, String email, Date dataDeNascimento){
        this.nome = nome;
        this.email = email;
        this.DataDeNascimento = dataDeNascimento;
    }

    public Date getDataDeNascimento() {
        return DataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        DataDeNascimento = dataDeNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        StringBuilder sb = new StringBuilder("DataDeNascimento = ").append(sdf.format(DataDeNascimento)).append("\n");
        sb.append("Nome = ").append(nome).append("\n");
        sb.append("Email = ").append(email);

        return sb.toString();
    }
}
