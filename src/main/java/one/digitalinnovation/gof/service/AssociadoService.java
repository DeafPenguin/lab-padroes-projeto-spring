package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Associado;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de associado. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 * 
 * @author falvojr
 */
public interface AssociadoService {

	Iterable<Associado> buscarTodos();

	Associado buscarPorId(Long id);

	void inserir(Associado associado);

	void atualizar(Long id, Associado associado);

	void deletar(Long id);

}
