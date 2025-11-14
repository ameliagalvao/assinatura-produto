package com.petfriends.assinatura_produto.assinatura.infra;

import com.petfriends.assinatura_produto.assinatura.domain.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssinaturaRepository extends JpaRepository<Assinatura, UUID> {

}
