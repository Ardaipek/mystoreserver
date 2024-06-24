package be.cbtw.mystore.service;

import be.cbtw.mystore.entity.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();

    Client getClientById(Integer id) throws Exception;

    Client saveClient(Client client);

    Client updateClient(Integer id, Client updatedClient);

    void deleteClientById(Integer id);
}
