// src/main/java/com/sp/motors/controller/TallerMecanicoController.java
package com.sp.motors.controller;

import com.sp.motors.model.TallerMecanico;
import com.sp.motors.service.TallerMecanicoService; // Importa la interfaz
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Cambiado de @RestController
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/talleres")
public class TallerMecanicoController {

    @Autowired
    private TallerMecanicoService tallerMecanicoService;


    @GetMapping
    public String mostrarTalleres(@RequestParam(value = "filtro", required = false) String filtro,
                                  Model model) {
        List<TallerMecanico> talleres;

        if (filtro != null && !filtro.isEmpty()) {
            talleres = tallerMecanicoService.buscarPorNombre(filtro);
        } else {
            talleres = tallerMecanicoService.getAllTalleres();
        }
        model.addAttribute("filtro", filtro);

        talleres = tallerMecanicoService.getAllTalleres();
        model.addAttribute("talleres", talleres);
        return "talleres";
    }


    @GetMapping("/{id}")
    public String mostrarTaller(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<TallerMecanico> tallerOpt = tallerMecanicoService.getTallerById(id);
        if (tallerOpt.isPresent()) {
            model.addAttribute("taller", tallerOpt.get());
            return "detalles-taller";
        } else {
            redirectAttributes.addFlashAttribute("error", "Taller no encontrado.");
            return "redirect:/talleres";
        }
    }


    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("taller", new TallerMecanico());
        return "formulario-crear-taller";
    }

    // --- Procesar Creación ---
    @PostMapping("/crear")
    public String guardarNuevoTaller(@ModelAttribute TallerMecanico taller, RedirectAttributes redirectAttributes) {
        try {
            tallerMecanicoService.crearTaller(taller);
            redirectAttributes.addFlashAttribute("mensaje", "Taller creado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el taller: " + e.getMessage());
            // Podrías redirigir de nuevo al formulario con los datos ingresados
            // model.addAttribute("taller", taller); // Necesitarías pasar el Model también
            // return "formulario-crear-taller";
        }
        return "redirect:/talleres";
    }

    // --- Mostrar Formulario de Edición ---
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<TallerMecanico> tallerOpt = tallerMecanicoService.getTallerById(id);
        if (tallerOpt.isPresent()) {
            model.addAttribute("taller", tallerOpt.get());
            return "formulario-editar-taller"; // Nombre archivo HTML: formulario-editar-taller.html
        } else {
            redirectAttributes.addFlashAttribute("error", "Taller no encontrado.");
            return "redirect:/talleres";
        }
    }

    // --- Procesar Edición ---
    @PostMapping("/editar/{id}")
    public String actualizarTaller(@PathVariable Long id, @ModelAttribute TallerMecanico tallerDetails, RedirectAttributes redirectAttributes) {

        try {
            Optional<TallerMecanico> updatedOptional = tallerMecanicoService.actualizarTaller(id, tallerDetails);
            if (updatedOptional.isPresent()) {
                redirectAttributes.addFlashAttribute("mensaje", "Taller actualizado correctamente.");
                return "redirect:/talleres/" + id; // Redirige a la vista de detalles
            } else {
                redirectAttributes.addFlashAttribute("error", "No se pudo actualizar, taller no encontrado.");
                return "redirect:/talleres";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el taller: " + e.getMessage());
            return "redirect:/talleres/editar/" + id; // Vuelve al formulario de edición
        }
    }

    // --- Procesar Borrado ---
    // Usamos POST para el borrado si no quieres configurar el filtro HiddenHttpMethodFilter para DELETE
    @PostMapping("/borrar/{id}")
    public String borrarTaller(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean borrado = tallerMecanicoService.borrarTaller(id);
        if (borrado) {
            redirectAttributes.addFlashAttribute("mensaje", "Taller eliminado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar, taller no encontrado.");
        }
        return "redirect:/talleres";
    }

    /* // Alternativa usando DELETE (necesita <input type="hidden" name="_method" value="delete"/> en el form)
    @DeleteMapping("/{id}")
    public String borrarAutoDelete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean borrado = tallerMecanicoService.borrarTaller(id);
        if (borrado) {
             redirectAttributes.addFlashAttribute("mensaje", "Taller eliminado correctamente.");
         } else {
             redirectAttributes.addFlashAttribute("error", "No se pudo eliminar, taller no encontrado.");
         }
        return "redirect:/talleres";
    }
    */
}