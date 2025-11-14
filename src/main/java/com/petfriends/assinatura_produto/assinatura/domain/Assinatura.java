package com.petfriends.assinatura_produto.assinatura.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Assinatura {
    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID clienteId;

    @Column(nullable = false)
    private UUID produtoId;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private LocalDate dataInicio;

    private LocalDate dataCancelamento;

    @Enumerated(EnumType.STRING)
    private FrequenciaEntrega frequenciaEntrega;

    @Enumerated(EnumType.STRING)
    private StatusAssinatura status;

    public Assinatura(UUID clienteId, UUID produtoId, int quantidade, FrequenciaEntrega frequenciaEntrega) {
        validar(clienteId, produtoId, quantidade, frequenciaEntrega);

        this.id = UUID.randomUUID();
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.frequenciaEntrega = frequenciaEntrega;
        this.dataInicio = LocalDate.now();
        this.status = StatusAssinatura.ATIVA;
    }

    private void validar(UUID clienteId, UUID produtoId, int quantidade, FrequenciaEntrega frequenciaEntrega) {
        if (clienteId == null) {
            throw new IllegalArgumentException("Cliente ID é obrigatório.");
        }

        if (produtoId == null) {
            throw new IllegalArgumentException("Produto ID é obrigatório.");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }

        if (frequenciaEntrega == null) {
            throw new IllegalArgumentException("Frequência de entrega deve ser informada.");
        }
    }

    public void cancelar() {
        if (this.status == StatusAssinatura.CANCELADA) {
            throw new IllegalStateException("Assinatura já está cancelada.");
        }
        this.status = StatusAssinatura.CANCELADA;
        this.dataCancelamento = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assinatura)) return false;
        Assinatura that = (Assinatura) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}