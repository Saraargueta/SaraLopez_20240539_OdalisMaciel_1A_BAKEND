package SystemITR.JosueGuinea1A.Reservas.Service;

import SystemITR.JosueGuinea1A.Clientes.Entity.ClienteEntity;
import SystemITR.JosueGuinea1A.Clientes.Repository.ClienteRepository;
import SystemITR.JosueGuinea1A.Exceptions.RecursoNoEncontradoException;
import SystemITR.JosueGuinea1A.Exceptions.ReglaNegocioException;
import SystemITR.JosueGuinea1A.Reservas.DTO.ReservaDTO;
import SystemITR.JosueGuinea1A.Reservas.Entity.ReservaEntity;
import SystemITR.JosueGuinea1A.Reservas.Repository.ReservaRepository;
import SystemITR.JosueGuinea1A.Vehiculos.Entity.VehiculoEntity;
import SystemITR.JosueGuinea1A.Vehiculos.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaService {

    public static final String PENDIENTE = "PENDIENTE";
    public static final String FINALIZADA = "FINALIZADA";

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Transactional(readOnly = true)
    public List<ReservaEntity> listar() {
        return reservaRepository.findAll();
    }

    @Transactional
    public ReservaEntity crear(ReservaDTO dto) {
        ReservaEntity reserva = new ReservaEntity();
        aplicarDatos(dto, reserva);
        reserva.setEstado(PENDIENTE);
        return reservaRepository.save(reserva);
    }

    @Transactional
    public ReservaEntity actualizar(Long id, ReservaDTO dto) {
        ReservaEntity reserva = buscar(id);
        if (FINALIZADA.equals(reserva.getEstado())) {
            throw new ReglaNegocioException("No se puede modificar una reserva que ya está finalizada");
        }
        aplicarDatos(dto, reserva);
        return reservaRepository.save(reserva);
    }

    @Transactional
    public ReservaEntity finalizar(Long id) {
        ReservaEntity reserva = buscar(id);
        if (FINALIZADA.equals(reserva.getEstado())) {
            throw new ReglaNegocioException("La reserva ya está finalizada");
        }
        reserva.setEstado(FINALIZADA);
        return reservaRepository.save(reserva);
    }

    @Transactional
    public void eliminar(Long id) {
        ReservaEntity reserva = buscar(id);
        reservaRepository.delete(reserva);
    }

    private ReservaEntity buscar(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Reserva con id " + id + " no encontrada"));
    }


    private void aplicarDatos(ReservaDTO dto, ReservaEntity reserva) {
        ClienteEntity cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente con id " + dto.getIdCliente() + " no encontrado"));
        VehiculoEntity vehiculo = vehiculoRepository.findById(dto.getIdVehiculo())
                .orElseThrow(() -> new RecursoNoEncontradoException("Vehículo con id " + dto.getIdVehiculo() + " no encontrado"));

        // La cantidad de pasajeros no puede superar la capacidad del vehículo
        if (dto.getCantidadPasajeros() > vehiculo.getCapacidad()) {
            throw new ReglaNegocioException("La cantidad de pasajeros (" + dto.getCantidadPasajeros()
                    + ") supera la capacidad del vehículo (" + vehiculo.getCapacidad() + ")");
        }

        reserva.setCliente(cliente);
        reserva.setVehiculo(vehiculo);
        reserva.setNombreReserva(dto.getNombreReserva().trim());
        reserva.setFechaReserva(dto.getFechaReserva());
        reserva.setCantidadPasajeros(dto.getCantidadPasajeros());
        reserva.setCantidadDias(dto.getCantidadDias());
        reserva.setTotalPago(dto.getCantidadDias() * vehiculo.getPrecioDia());
    }
}
