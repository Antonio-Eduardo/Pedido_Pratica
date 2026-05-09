package repository;

import Services.PersistenciaDdados;
import entities.Cliente;
import entities.Pedido;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServicoHistorico implements PersistenciaDdados {
    private static final String FILE = "clientes.txt";
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    @Override
    public List<Cliente> listar(Cliente cliente) {
        List<Cliente> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                Cliente c = new Cliente(partes[0],
                        partes[1],
                        LocalDate.parse(partes[2],fmt),
                        partes[3]);
                list.add(c);
            }
            Collections.sort(list);
        } catch (IOException e) {
            System.out.println("erro: " + e);
        }
        return list;
    }

    @Override
    public void salvar(Cliente c) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE,true))) {
            String linha = c.getNome() + ";" +
                    c.getEmail() + ";" +
                    c.getDataDeNascimento().format(fmt) + ";" +
                    c.getCpf();
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("erro: " + e);
        }
    }
}
