package be.cbtw.mystore.converter;

import be.cbtw.mystore.dto.ClientDto;
import be.cbtw.mystore.model.Client;

public class ClientConverter {

    public static ClientDto convertClientToDTO(Client client) {
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setUsername(client.getUsername());
        dto.setPassword(client.getPassword());
        dto.setEmail(client.getEmail());
        dto.setCreatedAt(client.getCreatedAt());
        dto.setLastLogin(client.getLastLogin());
        return dto;
    }

    public static Client convertClientToEntity(ClientDto dto) {
        Client client = new Client();
        client.setUsername(dto.getUsername());
        client.setPassword(dto.getPassword());
        client.setEmail(dto.getEmail());

        client.setCreatedAt(dto.getCreatedAt());
        client.setLastLogin(dto.getLastLogin());
        return client;
    }
}
