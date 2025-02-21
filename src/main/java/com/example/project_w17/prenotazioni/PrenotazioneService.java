package com.example.project_w17.prenotazioni;

import com.example.project_w17.auth.AppUser;
import com.example.project_w17.eventi.Evento;
import com.example.project_w17.eventi.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {
    private final PrenotazioneRepository prenotazioneRepository;
    private final EventoRepository eventoRepository;

    public Prenotazione prenotaPosto(AppUser utente, Long eventoId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));

        if (prenotazioneRepository.findByUtenteAndEvento(utente, evento).isPresent()) {
            throw new RuntimeException("Hai già prenotato un posto per questo evento");
        }

        if (evento.getMaxPartecipanti() <= prenotazioneRepository.findByEvento(evento).size()) {
            throw new RuntimeException("Non ci sono più posti disponibili per questo evento");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setEvento(evento);
        return prenotazioneRepository.save(prenotazione);
    }

    public void annullaPrenotazione(AppUser utente, Long prenotazioneId) {
        Prenotazione prenotazione = prenotazioneRepository.findById(prenotazioneId)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));

        if (!prenotazione.getUtente().equals(utente)) {
            throw new RuntimeException("Non puoi annullare una prenotazione che non è tua");
        }

        prenotazioneRepository.delete(prenotazione);
    }

    public List<Prenotazione> getPrenotazioniByUtente(AppUser utente) {
        return prenotazioneRepository.findByUtente(utente);
    }
}
