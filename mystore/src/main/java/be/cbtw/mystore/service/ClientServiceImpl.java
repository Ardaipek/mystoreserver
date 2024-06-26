package be.cbtw.mystore.service;

import be.cbtw.mystore.converter.ClientConverter;
import be.cbtw.mystore.dto.ClientDto;
import be.cbtw.mystore.exception.ClientNotFoundException;
import be.cbtw.mystore.model.Client;
import be.cbtw.mystore.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream().map(ClientConverter::convertClientToDTO).collect(Collectors.toList());
    }

    public ClientDto getClientById(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return ClientConverter.convertClientToDTO(client.get());
        }
        throw new ClientNotFoundException("Client with id: " + id + " not found");
    }

    public ClientDto saveClient(Client client) {
        client.setCreatedAt(LocalDateTime.now());
        Client savedClient = clientRepository.save(client);
        return ClientConverter.convertClientToDTO(savedClient);
    }

    public ClientDto updateClient(Integer id, ClientDto clientDto) {
        if (clientRepository.existsById(id)) {
            Client clientEntity = ClientConverter.convertClientToEntity(clientDto);
            Client updatedClient = clientRepository.save(clientEntity);
            return ClientConverter.convertClientToDTO(updatedClient);
        }

        throw new ClientNotFoundException("Client with ID " + id + " not found");

    }


    public void deleteClientById(Integer id) {
        boolean clientExists = clientRepository.existsById(id);
        if (clientExists) {
            clientRepository.deleteById(id);
        }
        throw new ClientNotFoundException("Client with ID " + id + " not found");
    }
    
}
