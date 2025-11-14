package com.petfriends.assinatura_produto.assinatura.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.petfriends.assinatura_produto.assinatura.domain.Assinatura;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssinaturaCriadaPublisher {
    private final PubSubTemplate pubSubTemplate;
    private final ObjectMapper objectMapper;

    private final String TOPIC = "assinatura-criada";

    public void publicarAssinaturaCriada(Assinatura assinatura) {
        try {
            AssinaturaCriadaPayload payload = AssinaturaCriadaPayload.criarPayload(assinatura);
            String json = objectMapper.writeValueAsString(payload);
            pubSubTemplate.publish(TOPIC, json);
            System.out.println("📣 Evento publicado: " + json);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao publicar evento de assinatura criada", e);
        }
    }
}
