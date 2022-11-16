package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.Associado;
import one.digitalinnovation.gof.model.AssociadoRepository;
import one.digitalinnovation.gof.model.Cargo;
import one.digitalinnovation.gof.model.CargoRepository;
import one.digitalinnovation.gof.service.AssociadoService;
import one.digitalinnovation.gof.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link CargoService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 *
 * @author DeafPenguin
 */
@Service
public class CargoServiceImpl implements CargoService {

    // Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private AssociadoRepository associadoRepository;

    @Override
    public Iterable<Cargo> buscarTodos() {
        // Buscar todos os Cargos.
        return cargoRepository.findAll();
    }

    @Override
    public Cargo buscarPorId(Long id) {
        // Buscar Cargo por ID.
        Optional<Cargo> cargo = cargoRepository.findById(id);
        return cargo.get();
    }

    @Override
    public void inserir(Cargo cargo) {
        cargoRepository.save(cargo);
    }

    @Override
    public void atualizar(Long id, Cargo cargo) {
        // Buscar Associado por ID, caso exista:
        Optional<Cargo> cargoBd = cargoRepository.findById(id);
        if (cargoBd.isPresent()) {
            cargoRepository.save(cargo);
        }
    }

    @Override
    public void deletar(Long id) {
        // Deletar Associado por ID.
        cargoRepository.deleteById(id);
    }
}
