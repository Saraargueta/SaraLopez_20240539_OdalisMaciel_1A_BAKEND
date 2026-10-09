package SystemITR.JosueGuinea1A.Reservas.Repository;

import SystemITR.JosueGuinea1A.Reservas.Entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

    boolean existsByCliente_IdCliente(Long idCliente);

    boolean existsByVehiculo_IdVehiculo(Long idVehiculo);
}
