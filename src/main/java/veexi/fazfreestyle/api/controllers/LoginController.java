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
import veexi.fazfreestyle.api.dto.LoginDTO;
import veexi.fazfreestyle.api.dto.UsuarioDTO;
import veexi.fazfreestyle.api.entities.Usuario;
import veexi.fazfreestyle.api.security.JwtSecurity;
import veexi.fazfreestyle.api.services.UsuarioService;

@RestController
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private JwtSecurity JwtSecurity;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO login) {
		System.out.println("LOGIN ->" + login.toString());
		try {

			Usuario usuarioAutenticado = usuarioService.autenticar(login.getUsername(), login.getPassword());
			String token = JwtSecurity.gerarToken(login.getUsername());
			UsuarioDTO usuarioDTO = new UsuarioDTO();

			System.out.println("PESSOA -> " + usuarioAutenticado.getPessoa());

			usuarioDTO.setId(usuarioAutenticado.getId());
			usuarioDTO.setToken(token);

			usuarioDTO.setPessoa(usuarioAutenticado.getPessoa());

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
