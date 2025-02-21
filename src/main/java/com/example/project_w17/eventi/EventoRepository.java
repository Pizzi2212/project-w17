package com.example.project_w17.eventi;


import com.example.project_w17.auth.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByOrganizzatoreId(AppUser organizzatoreId);
}