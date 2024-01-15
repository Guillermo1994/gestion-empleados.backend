package gestionempleadosbackend.gestionempleadosbackend.controller;

import gestionempleadosbackend.gestionempleadosbackend.excepciones.ResourceNotFoundException;
import gestionempleadosbackend.gestionempleadosbackend.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gestionempleadosbackend.gestionempleadosbackend.repository.EmpleadoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository repository;

    /**
     * este metodolista todos los empleados registrados
     *
     * @return Lista de empleados
     */
    @GetMapping("/empleados")
    public List<Empleado> ListAllEmpleados() {
        return repository.findAll();
    }

    @PostMapping("/empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
        return repository.save(empleado);
    }

    /**
     * Se obtiene un empleado buscamdolo por ID
     *
     * @param id parametro de identificaon del empleado
     * @return returna un empleado
     */
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadosPorId(@PathVariable Long id) {
        Empleado empleado = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "No existe el empleado con el ID " + id));

        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleadosPorId(@PathVariable Long id, @RequestBody Empleado empleado) {
        Empleado empleadoUpdate = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "No existe el empleado con el ID " + id));

        empleadoUpdate.setNombre(empleado.getNombre());
        empleadoUpdate.setApellido(empleado.getApellido());
        empleadoUpdate.setEmail(empleado.getEmail());

        Empleado empleadoAct = repository.save(empleadoUpdate);
        return ResponseEntity.ok(empleadoAct);

    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String,Boolean>> EliminarEmpleadosPorId(
            @PathVariable Long id) {
        Empleado empleadoeliminar = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "No existe el empleado con el ID " + id));

        repository.delete(empleadoeliminar);
        Map <String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return  ResponseEntity.ok(respuesta);

    }
}
