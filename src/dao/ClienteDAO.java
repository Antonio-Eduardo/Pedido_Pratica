package dao;

import entities.Cliente;

public interface ClienteDAO {
    void  salvar(Cliente c);
    Cliente buscarCliente(Cliente c);
    void deletarCliente(Cliente c);
    void updateCliente(Cliente c);
}
