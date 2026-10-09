package SystemITR.JosueGuinea1A.Reservas.Controller;

import SystemITR.JosueGuinea1A.Reservas.DTO.ReservaDTO;
import SystemITR.JosueGuinea1A.Reservas.Entity.ReservaEntity;
import SystemITR.JosueGuinea1A.Reservas.Service.ReservaService;
import SystemITR.JosueGuinea1A.Response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReservaEntity>>> listar() {
        List<ReservaEntity> lista = reservaService.listar();
        return ResponseEntity.ok(new ApiResponse<>(200, "Listado de reservas obtenido correctamente", lista));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReservaEntity>> crear(@Valid @RequestBody ReservaDTO dto) {
        ReservaEntity nueva = reservaService.crear(dto);
        return ResponseEntity.status(201).body(new ApiResponse<>(201, "Reserva creada correctamente", nueva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReservaEntity>> actualizar(@PathVariable Long id, @Valid @RequestBody ReservaDTO dto) {
        ReservaEntity actualizada = reservaService.actualizar(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(200, "Reserva actualizada correctamente", actualizada));
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<ApiResponse<ReservaEntity>> finalizar(@PathVariable Long id) {
        ReservaEntity finalizada = reservaService.finalizar(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Reserva finalizada correctamente", finalizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Reserva eliminada correctamente"));
    }
}
