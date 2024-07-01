package be.cbtw.mystore.service;

import be.cbtw.mystore.dto.ClientRecord;

import java.util.List;

public interface ClientService {
    List<ClientRecord> getAllClients();

    ClientRecord getClientById(Integer id) throws Exception;

    ClientRecord saveClient(ClientRecord clientRecord);

    ClientRecord updateClient(Integer id, ClientRecord clientRecord);

    void deleteClientById(Integer id);
}
