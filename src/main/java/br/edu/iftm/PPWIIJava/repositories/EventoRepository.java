package br.edu.iftm.PPWIIJava.repositories;

import br.edu.iftm.PPWIIJava.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}