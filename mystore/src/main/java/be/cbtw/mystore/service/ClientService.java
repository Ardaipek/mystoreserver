package be.cbtw.mystore.service;

import be.cbtw.mystore.dto.ClientDto;
import be.cbtw.mystore.model.Client;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAllClients();

    ClientDto getClientById(Integer id) throws Exception;

    ClientDto saveClient(Client client);

    ClientDto updateClient(Integer id, ClientDto clientDto);

    void deleteClientById(Integer id);
}
