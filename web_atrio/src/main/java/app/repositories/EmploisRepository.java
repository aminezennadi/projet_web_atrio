package app.repositories;

import app.models.Emplois;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploisRepository extends JpaRepository<Emplois, Integer> {
}
