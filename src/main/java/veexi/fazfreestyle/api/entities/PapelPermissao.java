package veexi.fazfreestyle.api.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Table(name = "PAPEL_PERMISSAO")
public class PapelPermissao {
	@EmbeddedId
	private PapelPermissaoId id;

	@ManyToOne
	@MapsId("papelId")
	@JoinColumn(name = "papel_id", nullable = false)
	private Papel papel;

	@ManyToOne
	@MapsId("permissaoId")
	@JoinColumn(name = "permissao_id", nullable = false)
	private Permissao permissao;

	public PapelPermissaoId getId() {
		return id;
	}

	public void setId(PapelPermissaoId id) {
		this.id = id;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((papel == null) ? 0 : papel.hashCode());
		result = prime * result + ((permissao == null) ? 0 : permissao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PapelPermissao other = (PapelPermissao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (papel == null) {
			if (other.papel != null)
				return false;
		} else if (!papel.equals(other.papel))
			return false;
		if (permissao == null) {
			if (other.permissao != null)
				return false;
		} else if (!permissao.equals(other.permissao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PapelPermissao [id=" + id + ", papel=" + papel + ", permissao=" + permissao + "]";
	}

	// construtores, getters e setters

}
