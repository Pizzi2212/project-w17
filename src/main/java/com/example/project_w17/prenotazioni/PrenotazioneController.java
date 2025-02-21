package com.example.project_w17.prenotazioni;

import com.example.project_w17.auth.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {
    private final PrenotazioneService prenotazioneService;

    @PostMapping("/prenota/{eventoId}")
    public ResponseEntity<Prenotazione> prenotaPosto(@PathVariable Long eventoId, @AuthenticationPrincipal AppUser utente) {
        return ResponseEntity.ok(prenotazioneService.prenotaPosto(utente, eventoId));
    }

    @DeleteMapping("/annulla/{prenotazioneId}")
    public ResponseEntity<Void> annullaPrenotazione(@PathVariable Long prenotazioneId, @AuthenticationPrincipal AppUser utente) {
        prenotazioneService.annullaPrenotazione(utente, prenotazioneId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/mie-prenotazioni")
    public ResponseEntity<List<Prenotazione>> getMiePrenotazioni(@AuthenticationPrincipal AppUser utente) {
        return ResponseEntity.ok(prenotazioneService.getPrenotazioniByUtente(utente));
    }
}
