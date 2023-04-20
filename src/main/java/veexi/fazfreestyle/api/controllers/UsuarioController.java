package veexi.fazfreestyle.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import veexi.fazfreestyle.api.entities.Usuario;
import veexi.fazfreestyle.api.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		List<Usuario> usuarios = usuarioService.getAllUsuarios();
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or @usuarioSecurity.isUsuarioValid(authentication,#id)")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario) {
		Usuario novoUsuario = usuarioService.createUsuario(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoUsuario.getId())
				.toUri();
		return ResponseEntity.created(uri).body(novoUsuario);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or @usuarioSecurity.isUsuarioValid(authentication,#id)")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		Usuario usuarioAtualizado = usuarioService.updateUsuario(id, usuario);
		if (usuarioAtualizado == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarioAtualizado);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or @usuarioSecurity.isUsuarioValid(authentication,#id)")
	public ResponseEntity<Void> deleteUsuarioById(@PathVariable Long id) {
		boolean sucesso = usuarioService.deleteUsuarioById(id);
		if (!sucesso) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/nome/{nome}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Usuario>> getAllUsuariosByNome(@PathVariable String nome) {
		List<Usuario> usuarios = usuarioService.getAllUsuariosByNome(nome);
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/email/{email}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Usuario>> getAllUsuariosByEmail(@PathVariable String email) {
		List<Usuario> usuarios = usuarioService.getAllUsuariosByEmail(email);
		return ResponseEntity.ok(usuarios);
	}
}
