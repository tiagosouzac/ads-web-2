package br.edu.iftm.PPWIIJava.controllers;

import br.edu.iftm.PPWIIJava.entities.Evento;
import br.edu.iftm.PPWIIJava.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    public String listarEventos(Model model) {
        model.addAttribute("eventos", eventoRepository.findAll());
        return "eventos/list";
    }

    @GetMapping("/novo")
    public String novoEvento(Model model) {
        model.addAttribute("evento", new Evento());
        return "eventos/form";
    }

    @GetMapping("/editar/{id}")
    public String editarEvento(@PathVariable("id") Long id, Model model) {
        Optional<Evento> evento = eventoRepository.findById(id);
        if (evento.isPresent()) {
            model.addAttribute("evento", evento.get());
            return "eventos/form";
        } else {
            return "redirect:/eventos";
        }
    }

    @PostMapping("/salvar")
    public String salvarEvento(@ModelAttribute Evento evento, RedirectAttributes redirectAttributes) {
        try {
            eventoRepository.save(evento);
            redirectAttributes.addFlashAttribute("mensagem", "Evento salvo com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao salvar evento: " + e.getMessage());
        }
        return "redirect:/eventos";
    }

    @GetMapping("/excluir/{id}")
    public String excluirEvento(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            eventoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensagem", "Evento excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir evento: " + e.getMessage());
        }
        return "redirect:/eventos";
    }
}