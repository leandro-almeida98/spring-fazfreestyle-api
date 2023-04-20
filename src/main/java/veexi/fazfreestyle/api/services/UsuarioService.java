package veexi.fazfreestyle.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import veexi.fazfreestyle.api.entities.Usuario;
import veexi.fazfreestyle.api.exceptions.handle.SenhaInvalidaException;
import veexi.fazfreestyle.api.exceptions.handle.UsuarioInativoException;
import veexi.fazfreestyle.api.exceptions.handle.UsuarioNaoEncontradoException;
import veexi.fazfreestyle.api.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// Busca todos os usuários
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	// Busca um usuário pelo id
	public Usuario getUsuarioById(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	// Cria um novo usuário
	public Usuario createUsuario(Usuario usuario) {
		// Criptografa a senha
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}

	// Atualiza um usuário existente
	public Usuario updateUsuario(Long id, Usuario usuario) {
		Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
		if (usuarioExistente != null) {
			usuarioExistente.setUsername(usuario.getUsername());
			usuarioExistente.setAtivo(usuario.getAtivo());
			usuarioExistente.setPessoa(usuario.getPessoa());
			// Atualize outros campos de acordo com suas necessidades

			usuarioRepository.save(usuarioExistente);
		}
		return usuarioExistente;
	}

	// Deleta um usuário pelo id
	public boolean deleteUsuarioById(Long id) {
		Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
		if (usuarioExistente != null) {
			usuarioRepository.delete(usuarioExistente);
			return true;
		}
		return false;
	}

	// Busca todos os usuários por nome
	public List<Usuario> getAllUsuariosByNome(String nome) {
		return usuarioRepository.findByPessoaNomeContainingIgnoreCase(nome);
	}

	// Busca todos os usuários por email
	public List<Usuario> getAllUsuariosByEmail(String email) {
		return usuarioRepository.findByPessoaEmailContainingIgnoreCase(email);
	}

	public Usuario autenticar(String username, String password) {
		Usuario usuario = usuarioRepository.findByUsername(username);

		String senhaCriptografada = passwordEncoder.encode(password);
		System.out.println("SENHA CRIPTOGRAFADA -> " + senhaCriptografada);

		if (usuario == null) {
			throw new UsuarioNaoEncontradoException("Usuário não encontrado");
		}

		if (!usuario.getAtivo()) {
			throw new UsuarioInativoException("Usuário inativo");
		}

		// Verifica a senha criptografada
		if (!passwordEncoder.matches(password, usuario.getPassword())) {
			throw new SenhaInvalidaException("Senha inválida");
		}

		return usuario;
	}
}
