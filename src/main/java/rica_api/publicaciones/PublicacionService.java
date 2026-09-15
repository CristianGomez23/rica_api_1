package rica_api.publicaciones;

import org.springframework.stereotype.Service;
import rica_api.compartido.LimiteAnualExcedidoException;
import rica_api.compartido.RecursoNoEncontradoException;
import rica_api.investigadores.CorreoInstitucional;
import rica_api.investigadores.Investigador;
import rica_api.investigadores.InvestigadorRepository;

import java.util.List;

@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final InvestigadorRepository investigadorRepository;
    private final LimitePublicacionesAnualesService limiteService;

    public PublicacionService(PublicacionRepository publicacionRepository,
                              InvestigadorRepository investigadorRepository, LimitePublicacionesAnualesService limiteService) {
        this.publicacionRepository = publicacionRepository;
        this.investigadorRepository = investigadorRepository;
        this.limiteService = limiteService;
    }

    public Publicacion registrar(Publicacion publicacion) {
        Investigador investigador = investigadorRepository.findByCorreoInstitucional(new CorreoInstitucional(publicacion.getInvestigadorCorreo()));
            if (investigador == null) {
                throw new RecursoNoEncontradoException(
                        "No existe un investigador con correo " + publicacion.getInvestigadorCorreo());
            };

        if (!limiteService.puedeRegistrar(investigador, publicacion)) {
            throw new LimiteAnualExcedidoException(
                    "El investigador ha alcanzado el límite máximo de 5 publicaciones para este año.");
        }

        return publicacionRepository.save(publicacion);
    }

    public List<Publicacion> listarPorInvestigador(String investigadorCorreo) {
        return publicacionRepository.findByInvestigadorCorreo(investigadorCorreo);
    }

    public Publicacion buscarPorId(String id) {
        return publicacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe una publicación con id " + id));
    }
}