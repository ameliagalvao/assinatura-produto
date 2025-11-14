package com.petfriends.assinatura_produto.assinatura.event;

import com.petfriends.assinatura_produto.assinatura.domain.Assinatura;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class AssinaturaCriadaPayload extends EventoDeDominio {

    private final UUID assinaturaId;
    private final UUID clienteId;
    private final UUID produtoId;
    private final int quantidade;
    private final String frequenciaEntrega;

    public AssinaturaCriadaPayload(
            UUID assinaturaId,
            UUID clienteId,
            UUID produtoId,
            int quantidade,
            String frequenciaEntrega
    ) {
        super("AssinaturaCriada");
        this.assinaturaId = assinaturaId;
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.frequenciaEntrega = frequenciaEntrega;
    }

    public static AssinaturaCriadaPayload criarPayload(Assinatura assinatura) {
        return new AssinaturaCriadaPayload(
                assinatura.getId(),
                assinatura.getClienteId(),
                assinatura.getProdutoId(),
                assinatura.getQuantidade(),
                assinatura.getFrequenciaEntrega().name()
        );
    }
}