package rica_api.plataforma;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rica_api.investigadores.infraestructura.salida.persistencia.InvestigadorRepository;

@Component
public class ArranqueInformativo implements CommandLineRunner{

    private final SaludoInstitucionalService saludoInstitucionalService;
    private final InvestigadorRepository publicacionService;

    public ArranqueInformativo(SaludoInstitucionalService saludoInstitucionalService, InvestigadorRepository publicacionService) {
        this.saludoInstitucionalService = saludoInstitucionalService;
        this.publicacionService = publicacionService;
    }

    @Override
    public void run(String... args) {
        System.out.println(saludoInstitucionalService.mensajeDeBienvenida());
    }
}
