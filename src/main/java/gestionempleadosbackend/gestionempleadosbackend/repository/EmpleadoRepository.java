package gestionempleadosbackend.gestionempleadosbackend.repository;

import gestionempleadosbackend.gestionempleadosbackend.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
}
