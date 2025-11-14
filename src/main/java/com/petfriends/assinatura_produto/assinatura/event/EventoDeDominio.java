package com.petfriends.assinatura_produto.assinatura.event;

import java.time.Instant;

public abstract class EventoDeDominio {
    private final Instant dataHoraCriacao;
    private final String tipoDoEvento;

    protected EventoDeDominio() {
        this.dataHoraCriacao = Instant.now();
        this.tipoDoEvento = this.getClass().getSimpleName();
    }

    public Instant getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public String getTipoDoEvento() {
        return tipoDoEvento;
    }
}