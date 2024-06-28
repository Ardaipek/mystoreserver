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
        return new Client(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getEmail(), dto.getLastLogin());
    }
}
