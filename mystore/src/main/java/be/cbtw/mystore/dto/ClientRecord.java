package be.cbtw.mystore.dto;

import java.time.LocalDateTime;

public record ClientRecord(
        Integer id,
        String username,
        String password,
        String email,
        LocalDateTime createdAt,
        LocalDateTime lastLogin) {


}
