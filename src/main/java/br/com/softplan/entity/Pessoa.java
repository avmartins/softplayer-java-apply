package br.com.softplan.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.softplan.web.jsonview.Views;

/**
 * @author Anderson Virginio Martins
 */
@Entity
@Table(name = "Pessoas")
public class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1022092624232399374L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Public.class)
	private Long id;

	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private String nome;

	@JsonView(Views.Public.class)
	private String sexo;

	@JsonView(Views.Public.class)
	private String email;

	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private String dataNascimento;

	@Column(unique = true, nullable = false)
	private String cpf;

	private String naturalidade;

	private String nacionalidade;

	private LocalDateTime dataCadastro;
	private LocalDateTime dataAtualização;

	public Pessoa() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataAtualização() {
		return dataAtualização;
	}

	public void setDataAtualização(LocalDateTime dataAtualização) {
		this.dataAtualização = dataAtualização;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Pessoa(Long id, String nome, String sexo, String email, String dataNascimento, String cpf,
			String naturalidade, String nacionalidade, LocalDateTime dataCadastro, LocalDateTime dataAtualização) {
		super();
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.naturalidade = naturalidade;
		this.nacionalidade = nacionalidade;
		this.dataCadastro = dataCadastro;
		this.dataAtualização = dataAtualização;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataAtualização == null) ? 0 : dataAtualização.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nacionalidade == null) ? 0 : nacionalidade.hashCode());
		result = prime * result + ((naturalidade == null) ? 0 : naturalidade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataAtualização == null) {
			if (other.dataAtualização != null)
				return false;
		} else if (!dataAtualização.equals(other.dataAtualização))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nacionalidade == null) {
			if (other.nacionalidade != null)
				return false;
		} else if (!nacionalidade.equals(other.nacionalidade))
			return false;
		if (naturalidade == null) {
			if (other.naturalidade != null)
				return false;
		} else if (!naturalidade.equals(other.naturalidade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		return true;
	}
	
	public String getDataNascimentoView() {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		String pattern2 = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);
		Date date = null;
		
		try {
			date = simpleDateFormat.parse(dataNascimento);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return simpleDateFormat2.format(date);
	}

}
