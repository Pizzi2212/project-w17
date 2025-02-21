package com.example.project_w17.prenotazioni;


import com.example.project_w17.auth.AppUser;
import com.example.project_w17.eventi.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByUtente(AppUser utente);
    List<Prenotazione> findByEvento(Evento evento);
    Optional<Prenotazione> findByUtenteAndEvento(AppUser utente, Evento evento);
}
