package rica_api;

import java.util.*;

public interface InvestigadorRepository {

    List<Investigador> findAll();

    Optional<Investigador> findById(Long id);

    Investigador save(Investigador investigador);

    boolean existsByCorreoInstitucional(String correoInstitucional);
}
