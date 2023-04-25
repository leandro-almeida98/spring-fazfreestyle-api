package veexi.fazfreestyle.api.dto;

import veexi.fazfreestyle.api.entities.Pessoa;
import veexi.fazfreestyle.api.entities.Usuario;

public class UsuarioDTO {
	private Long id;
	private Pessoa pessoa;
	private String token;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UsuarioDTO(Usuario Usuario, String token) {
		super();
		this.id = Usuario.getId();
		this.pessoa = Usuario.getPessoa();
		this.token = token;
	}

}
