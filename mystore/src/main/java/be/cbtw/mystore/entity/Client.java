package be.cbtw.mystore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    private Integer id;
    private String username;
    private String password;
    private String email;
    private LocalDate createdAt;
    private LocalDate lastLogin;

}
