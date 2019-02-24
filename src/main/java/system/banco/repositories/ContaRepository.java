package system.banco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import system.banco.models.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{

}
