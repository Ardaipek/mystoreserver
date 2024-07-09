package be.cbtw.mystore.converter;

import be.cbtw.mystore.dto.ClientRecord;
import be.cbtw.mystore.model.Client;

public class ClientConverter {

    public static ClientRecord convertClientToRecord(Client client) {
        return new ClientRecord(
                client.getId(),
                client.getUsername(),
                client.getPassword(),
                client.getEmail(),
                client.getCreatedAt(),
                client.getLastLogin());
    }


    public static Client convertRecordToEntity(ClientRecord record) {
        return new Client(
                record.id(),
                record.username(),
                record.password(),
                record.email(),
                record.lastLogin());
    }
}
