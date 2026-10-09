package SystemITR.JosueGuinea1A.Clientes.Repository;

import SystemITR.JosueGuinea1A.Clientes.Entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdClienteNot(String email, Long idCliente);
}
