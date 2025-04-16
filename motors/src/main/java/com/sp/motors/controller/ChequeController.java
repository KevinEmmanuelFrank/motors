
package com.sp.motors.controller;

import com.sp.motors.model.Cheque;
import com.sp.motors.model.EstadoCheque;
import com.sp.motors.service.ChequeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;

@Controller
@RequestMapping("/cheques")
public class ChequeController {
    private final ChequeService chequeService;
    @Autowired
    public ChequeController(ChequeService chequeService) {
        this.chequeService = chequeService;
    }

    @GetMapping("/listar-todos")
    public String listarCheques(Model model){
        model.addAttribute("cheques",chequeService.listarTodos());
        model.addAttribute("pageTitle","Listado de Cheques");
        return "listado-cheques";

    }
    @GetMapping("crear")
    public String mostrarFormularioCheque(Model model) {
        Cheque cheque = new Cheque();
        System.out.println("Cheque inicial: " + cheque); // Para depuración

        if (cheque.getFechaEmision() == null) {
            cheque.setFechaEmision(LocalDate.now());
            System.out.println("Fecha de emisión establecida por defecto: " + cheque.getFechaEmision()); // Para depuración
        } else {
            System.out.println("Fecha de emisión existente: " + cheque.getFechaEmision()); // Para depuración
        }

        model.addAttribute("cheque", cheque);
        model.addAttribute("pageTitle", "Crear Cheque");
        model.addAttribute("estadosCheque", EstadoCheque.values()); // <--- Corrección: Pasa el array directamente
        System.out.println("estados de los cheques: " + Arrays.toString(EstadoCheque.values())); // Para depuración
        return "crear-cheque";
    }
    /*
        @GetMapping("crear")
        public String mostrarFormularioCheque(Model model) {
            Cheque cheque = new Cheque();
            System.out.println("Cheque inicial: " + cheque); // <-- Agregá esto
            model.addAttribute("cheque", cheque);
            // model.addAttribute("fechaEmisionStr", cheque.getFechaEmision().toString()); // convertir la fecha a String
            model.addAttribute("pageTitle", "Crear Cheque");
            return "crear-cheque";

        }

     */
    @PostMapping("/guardar")
    public String guardarCheque(@Valid @ModelAttribute("cheque") Cheque cheque,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Crear Nuevo Cheque");
            return "crear-cheque";
        }
        chequeService.guardar(cheque);
        return "redirect:/cheques/listar-todos";
    }
}

