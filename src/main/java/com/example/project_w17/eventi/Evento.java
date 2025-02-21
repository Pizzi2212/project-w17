package com.example.project_w17.eventi;


import com.example.project_w17.auth.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "eventi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private String luogo;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private int maxPartecipanti;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private AppUser organizzatore;
}
