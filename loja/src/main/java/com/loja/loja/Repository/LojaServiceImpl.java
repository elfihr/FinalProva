package com.loja.loja.Repository;

import com.loja.loja.model.Hamburguer;
import com.loja.loja.service.LojaService;
import com.loja.loja.shared.LojaCompletoDto;
import com.loja.loja.shared.LojaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


    @Service
    public class LojaServiceImpl implements LojaService {

        @Autowired
        private LojaRepository repositorio;

        @Override
        public List<LojaDto> obterTodas() {
            return repositorio.findAll()
                    .stream()
                    .map(p -> new LojaDto(p.getSaborCarne(), p.getIngredientes()))
                    .toList();
        }

        @Override
        public Optional<LojaCompletoDto> obterPorId(String id) {
            Optional<Hamburguer> hamburguer = repositorio.findById(id);

            if (hamburguer.isPresent()) {
                return Optional.of(new LojaCompletoDto(hamburguer.get().getId(),
                        hamburguer.get().getSaborCarne(),
                        hamburguer.get().getIngredientes(),
                        hamburguer.get().getTamanho(),
                        hamburguer.get().getValor()));
            } else {
                return Optional.empty();
            }
        }


        @Override
        public LojaCompletoDto cadastrar(LojaCompletoDto dto) {
            Hamburguer hamburguer = new Hamburguer(dto);
            repositorio.save(hamburguer);

            return new LojaCompletoDto(hamburguer.getId(),
                    hamburguer.getSaborCarne(),
                    hamburguer.getIngredientes(),
                    hamburguer.getTamanho(),
                    hamburguer.getValor());
        }

        @Override
        public LojaCompletoDto atualizarPorId(String id, LojaCompletoDto dto) {
            Hamburguer hamburguer = repositorio.findById(id).orElse(null);

            if (hamburguer != null) {
                Hamburguer hamburguerAtualizar = new Hamburguer(dto);
                hamburguerAtualizar.setId(id);
                repositorio.save(hamburguerAtualizar);
                return new LojaCompletoDto(hamburguerAtualizar.getId(),
                        hamburguerAtualizar.getSaborCarne(),
                        hamburguerAtualizar.getIngredientes(),
                        hamburguerAtualizar.getTamanho(),
                        hamburguerAtualizar.getValor());
            } else {
                return null;
            }
        }

        @Override
        public void excluirPorId(String id) {
            repositorio.deleteById(id);
        }

    }
