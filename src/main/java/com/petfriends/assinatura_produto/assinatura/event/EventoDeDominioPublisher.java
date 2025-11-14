package com.petfriends.assinatura_produto.assinatura.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventoDeDominioPublisher {
    private final PubSubTemplate pubSubTemplate;
    private final ObjectMapper objectMapper;

    public void publicarEventoDeDominio(EventoDeDominio evento) {
        try {
            String json = objectMapper.writeValueAsString(evento);
            String topico = evento.getTipoDoEvento();

            pubSubTemplate.publish(topico, json);
            System.out.println("📣 Evento publicado no tópico [" + topico + "]: " + json);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao publicar evento de domínio: " + evento.getTipoDoEvento(), e);
        }
    }
}
