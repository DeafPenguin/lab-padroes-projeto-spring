package one.digitalinnovation.gof.controller;

import one.digitalinnovation.gof.model.Cargo;
import one.digitalinnovation.gof.model.Cargo;
import one.digitalinnovation.gof.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 *
 * @author DeafPenguin
 */
@RestController
@RequestMapping("cargo")
public class CargoRestController {

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ResponseEntity<Iterable<Cargo>> buscarTodos() {
        return ResponseEntity.ok(cargoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cargoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Cargo> inserir(@RequestBody Cargo cargo) {
        cargoService.inserir(cargo);
        return ResponseEntity.ok(cargo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargo> atualizar(@PathVariable Long id, @RequestBody Cargo cargo) {
        cargoService.atualizar(id, cargo);
        return ResponseEntity.ok(cargo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cargoService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
