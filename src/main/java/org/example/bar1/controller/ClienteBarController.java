package org.example.bar1.controller;

import org.example.bar1.model.ClienteBar;
import org.example.bar1.model.TipoCliente;
import org.example.bar1.repository.ClienteBarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
}
