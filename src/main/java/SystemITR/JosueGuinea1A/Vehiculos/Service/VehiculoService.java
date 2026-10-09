package SystemITR.JosueGuinea1A.Vehiculos.Service;

import SystemITR.JosueGuinea1A.Exceptions.ConflictoException;
import SystemITR.JosueGuinea1A.Exceptions.RecursoNoEncontradoException;
import SystemITR.JosueGuinea1A.Reservas.Repository.ReservaRepository;
import SystemITR.JosueGuinea1A.Vehiculos.DTO.VehiculoDTO;
import SystemITR.JosueGuinea1A.Vehiculos.Entity.VehiculoEntity;
import SystemITR.JosueGuinea1A.Vehiculos.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Transactional(readOnly = true)
    public List<VehiculoEntity> listar() {
        return vehiculoRepository.findAll();
    }

    @Transactional
    public VehiculoEntity crear(VehiculoDTO dto) {
        String placa = dto.getPlaca().trim().toUpperCase();
        if (vehiculoRepository.existsByPlaca(placa)) {
            throw new ConflictoException("Ya existe un vehículo con la placa " + placa);
        }
        VehiculoEntity vehiculo = new VehiculoEntity();
        copiar(dto, vehiculo);
        return vehiculoRepository.save(vehiculo);
    }

    @Transactional
    public VehiculoEntity actualizar(Long id, VehiculoDTO dto) {
        VehiculoEntity vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Vehículo con id " + id + " no encontrado"));
        String placa = dto.getPlaca().trim().toUpperCase();
        if (vehiculoRepository.existsByPlacaAndIdVehiculoNot(placa, id)) {
            throw new ConflictoException("Ya existe otro vehículo con la placa " + placa);
        }
        copiar(dto, vehiculo);
        return vehiculoRepository.save(vehiculo);
    }

    @Transactional
    public void eliminar(Long id) {
        VehiculoEntity vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Vehículo con id " + id + " no encontrado"));
        // No se pueden eliminar registros que contienen valores foráneos (reservas asociadas)
        if (reservaRepository.existsByVehiculo_IdVehiculo(id)) {
            throw new ConflictoException("No se puede eliminar el vehículo porque tiene reservas asociadas");
        }
        vehiculoRepository.delete(vehiculo);
    }

    private void copiar(VehiculoDTO dto, VehiculoEntity vehiculo) {
        vehiculo.setMarca(dto.getMarca().trim());
        vehiculo.setModelo(dto.getModelo().trim());
        vehiculo.setCapacidad(dto.getCapacidad());
        vehiculo.setPrecioDia(dto.getPrecioDia());
        vehiculo.setPlaca(dto.getPlaca().trim().toUpperCase());
    }
}
