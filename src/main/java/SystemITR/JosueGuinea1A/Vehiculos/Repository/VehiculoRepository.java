package SystemITR.JosueGuinea1A.Vehiculos.Repository;

import SystemITR.JosueGuinea1A.Vehiculos.Entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long> {

    boolean existsByPlaca(String placa);

    boolean existsByPlacaAndIdVehiculoNot(String placa, Long idVehiculo);
}
