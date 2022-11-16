package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Cargo;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cargo. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 *
 * @author DeafPenguin
 */
public interface CargoService {
    Iterable<Cargo> buscarTodos();

    Cargo buscarPorId(Long id);

    void inserir(Cargo cargo);

    void atualizar(Long id, Cargo cargo);

    void deletar(Long id);

}
