package controller;

import model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.VeiculoService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    private final VeiculoService veiculoService;

    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public List<Veiculo> getAllVeiculos() {
        return veiculoService.getAllVeiculos();
    }

    @GetMapping("/{id}")
    public Veiculo getVeiculoById(@PathVariable Long id) {
        return veiculoService.getVeiculoById(id);
    }

    @PostMapping
    public Veiculo addVeiculo(@RequestBody Veiculo veiculo) {
        veiculo.setCreated(LocalDateTime.now());
        return veiculoService.addVeiculo(veiculo);
    }

    @PutMapping("/{id}")
    public Veiculo updateVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculoDetails) {
        Veiculo veiculo = veiculoService.getVeiculoById(id);

        veiculo.setVeiculo(veiculoDetails.getVeiculo());
        veiculo.setMarca(veiculoDetails.getMarca());
        veiculo.setAno(veiculoDetails.getAno());
        veiculo.setDescricao(veiculoDetails.getDescricao());
        veiculo.setVendido(veiculoDetails.getVendido());
        veiculo.setUpdated(LocalDateTime.now());

        return veiculoService.addVeiculo(veiculo);
    }

    @DeleteMapping("/{id}")
    public void deleteVeiculo(@PathVariable Long id) {
        Veiculo veiculo = veiculoService.getVeiculoById(id);

        veiculoService.deleteVeiculo(veiculo.getId());
    }
}
