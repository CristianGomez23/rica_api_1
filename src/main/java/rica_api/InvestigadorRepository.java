package rica_api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface InvestigadorRepository extends JpaRepository<Investigador, Long> {

    boolean existsByCorreoInstitucional(String correoInstitucional);

}
