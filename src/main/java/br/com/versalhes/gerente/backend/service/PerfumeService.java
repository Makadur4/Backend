package br.com.versalhes.gerente.backend.service;

import br.com.versalhes.gerente.backend.model.Perfume;
import br.com.versalhes.gerente.backend.model.PerfumeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfumeService {

    final PerfumeRepository _perfumeRepository;

    public PerfumeService(PerfumeRepository perfumeRepository) {

        _perfumeRepository = perfumeRepository;

    }

    @Transactional
    public Perfume IncluirPerfume(Perfume perfume) {

        Perfume novoPerfume = _perfumeRepository.save(perfume);

        return novoPerfume;

    }

    @Transactional
    public Perfume AtualizarPerfume(Perfume perfume) throws Exception {

        Optional<Perfume> perfumeExistente = _perfumeRepository.findById(perfume.getIdPerfume());

        if (perfumeExistente.isPresent()) {

            Perfume perfumeAtualizado = _perfumeRepository.save(perfume);

            return perfumeAtualizado;

        }

        throw new Exception();

    }

    public void ExcluirPerfume(long idPerfume) throws Exception {

        Optional<Perfume> perfumeExistente = _perfumeRepository.findById(idPerfume);

        if (perfumeExistente.isPresent()) {

            _perfumeRepository.deleteById(idPerfume);

            return;

        }

        throw new Exception();

    }

    public List<Perfume> ConsultarPerfumeTodos() {

        List<Perfume> lista = _perfumeRepository.findAll();

        return lista;

    }

    public Perfume ConsultarPerfumePeloId(long idPerfume) throws Exception {

        Optional<Perfume> perfumeExistente = _perfumeRepository.findById(idPerfume);

        if (perfumeExistente.isPresent()) {

            return perfumeExistente.get();

        }

        throw new Exception();

    }

    @Transactional
    public Perfume AtualizarEstoque(long idPerfume, long estoque) throws Exception {

        Optional<Perfume> perfumeExistente = _perfumeRepository.findById(idPerfume);

        if (perfumeExistente.isPresent()) {

            perfumeExistente.get().setEstoque(estoque);

            Perfume perfumeAtualizado = _perfumeRepository.save(perfumeExistente.get());

            return perfumeAtualizado;

        }

        throw new Exception();

    }

}