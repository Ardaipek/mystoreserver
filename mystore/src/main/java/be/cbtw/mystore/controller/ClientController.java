package be.cbtw.mystore.controller;

import be.cbtw.mystore.dto.ClientRecord;
import be.cbtw.mystore.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientRecord> getAllClients() {
        return clientService.getAllClients();
    }


    @GetMapping("/{id}")
    public ClientRecord getClientById(@PathVariable Integer id) throws Exception {
        return clientService.getClientById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ClientRecord saveClient(@RequestBody ClientRecord clientRecord) {
        return clientService.saveClient(clientRecord);
    }

    @PutMapping("/{id}")
    public ClientRecord updateClient(@PathVariable Integer id, @RequestBody ClientRecord clientRecord) {
        return clientService.updateClient(id, clientRecord);
    }

    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable Integer id) {
        clientService.deleteClientById(id);
    }
}
