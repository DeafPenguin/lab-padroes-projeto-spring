package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import one.digitalinnovation.gof.model.Associado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.AssociadoRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.AssociadoService;
import one.digitalinnovation.gof.service.ViaCepService;

/**
 * Implementação da <b>Strategy</b> {@link AssociadoService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author falvojr
 */
@Service
public class AssociadoServiceImpl implements AssociadoService {

	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private AssociadoRepository associadoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;
	
	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Associado> buscarTodos() {
		// Buscar todos os Associados.
		return associadoRepository.findAll();
	}

	@Override
	public Associado buscarPorId(Long id) {
		// Buscar Associado por ID.
		Optional<Associado> associado = associadoRepository.findById(id);
		return associado.get();
	}

	@Override
	public void inserir(Associado associado) {
		salvarAssociadoComCep(associado);
	}

	@Override
	public void atualizar(Long id, Associado associado) {
		// Buscar Associado por ID, caso exista:
		Optional<Associado> associadoBd = associadoRepository.findById(id);
		if (associadoBd.isPresent()) {
			salvarAssociadoComCep(associado);
		}
	}

	@Override
	public void deletar(Long id) {
		// Deletar Associado por ID.
		associadoRepository.deleteById(id);
	}

	private void salvarAssociadoComCep(Associado associado) {
		// Verificar se o Endereco do Associado já existe (pelo CEP).
		String cep = associado.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			// Caso não exista, integrar com o ViaCEP e persistir o retorno.
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		associado.setEndereco(endereco);
		// Inserir Associado, vinculando o Endereco (novo ou existente).
		associadoRepository.save(associado);
	}

}
