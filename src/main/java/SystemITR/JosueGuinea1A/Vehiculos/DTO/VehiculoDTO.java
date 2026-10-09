package SystemITR.JosueGuinea1A.Vehiculos.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiculoDTO {

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 50, message = "La marca no puede superar 50 caracteres")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(max = 50, message = "El modelo no puede superar 50 caracteres")
    private String modelo;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser al menos 1 pasajero")
    @Max(value = 100, message = "La capacidad no puede superar 100 pasajeros")
    private Integer capacidad;

    @NotNull(message = "El precio por día es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio por día debe ser mayor que 0")
    private Double precioDia;

    @NotBlank(message = "La placa es obligatoria")
    @Size(max = 20, message = "La placa no puede superar 20 caracteres")
    private String placa;
}
