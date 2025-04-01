package com.sp.motors.controller;

import com.sp.motors.model.Auto;
import com.sp.motors.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autos")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @GetMapping("/{id}")
    public Auto getAutoById(@PathVariable Long id) {
        return autoService.getAutoById(id);
    }

    @PostMapping
    public Auto crearAuto(@RequestBody Auto auto) {
        return autoService.crearAuto(auto);
    }

    @PutMapping("/{id}")
    public Auto actualizarAuto(@PathVariable Long id, @RequestBody Auto auto) {
        if (autoService.getAutoById(id) != null) {
            return autoService.actualizarAuto(auto);
        } else {
            return null;
        }


    }

    @DeleteMapping("/{id}")
    public void borrarAuto(@PathVariable Long id) {
        autoService.borrarAuto(id);
    }
}
