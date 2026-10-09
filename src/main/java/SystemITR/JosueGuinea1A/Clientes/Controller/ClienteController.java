package SystemITR.JosueGuinea1A.Clientes.Controller;

import SystemITR.JosueGuinea1A.Clientes.DTO.ClienteDTO;
import SystemITR.JosueGuinea1A.Clientes.Service.ClienteService;
import SystemITR.JosueGuinea1A.Response.ApiResponse;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Api/clientes")

public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ApiResponse<ClienteDTO>> createCliente(@ResponseBody ClienteDTO){
        try {
            ClienteService clienteService;
            createCliente();
            if (clienteService.equals(createCliente());



        }
        }
    }

}


