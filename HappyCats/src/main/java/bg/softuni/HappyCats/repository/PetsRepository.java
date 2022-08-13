package bg.softuni.HappyCats.repository;

import bg.softuni.HappyCats.model.entity.Pets;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.DoubleStream;

@Repository
public interface PetsRepository extends JpaRepository<Pets, Long> {
}
