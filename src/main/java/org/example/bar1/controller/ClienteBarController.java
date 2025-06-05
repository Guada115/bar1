package org.example.bar1.controller;

import org.example.bar1.model.ClienteBar;
import org.example.bar1.model.HorarioBar;
import org.example.bar1.model.TipoCliente;
import org.example.bar1.repository.ClienteBarRepository;
import org.example.bar1.repository.HorarioBarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.bar1.model.ClienteHorario;
import org.example.bar1.repository.HorarioClienteRepository;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteBarController {

    @Autowired
    private ClienteBarRepository clienteBarRepository;

    // Listar clientes
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteBarRepository.findAll());
        return "clientes/clientes-lista";
    }
    //formulario crear clientes
    @GetMapping("/nuevo")
    public String mostrarFormularioCliente(Model model){
        model.addAttribute("cliente", new ClienteBar());
        return "clientes/crear-cliente";
    }

    //guardar cliente
    @PostMapping("/guardar")
    public String guardarCliente(
            @RequestParam String nombre,
            @RequestParam String telefono,
            @RequestParam String tipoCliente,
            @RequestParam String bebidaFavorita) {
        ClienteBar cliente = new ClienteBar();
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        cliente.setTipoCliente(TipoCliente.valueOf(tipoCliente.toUpperCase()));
        cliente.setBebidaFavorita(bebidaFavorita);

        clienteBarRepository.save(cliente);
        return "redirect:/clientes";
    }

    @Autowired
    private HorarioBarRepository horarioBarRepository;

    // Mostrar formulario para asignar horario a cliente

    @GetMapping("/{clienteId}/asignar-horario")
    public String mostrarFormularioAsignacion(
            @PathVariable Long clienteId, Model model) {
        model.addAttribute("cliente", clienteBarRepository.findById(clienteId).orElseThrow());

        // Verifica que hay horarios disponibles
        List<HorarioBar> horarios = (List<HorarioBar>) horarioBarRepository.findAll();
        if (horarios.isEmpty()) {
            // Si no hay horarios, puedes redirigir o mostrar un mensaje
            return "redirect:/clientes?error=No hay horarios disponibles";
        }
        model.addAttribute("horariosDisponibles", horarios);
        return "clientes/asignar-horario";
    }

    // Guardar asignación de horario a cliente
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/{clienteId}/asignar-horario")
    public String asignarHorarioACliente(
            @PathVariable Long clienteId,
            @RequestParam Long horarioId) {
        String sql = "INSERT INTO cliente_horario (cliente_id, horario_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, clienteId, horarioId);
        return "redirect:/clientes";
    }

    //Ver los horarios de un cliente
    @GetMapping("/{clienteId}/horarios")
    public String verHorariosCliente(
            @PathVariable Long clienteId, Model model) {

        //Obtener el cliente y su horario
        ClienteBar cliente = clienteBarRepository.findById(clienteId).orElseThrow();
        List<HorarioBar> horarios = horarioBarRepository.findHorariosByClienteId(clienteId);

        model.addAttribute("cliente", cliente);
        model.addAttribute("horarios", horarios);
        return "clientes/horarios-cliente";
    }
    //ver lista de horarios
    @GetMapping("/horarios")
    public String listarHorarios(Model model) {
        model.addAttribute("horarios", horarioBarRepository.findAll());
        return "clientes/horarios-lista";
    }

    //ver los clientes de un horario específico
    @GetMapping("/horario/{horarioId}/clientes")
    public String verClienteDeHorario(
            @PathVariable Long horarioId, Model model){
        //Obtener el horario y sus clientes
        HorarioBar horario = horarioBarRepository.findById(horarioId).orElseThrow();
        List<ClienteBar> clientes = clienteBarRepository.findClientesByHorarioId(horarioId);

        model.addAttribute("horario", horario);
        model.addAttribute("clientes", clientes);
        return "clientes/clientes-horario";

    }
}

