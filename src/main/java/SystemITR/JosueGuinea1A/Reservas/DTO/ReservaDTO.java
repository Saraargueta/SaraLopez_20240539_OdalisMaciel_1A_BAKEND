package SystemITR.JosueGuinea1A.Reservas.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class ReservaDTO {

    @NotNull(message = "El cliente es obligatorio")
    private Long idCliente;

    @NotNull(message = "El vehículo es obligatorio")
    private Long idVehiculo;

    @NotBlank(message = "El nombre de la reserva es obligatorio")
    @Size(max = 100, message = "El nombre de la reserva no puede superar 100 caracteres")
    private String nombreReserva;

    @NotNull(message = "La fecha de la reserva es obligatoria")
    private LocalDate fechaReserva;

    @NotNull(message = "La cantidad de pasajeros es obligatoria")
    @Min(value = 1, message = "Debe haber al menos 1 pasajero")
    private Integer cantidadPasajeros;

    @NotNull(message = "La cantidad de días es obligatoria")
    @Min(value = 1, message = "La cantidad de días debe ser al menos 1")
    @Max(value = 365, message = "La cantidad de días no puede superar 365")
    private Integer cantidadDias;
}
