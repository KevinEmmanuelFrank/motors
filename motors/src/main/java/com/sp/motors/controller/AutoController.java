package com.sp.motors.controller;

import com.sp.motors.model.Auto;
import com.sp.motors.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/autos")
public class AutoController {

    @Autowired
    private AutoService autoService;



    @GetMapping
    public String mostrarAutos(@RequestParam(value = "filtro", required = false) String filtro, Model model) {
        List<Auto> autos;

        if (filtro != null && !filtro.isEmpty()) {
            autos = autoService.buscarPorMarcaOModello(filtro);
        } else {
            autos = autoService.getAllAutos();
        }

        model.addAttribute("autos", autos);
        model.addAttribute("filtro", filtro); // para que el input conserve lo buscado
        return "autos"; // archivo autos.html
    }

    @GetMapping("/{id}")

    public String mostrarAuto(@PathVariable Long id, Model model) {
        Optional<Auto> autoOpt = autoService.getAutoById(id);
        if (autoOpt.isPresent()) {
            model.addAttribute("auto", autoOpt.get());
            return "detalles-auto";
        } else {
            return "redirect:/autos";
        }
    }


    @DeleteMapping("/{id}")
    public String borrarAuto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        autoService.borrarAuto(id);
        redirectAttributes.addFlashAttribute("mensaje", "Auto eliminado correctamente.");
        return "redirect:/autos";

    }



    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<Auto> autoOpt = autoService.getAutoById(id);
        if (autoOpt.isPresent()) {
            model.addAttribute("auto", autoOpt.get());
            return "formulario-edicion";
        } else {
            return "redirect:/autos";
        }
    }

    @PostMapping("/editar/{id}")
    public String actualizarAutoDesdeFormulario(@PathVariable Long id, @ModelAttribute Auto autoDetails) {
        autoDetails.setId(id); // asegurás que mantenga el mismo ID
        Optional<Auto> updatedOptional = autoService.actualizarAuto(id, autoDetails);
        return "redirect:/autos/" + id; // redirige a la vista detallada
    }

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("auto", new Auto());
        return "formulario-crear"; // Este es el HTML del formulario de creación
    }

    @PostMapping("/crear")
    public String guardarNuevoAuto(@ModelAttribute Auto auto, RedirectAttributes redirectAttributes) {
        autoService.crearAuto(auto);
        redirectAttributes.addFlashAttribute("mensaje", "Auto creado exitosamente.");
        return "redirect:/autos";
    }
}
