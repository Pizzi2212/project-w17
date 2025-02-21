package com.example.project_w17.eventi;


import com.example.project_w17.auth.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventoService {
    private final EventoRepository eventoRepository;

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
}
