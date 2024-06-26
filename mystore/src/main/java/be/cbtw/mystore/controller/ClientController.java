package be.cbtw.mystore.controller;

import be.cbtw.mystore.dto.ClientDto;
import be.cbtw.mystore.model.Client;
import be.cbtw.mystore.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@Validated
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }


    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable Integer id) throws Exception {
        return clientService.getClientById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ClientDto saveClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @PutMapping("/")
    public ClientDto updateClient(@PathVariable Integer id, @RequestBody ClientDto clientDto) {
        return clientService.updateClient(id, clientDto);
    }

    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable Integer id) {
        clientService.deleteClientById(id);
    }
}
