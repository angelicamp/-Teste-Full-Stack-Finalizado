package service;

import exception.VeiculoNotFoundException;
import model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import repository.VeiculoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> getAllVeiculos() {
        return veiculoRepository.findAll();
    }

    public Veiculo getVeiculoById(@PathVariable Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException("Veiculo não encontrado com o ID: " + id));
    }

    public Veiculo addVeiculo(@RequestBody Veiculo veiculo) {
        veiculo.setCreated(LocalDateTime.now());
        return veiculoRepository.save(veiculo);
    }

    public Veiculo updateVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculoDetails) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException("Veiculo não encontrado com o ID: " + id));

        veiculo.setVeiculo(veiculoDetails.getVeiculo());
        veiculo.setMarca(veiculoDetails.getMarca());
        veiculo.setAno(veiculoDetails.getAno());
        veiculo.setDescricao(veiculoDetails.getDescricao());
        veiculo.setVendido(veiculoDetails.getVendido());
        veiculo.setUpdated(LocalDateTime.now());

        return veiculoRepository.save(veiculo);
    }

    public void deleteVeiculo(@PathVariable Long id) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException("Veiculo não encontrado com o ID: " + id));

        veiculoRepository.delete(veiculo);
    }
}
