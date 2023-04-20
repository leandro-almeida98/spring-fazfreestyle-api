package veexi.fazfreestyle.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import veexi.fazfreestyle.api.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);

	List<Usuario> findByPessoaEmailContainingIgnoreCase(String email);

	List<Usuario> findByPessoaNomeContainingIgnoreCase(String nome);
}
