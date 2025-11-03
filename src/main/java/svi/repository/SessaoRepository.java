package svi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import svi.model.Sessao;

@ApplicationScoped
public class SessaoRepository implements PanacheRepository<Sessao> {
}
