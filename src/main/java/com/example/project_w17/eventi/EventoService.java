package com.example.project_w17.eventi;


import com.example.project_w17.auth.AppUser;
import com.example.project_w17.prenotazioni.PrenotazioneRepository;
import com.example.project_w17.prenotazioni.PrenotazioneService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventoService {
    private final EventoRepository eventoRepository;
    private final PrenotazioneRepository prenotazioneRepository;

    public Evento creazioneEvento(Evento evento, AppUser organizzatore) {
        evento.setOrganizzatore(organizzatore);
        return eventoRepository.save(evento);
    }
    public List<Evento> getAllEvents() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> getEventById(Long id) {
        return eventoRepository.findById(id);
    }

    public List<Evento> getEventsByOrganizer(AppUser organizerId) {
        return eventoRepository.findByOrganizzatoreId(organizerId);
    }

    public Evento updateEvent(Long id, Evento newEventData) {
        return eventoRepository.findById(id).map(event -> {
            event.setTitolo(newEventData.getTitolo());
            event.setDescrizione(newEventData.getDescrizione());
            event.setLuogo(newEventData.getLuogo());
            event.setData(newEventData.getData());
            event.setMaxPartecipanti(newEventData.getMaxPartecipanti());
            return eventoRepository.save(event);
        }).orElseThrow(() -> new RuntimeException("Evento non trovato"));
    }

    public void deleteEvent(Long id) {
        eventoRepository.deleteById(id);
    }

    public int getPostiDisponibili(Long eventoId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));
        int prenotazioni = prenotazioneRepository.findByEvento(evento).size();
        return evento.getMaxPartecipanti() - prenotazioni;
    }

    public Evento updateEvent(Long id, Evento newEventData, AppUser organizzatore) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));

        if (!evento.getOrganizzatore().equals(organizzatore)) {
            throw new AccessDeniedException("Non sei autorizzato a modificare questo evento");
        }

        evento.setTitolo(newEventData.getTitolo());
        evento.setDescrizione(newEventData.getDescrizione());
        evento.setLuogo(newEventData.getLuogo());
        evento.setData(newEventData.getData());
        evento.setMaxPartecipanti(newEventData.getMaxPartecipanti());
        return eventoRepository.save(evento);
    }

    public void deleteEvent(Long id, AppUser organizzatore) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));

        if (!evento.getOrganizzatore().equals(organizzatore)) {
            throw new AccessDeniedException("Non sei autorizzato a eliminare questo evento");
        }

        eventoRepository.delete(evento);
    }
}
