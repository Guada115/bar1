package org.example.bar1.controller;


import org.example.bar1.model.*;
import org.example.bar1.repository.HorarioBarRepository;
import org.example.bar1.repository.MesaBarRepository;
import org.example.bar1.repository.PedidoBarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.bar1.model.EstadoPedido;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping("/mesas")
public class MesaBarController {
    @Autowired
    private MesaBarRepository mesaBarRepository;
    @Autowired
    private HorarioBarRepository horarioBarRepository;

    @GetMapping
    public String listarMesas(Model model) {
        model.addAttribute("mesas", mesaBarRepository.findAll());
        return "mesas/lista";
    }
    @PostMapping("/guardar")
    public String guardarMesa(
            @RequestParam int capacidad,
            @RequestParam String ubicacion,
            @RequestParam EstadoMesa estado,
            @RequestParam TipoMesa tipo){
        MesaBar mesa = new MesaBar();
        mesa.setCapacidad(capacidad);
        mesa.setUbicacion(ubicacion);
        mesa.setEstado(estado);
        mesa.setTipo(tipo);

        mesaBarRepository.save(mesa);
        return "redirect:/mesas";

    }
    @GetMapping("/nueva")
    public String mostrarFormularioCombinado(Model model){
        model.addAttribute("mesa", new MesaBar());
        model.addAttribute("horario", new HorarioBar());
        return "mesas/crear-combined";
    }
    @PostMapping("/guardar-combined")
    public String guardarMesaYHorario(
            @RequestParam int capacidad,
            @RequestParam String ubicacion,
            @RequestParam EstadoMesa estado,
            @RequestParam TipoMesa tipo,
            @RequestParam LocalTime horaApertura,
            @RequestParam LocalTime horaCierre,
            @RequestParam DiaSemana dia,
            @RequestParam(required = false) boolean horaFeliz) {

        //primero guardar el horario
        HorarioBar horario = new HorarioBar();
        horario.setHoraApertura(horaApertura);
        horario.setHoraCierre(horaCierre);
        horario.setDia(dia);
        horario.setHoraFeliz(horaFeliz);
        HorarioBar horarioGuardado = horarioBarRepository.save(horario);

        //asignar el horario a la mesa
        MesaBar mesa = new MesaBar();
        mesa.setCapacidad(capacidad);
        mesa.setUbicacion(ubicacion);
        mesa.setEstado(estado);
        mesa.setTipo(tipo);
        mesa.setHorarioId(horarioGuardado.getId()); // Asignar el ID del horario guardado

        mesaBarRepository.save(mesa);

        return "redirect:/mesas";

    }
    @GetMapping("{mesaId}/horario")
    public String verHorarioMesa(@PathVariable Long mesaId, Model model){
        //Buscar la mesa
        MesaBar mesa = mesaBarRepository.findById(mesaId).orElseThrow(()-> new IllegalArgumentException("Mesa no encontrada con ID: " + mesaId));

        //Buscar el horario asociado a la mesa
        HorarioBar horario = null;
        if (mesa.getHorarioId() != null){
            horario = horarioBarRepository.findById(mesa.getHorarioId())
                    .orElse(null);
        }
        model.addAttribute("mesa", mesa);
        model.addAttribute("horario", horario);
        return "mesas/ver-horario";

    }
    //Gestionar pedidos

    @Autowired
    private PedidoBarRepository pedidoBarRepository;

    //Mostrar formulario para nuevo pedido
    @GetMapping("/{mesaId}/nuevo-pedido")
    public String mostrarFormularioPedido(@PathVariable Long mesaId, Model model){
        model.addAttribute("pedido", new PedidoBar());
        model.addAttribute("mesaId", mesaId);
        return "mesas/formulario-pedido";
    }
    //Guardar nuevo pedido
    @PostMapping("/{mesaId}/guardar-pedido")
    public String guardarPedido(
            @PathVariable Long mesaId,
            @RequestParam String producto,
            @RequestParam int cantidad,
            @RequestParam double precioUnitario,
            @RequestParam EstadoPedido estado)
                {

                    PedidoBar pedido = new PedidoBar();
                    pedido.setProducto(producto);
                    pedido.setCantidad(cantidad);
                    pedido.setPrecioUnitario(precioUnitario);
                    pedido.setEstado(estado);
                    pedido.setMesaId(mesaId);

        pedidoBarRepository.save(pedido);
        return "redirect:/mesas/" + mesaId + "/pedidos";

    }
    //Listar pedidos de una mesa
    @GetMapping("/{mesaId}/pedidos")
    public String listarPedidosMesa(@PathVariable Long mesaId, Model model){
        model.addAttribute("mesa", mesaBarRepository.findById(mesaId)
                .orElseThrow(() -> new IllegalArgumentException("Mesa no encontrada con ID: " + mesaId)));
        model.addAttribute("pedidos", pedidoBarRepository.findByMesaId(mesaId));
        return "mesas/lista-pedidos";
    }




}
