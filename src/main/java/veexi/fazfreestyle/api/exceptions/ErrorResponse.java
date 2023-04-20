package veexi.fazfreestyle.api.exceptions;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONObject;

public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataHora;
	private String mensagem;
	private String detalhe;

	public ErrorResponse(Date dataHora, String mensagem, String detalhe) {
		this.dataHora = dataHora;
		this.mensagem = mensagem;
		this.detalhe = detalhe;
	}

	public String toJson() {
		JSONObject json = new JSONObject();
		json.put("dataHora", this.dataHora);
		json.put("mensagem", this.mensagem);
		json.put("detalhe", this.detalhe);
		return json.toString();
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

}
