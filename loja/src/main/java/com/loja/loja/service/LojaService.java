package com.loja.loja.service;

import com.loja.loja.shared.LojaCompletoDto;
import com.loja.loja.shared.LojaDto;

import java.util.List;
import java.util.Optional;

public interface LojaService {
    List<LojaDto> obterTodas();
    Optional<LojaCompletoDto> obterPorId(String id);
    LojaCompletoDto cadastrar(LojaCompletoDto dto);
    LojaCompletoDto atualizarPorId(String id, LojaCompletoDto dto);
    void excluirPorId(String id);
}
