package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {

	private BigDecimal salario;
	private String funcao;

	public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
		super(nome, dataNascimento);
		this.setSalario(salario);
		this.setFuncao(funcao);
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public void aumento(double porcentagem) {
		setSalario(getSalario().add(getSalario().multiply(BigDecimal.valueOf(porcentagem))));
	}

}
