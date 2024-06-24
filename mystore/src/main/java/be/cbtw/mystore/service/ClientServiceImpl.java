package be.cbtw.mystore.service;

import be.cbtw.mystore.entity.Client;
import be.cbtw.mystore.exception.ClientNotFoundException;
import be.cbtw.mystore.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Integer id) throws Exception {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client with id: " + id + " not found"));
    }

    public Client saveClient(Client client) {
        client.setCreatedAt(LocalDateTime.now());
        return clientRepository.save(client);
    }

    public Client updateClient(Integer id, Client client) {
        boolean existingClient = clientRepository.existsById(id);
        if (existingClient) {
            return clientRepository.save(client);

        } else {
            throw new ClientNotFoundException("Client with ID " + id + " not found");

        }

    }

    public void deleteClientById(Integer id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Client with ID " + id + " not found");
        }
        clientRepository.deleteById(id);
    }


}
