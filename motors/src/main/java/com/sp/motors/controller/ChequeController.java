package com.sp.motors.controller;

import com.sp.motors.service.ChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cheques")
public class ChequeController {
    private final ChequeService chequeService;
    @Autowired
    public ChequeController(ChequeService chequeService) {
        this.chequeService = chequeService;
    }

    @GetMapping
    public String listarCheques(Model model){
        model.addAttribute("cheques",chequeService.listarTodos());
        model.addAttribute("pageTitle","Listado de Cheques");
        return "listado-cheques";

    }
}
