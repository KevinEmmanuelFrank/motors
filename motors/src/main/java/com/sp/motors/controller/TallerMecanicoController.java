package com.sp.motors.controller;


import com.sp.motors.model.TallerMecanico;
import com.sp.motors.service.TallerMecanicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taller_mecanico")
public class TallerMecanicoController {

    @Autowired
    private TallerMecanicoService tallerMecanicoService;

    @GetMapping("/{id}")
    public TallerMecanico getTallerById(@PathVariable Long id) {
        return tallerMecanicoService.getTallerById(id);
    }

    @PostMapping
    public TallerMecanico crearTaller(@RequestBody TallerMecanico taller) {
        return tallerMecanicoService.crearTaller(taller);
    }

    @PutMapping("/{id}")
    public TallerMecanico actualizarTaller(@PathVariable Long id, @RequestBody TallerMecanico taller) {
        if (tallerMecanicoService.getTallerById(id) != null) {
            return tallerMecanicoService.actualizarTaller(taller);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void borrarTaller(@PathVariable Long id) {
        tallerMecanicoService.borrarTaller(id);
    }
}