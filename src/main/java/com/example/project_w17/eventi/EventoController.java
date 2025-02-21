package com.example.project_w17.eventi;


import com.example.project_w17.auth.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventService;

    @PostMapping
    public ResponseEntity<Evento> createEvent(@RequestBody Evento evento, @AuthenticationPrincipal AppUser organizzatore) {
        Evento createdEvent = eventService.creazioneEvento(evento, organizzatore);
        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping
    public ResponseEntity<List<Evento>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventById(@PathVariable Long id) {
        Optional<Evento> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvent(@PathVariable Long id, @RequestBody Evento newEventoData) {
        return ResponseEntity.ok(eventService.updateEvent(id, newEventoData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/posti-disponibili")
    public ResponseEntity<Integer> getPostiDisponibili(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getPostiDisponibili(id));
    }
}
