package be.cbtw.mystore.controller;

import be.cbtw.mystore.dto.ClientDto;
import be.cbtw.mystore.model.Client;
import be.cbtw.mystore.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }


    @GetMapping("/clients/{id}")
    public ClientDto getClientById(@PathVariable Integer id) throws Exception {
        return clientService.getClientById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/clients")
    public ClientDto saveClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @PutMapping("/clients/{id}")
    public ClientDto updateClient(@PathVariable Integer id, @RequestBody ClientDto clientDto) {
        return clientService.updateClient(id, clientDto);
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClientById(@PathVariable Integer id) {
        clientService.deleteClientById(id);
    }
}
