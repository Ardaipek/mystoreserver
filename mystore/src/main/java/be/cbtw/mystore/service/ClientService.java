package be.cbtw.mystore.service;

import be.cbtw.mystore.entity.Client;
import be.cbtw.mystore.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Integer id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            return client.get();
        }

        log.info("Client with id: {} doesn't exist", id);
        return null;
    }

    public Client saveClient(Client client) {
        client.setCreatedAt(LocalDateTime.now());

        Client savedClient = clientRepository.save(client);

        log.info("Client with id: {} saved successfully", client.getId());
        return savedClient;
    }

    public Client updateClient(Client client) {
        Optional<Client> existingClient = clientRepository.findById(client.getId());
        client.setCreatedAt(existingClient.get().getCreatedAt());

        Client updatedClient = clientRepository.save(client);

        log.info("Client with id: {} successfully updated", updatedClient.getId());

        return updatedClient;
    }

    public void deleteClientById(Integer id) {
        clientRepository.deleteById(id);
        log.info("client with id: {} successfully deleted", id);
    }


}
