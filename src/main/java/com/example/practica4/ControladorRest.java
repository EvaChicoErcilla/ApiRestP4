package com.example.practica4;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/personas") // base path
public class ControladorRest {
    private final List<Persona> personas = new ArrayList<>();

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Persona persona, BindingResult result) {
        if (result.hasErrors()) {
            throw new ExcepcionPersonaInvalida(result);
        }

        boolean yaExiste = personas.stream().anyMatch(p ->
                p.nombre().equalsIgnoreCase(persona.nombre()) &&
                        p.apellidos().equalsIgnoreCase(persona.apellidos()) &&
                        p.email().equalsIgnoreCase(persona.email())
        );

        if (yaExiste) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Esa persona ya está registrada.");
        }

        personas.add(persona);
        return ResponseEntity.ok(persona);

    }

    @GetMapping("/{nombre}")
    public List<Persona> obtenerPorNombre(@PathVariable String nombre) {
        return personas.stream()
                .filter(p -> p.nombre().equalsIgnoreCase(nombre))
                .toList();
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editarPorNombreYApellidos(@RequestBody Persona nuevosDatos) {
        for (int i = 0; i < personas.size(); i++) {
            Persona p = personas.get(i);
            if (p.nombre().equalsIgnoreCase(nuevosDatos.nombre()) &&
                    p.apellidos().equalsIgnoreCase(nuevosDatos.apellidos())) {

                Persona actualizada = new Persona(p.nombre(), p.apellidos(), nuevosDatos.email());
                personas.set(i, actualizada);
                return ResponseEntity.ok(actualizada);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada.");
    }



    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarPorNombreApellidosEmail(@RequestBody Persona datos) {
        boolean eliminado = personas.removeIf(p ->
                p.nombre().equalsIgnoreCase(datos.nombre()) &&
                        p.apellidos().equalsIgnoreCase(datos.apellidos()) &&
                        p.email().equalsIgnoreCase(datos.email())
        );

        return eliminado
                ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada.");
    }

    @ExceptionHandler(ExcepcionPersonaInvalida.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ModeloCampoIncorrecto> contadorIncorrecto(ExcepcionPersonaInvalida ex) {
        return ex.getErrores().stream().map(error -> new ModeloCampoIncorrecto(
                error.getDefaultMessage(), error.getField(), error.getRejectedValue()
        )).toList();
    }

    public void borrarTodas() {
        personas.clear();
    }


}
