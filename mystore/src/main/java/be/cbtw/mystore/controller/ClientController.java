package be.cbtw.mystore.controller;

import be.cbtw.mystore.entity.Client;
import be.cbtw.mystore.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Validated
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok().body(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(clientService.getClientById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        return ResponseEntity.ok().body(clientService.saveClient(client));
    }

    @PutMapping("/")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        return ResponseEntity.ok().body(clientService.updateClient(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable Integer id) {
        clientService.deleteClientById(id);

        return ResponseEntity.ok().body("Client deleted successfully");
    }
}
