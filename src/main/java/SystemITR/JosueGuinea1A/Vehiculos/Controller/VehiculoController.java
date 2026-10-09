package SystemITR.JosueGuinea1A.Vehiculos.Controller;

import SystemITR.JosueGuinea1A.Response.ApiResponse;
import SystemITR.JosueGuinea1A.Vehiculos.DTO.VehiculoDTO;
import SystemITR.JosueGuinea1A.Vehiculos.Entity.VehiculoEntity;
import SystemITR.JosueGuinea1A.Vehiculos.Service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<VehiculoEntity>>> listar() {
        List<VehiculoEntity> lista = vehiculoService.listar();
        return ResponseEntity.ok(new ApiResponse<>(200, "Listado de vehículos obtenido correctamente", lista));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VehiculoEntity>> crear(@Valid @RequestBody VehiculoDTO dto) {
        VehiculoEntity nuevo = vehiculoService.crear(dto);
        return ResponseEntity.status(201).body(new ApiResponse<>(201, "Vehículo creado correctamente", nuevo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VehiculoEntity>> actualizar(@PathVariable Long id, @Valid @RequestBody VehiculoDTO dto) {
        VehiculoEntity actualizado = vehiculoService.actualizar(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(200, "Vehículo actualizado correctamente", actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        vehiculoService.eliminar(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Vehículo eliminado correctamente"));
    }
}
