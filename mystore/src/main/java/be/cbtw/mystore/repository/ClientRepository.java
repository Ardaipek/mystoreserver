package be.cbtw.mystore.repository;

import be.cbtw.mystore.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
