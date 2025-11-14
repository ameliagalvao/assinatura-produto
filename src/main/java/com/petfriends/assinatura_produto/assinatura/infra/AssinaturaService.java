package com.petfriends.assinatura_produto.assinatura.infra;

import com.petfriends.assinatura_produto.assinatura.domain.Assinatura;
import com.petfriends.assinatura_produto.assinatura.domain.FrequenciaEntrega;
import com.petfriends.assinatura_produto.assinatura.event.EventoDeDominioPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssinaturaService {

    private final AssinaturaRepository repository;
    private final EventoDeDominioPublisher publisher;

    @Transactional
    public Assinatura criarAssinatura(UUID clienteId, UUID produtoId, int quantidade, FrequenciaEntrega frequencia) {
        Assinatura assinatura = new Assinatura(clienteId, produtoId, quantidade, frequencia);
        repository.save(assinatura);
        publisher.publicarAssinaturaCriada(assinatura);
        return assinatura;
    }

    @Transactional
    public void cancelarAssinatura(UUID assinaturaId) {
        Assinatura assinatura = repository.findById(assinaturaId)
                .orElseThrow(() -> new IllegalArgumentException("Assinatura não encontrada"));
        assinatura.cancelar();
    }
}