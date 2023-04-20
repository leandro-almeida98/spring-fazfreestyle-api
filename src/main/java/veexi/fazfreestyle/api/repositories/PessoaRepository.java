package veexi.fazfreestyle.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import veexi.fazfreestyle.api.entities.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	List<Pessoa> findByNome(String nome);

	List<Pessoa> findBySobrenome(String sobrenome);

	List<Pessoa> findByEmail(String email);

}
