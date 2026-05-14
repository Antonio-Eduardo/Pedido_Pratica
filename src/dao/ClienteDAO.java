package dao;

import entities.Cliente;

public interface ClienteDAO {
    void  salvar(Cliente c);
    Cliente buscarContaPorId(Long id);
    void deletarCliente(Long id);

}
