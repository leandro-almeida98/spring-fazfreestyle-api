package veexi.fazfreestyle.api.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "sobre_nome")
	private String sobreNome;

	@Column(name = "email")
	private String email;

	@Column(name = "image")
	private String image;

	@Column(name = "mc")
	private boolean mc;

	@Column(name = "vulgo")
	private String vulgo;

	@Column(name = "instagram")
	private String instagram;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cidade_id", referencedColumnName = "id")
	private Cidade cidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cidade_estado_id", referencedColumnName = "id")
	private Estado estado;

	@Column(name = "youtube")
	private String youtube;

	@Column(name = "biografia")
	private String biografia;

	@Column(name = "telefone")
	private Integer telefone;

	@Column(name = "data_cadastro")
	private LocalDateTime dataCadastro;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	// getters e setters
}
