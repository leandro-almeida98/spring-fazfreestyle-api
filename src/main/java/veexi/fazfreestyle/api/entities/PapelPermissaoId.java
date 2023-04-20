package veexi.fazfreestyle.api.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PapelPermissaoId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "papel_id")
	private Long papelId;

	@Column(name = "permissao_id")
	private Long permissaoId;

	public Long getPapelId() {
		return papelId;
	}

	public void setPapelId(Long papelId) {
		this.papelId = papelId;
	}

	public Long getPermissaoId() {
		return permissaoId;
	}

	public void setPermissaoId(Long permissaoId) {
		this.permissaoId = permissaoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((papelId == null) ? 0 : papelId.hashCode());
		result = prime * result + ((permissaoId == null) ? 0 : permissaoId.hashCode());
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
		PapelPermissaoId other = (PapelPermissaoId) obj;
		if (papelId == null) {
			if (other.papelId != null)
				return false;
		} else if (!papelId.equals(other.papelId))
			return false;
		if (permissaoId == null) {
			if (other.permissaoId != null)
				return false;
		} else if (!permissaoId.equals(other.permissaoId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PapelPermissaoId [papelId=" + papelId + ", permissaoId=" + permissaoId + "]";
	}

	// construtores, getters e setters

}
