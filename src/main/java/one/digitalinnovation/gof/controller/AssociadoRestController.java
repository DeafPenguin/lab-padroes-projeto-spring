package one.digitalinnovation.gof.controller;

import one.digitalinnovation.gof.model.Associado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.service.AssociadoService;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 * 
 * @author falvojr
 */
@RestController
@RequestMapping("associados")
public class AssociadoRestController {

	@Autowired
	private AssociadoService associadoService;

	@GetMapping
	public ResponseEntity<Iterable<Associado>> buscarTodos() {
		return ResponseEntity.ok(associadoService.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Associado> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(associadoService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Associado> inserir(@RequestBody Associado associado) {
		associadoService.inserir(associado);
		return ResponseEntity.ok(associado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Associado> atualizar(@PathVariable Long id, @RequestBody Associado associado) {
		associadoService.atualizar(id, associado);
		return ResponseEntity.ok(associado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		associadoService.deletar(id);
		return ResponseEntity.ok().build();
	}
}
