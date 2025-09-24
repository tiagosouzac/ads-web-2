package br.edu.iftm.PPWIIJava.services;

import br.edu.iftm.PPWIIJava.entities.Evento;
import br.edu.iftm.PPWIIJava.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> buscarPorId(Long id) {
        return eventoRepository.findById(id);
    }

    public Evento salvar(Evento evento) {
        try {
            return eventoRepository.save(evento);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar evento: " + e.getMessage(), e);
        }
    }

    public void excluir(Long id) {
        try {
            if (!eventoRepository.existsById(id)) {
                throw new RuntimeException("Evento não encontrado com ID: " + id);
            }

            eventoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir evento: " + e.getMessage(), e);
        }
    }

    public boolean existePorId(Long id) {
        return eventoRepository.existsById(id);
    }

    public long contarEventos() {
        return eventoRepository.count();
    }
}