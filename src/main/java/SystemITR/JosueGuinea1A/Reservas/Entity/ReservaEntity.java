package SystemITR.JosueGuinea1A.Reservas.Entity;

import SystemITR.JosueGuinea1A.Clientes.Entity.ClienteEntity;
import SystemITR.JosueGuinea1A.Vehiculos.Entity.VehiculoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "RESERVAS")
@Getter
@Setter
@NoArgsConstructor
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESERVA")
    private Long idReserva;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_VEHICULO", nullable = false)
    private VehiculoEntity vehiculo;

    @Column(name = "NOMBRE_RESERVA", nullable = false, length = 100)
    private String nombreReserva;

    @Column(name = "FECHA_RESERVA", nullable = false)
    private LocalDate fechaReserva;

    @Column(name = "CANTIDAD_PASAJEROS", nullable = false)
    private Integer cantidadPasajeros;

    @Column(name = "CANTIDAD_DIAS", nullable = false)
    private Integer cantidadDias;

    // PENDIENTE | FINALIZADA
    @Column(name = "ESTADO", nullable = false, length = 20)
    private String estado;

    @Column(name = "TOTAL_PAGO", nullable = false)
    private Double totalPago;
}
