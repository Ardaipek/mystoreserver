package be.cbtw.mystore.service;

import be.cbtw.mystore.converter.ClientConverter;
import be.cbtw.mystore.dto.ClientRecord;
import be.cbtw.mystore.exception.BusinessException;
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

    public List<ClientRecord> getAllClients() {
        return clientRepository.findAll().stream().map(ClientConverter::convertClientToRecord).collect(Collectors.toList());
    }

    public ClientRecord getClientById(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return ClientConverter.convertClientToRecord(client.get());
        }
        throw new BusinessException("Client with id: " + id + " not found");
    }

    public ClientRecord saveClient(ClientRecord clientRecord) {
        Client client = ClientConverter.convertRecordToEntity(clientRecord);
        client.setCreatedAt(LocalDateTime.now());
        Client savedClient = clientRepository.save(client);
        return ClientConverter.convertClientToRecord(savedClient);
    }

    public ClientRecord updateClient(Integer id, ClientRecord clientRecord) {
        Client savedClient = clientRepository.findById(id).orElseThrow(() -> new BusinessException("Client with ID " + id + " not found"));
        Client clientEntity = ClientConverter.convertRecordToEntity(clientRecord);
        clientEntity.setCreatedAt(savedClient.getCreatedAt());
        return ClientConverter.convertClientToRecord(clientEntity);


    }


    public void deleteClientById(Integer id) {
        boolean clientExists = clientRepository.existsById(id);
        if (clientExists) {
            clientRepository.deleteById(id);
        } else {
            throw new BusinessException("Client with ID " + id + " not found");

        }
    }

}
