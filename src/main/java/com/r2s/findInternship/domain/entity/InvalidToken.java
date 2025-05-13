package com.r2s.findInternship.domain.entity;

import com.google.auto.value.AutoValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@AutoValue.Builder
@Entity
@Table(name = "invalid_token")
public class InvalidToken {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "expiry_time")
    private LocalDateTime expiryTime;
}