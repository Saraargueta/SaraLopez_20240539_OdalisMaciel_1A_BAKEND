package SystemITR.JosueGuinea1A.Clientes.Service;

import SystemITR.JosueGuinea1A.Clientes.DTO.ClienteDTO;
import SystemITR.JosueGuinea1A.Clientes.Entity.ClienteEntity;
import SystemITR.JosueGuinea1A.Clientes.Repository.ClienteRepository;
import SystemITR.JosueGuinea1A.Exceptions.ConflictoException;
import SystemITR.JosueGuinea1A.Exceptions.RecursoNoEncontradoException;
import SystemITR.JosueGuinea1A.Reservas.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Transactional(readOnly = true)
    public List<ClienteEntity> listar() {
        return clienteRepository.findAll();
    }

    @Transactional
    public ClienteEntity crear(ClienteDTO dto) {
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new ConflictoException("Ya existe un cliente con el email " + dto.getEmail());
        }
        ClienteEntity cliente = new ClienteEntity();
        copiar(dto, cliente);
        return clienteRepository.save(cliente);
    }

    @Transactional
    public ClienteEntity actualizar(Long id, ClienteDTO dto) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente con id " + id + " no encontrado"));
        if (clienteRepository.existsByEmailAndIdClienteNot(dto.getEmail(), id)) {
            throw new ConflictoException("Ya existe otro cliente con el email " + dto.getEmail());
        }
        copiar(dto, cliente);
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void eliminar(Long id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente con id " + id + " no encontrado"));

        if (reservaRepository.existsByCliente_IdCliente(id)) {
            throw new ConflictoException("No se puede eliminar el cliente porque tiene reservas asociadas");
        }
        clienteRepository.delete(cliente);
    }

    private void copiar(ClienteDTO dto, ClienteEntity cliente) {
        cliente.setNombre(dto.getNombre().trim());
        cliente.setApellido(dto.getApellido().trim());
        cliente.setTelefono(dto.getTelefono().trim());
        cliente.setEmail(dto.getEmail().trim());
        cliente.setDireccion(dto.getDireccion().trim());
    }
}
