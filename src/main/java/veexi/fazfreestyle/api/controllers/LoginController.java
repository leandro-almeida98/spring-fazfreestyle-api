package veexi.fazfreestyle.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import veexi.fazfreestyle.api.dto.UsuarioDTO;
import veexi.fazfreestyle.api.entities.Usuario;
import veexi.fazfreestyle.api.security.JwtSecurity;
import veexi.fazfreestyle.api.services.UsuarioService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private JwtSecurity JwtSecurity;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Usuario usuario) {
		try {
			Usuario usuarioAutenticado = usuarioService.autenticar(usuario.getUsername(), usuario.getPassword());
			String token = JwtSecurity.gerarToken(usuario.getUsername());
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioAutenticado, token);

			return ResponseEntity.ok(usuarioDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response, null);
		return "redirect:/login?logout";
	}
}
