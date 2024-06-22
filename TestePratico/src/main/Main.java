package main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import model.Funcionario;

public class Main {

	public static void main(String[] args) {
		// 3.1
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		inserirFuncionarios(funcionarios);

		// 3.2
		funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

		// 3.3
		System.out.println("Todos os funcionários:");
		imprimirFuncionarios(funcionarios);

		// 3.4
		for (Funcionario f : funcionarios) {
			f.aumento(0.1);
		}

		// 3.5
		Map<String, List<Funcionario>> funcPorFuncao = funcionarios.stream()
				.collect(Collectors.groupingBy(Funcionario::getFuncao));

		// 3.6
		System.out.println("\n\nFuncionários por função:\n");
		funcPorFuncao.forEach((funcao, lista) -> {
			System.out.println(funcao);
			imprimirFuncionarios(lista);
			System.out.println("\n");
		});

		// 3.8
		System.out.println("\nFuncionários que fazem aniversário em outubro ou dezembro:");
		imprimirFuncionarios(funcionarios.stream().filter(f -> f.getDataNascimento().getMonth() == Month.OCTOBER
				|| f.getDataNascimento().getMonth() == Month.DECEMBER).collect(Collectors.toList()));

		// 3.9
		Funcionario maisVelho = funcionarios.stream().min((f1, f2) -> {
			return f1.getDataNascimento().compareTo(f2.getDataNascimento());
		}).get();

		System.out.println(
				"\n\nFuncionário mais velho:\n" + maisVelho.getNome() + " - " + maisVelho.calcularIdade() + " anos\n");

		// 3.10
		System.out.println("\n\nLista de funcionários por ordem alfabética:");
		imprimirFuncionarios(funcionarios.stream().sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome()))
				.collect(Collectors.toList()));

		// 3.11
		BigDecimal soma = BigDecimal.valueOf(0);

		for (Funcionario f : funcionarios) {
			soma = soma.add(f.getSalario());
		}

		System.out.println("\n\nSoma dos salários dos funcionários:\nR$ " + soma + "\n\n");

		// 3.12
		System.out.println("Quantidade de salários mínimos por funcionário:");
		for (Funcionario f : funcionarios) {
			System.out.println(
					f.getNome() + " - " + f.getSalario().divide(BigDecimal.valueOf(1212.00), 2, RoundingMode.HALF_UP));
		}
	}

	private static void inserirFuncionarios(List<Funcionario> funcionarios) {
		funcionarios
				.add(new Funcionario("Maria", LocalDate.parse("2000-10-18"), BigDecimal.valueOf(2009.44), "Operador"));

		funcionarios
				.add(new Funcionario("João", LocalDate.parse("1990-05-12"), BigDecimal.valueOf(2284.38), "Operador"));

		funcionarios.add(
				new Funcionario("Caio", LocalDate.parse("1961-05-02"), BigDecimal.valueOf(9836.14), "Coordenador"));

		funcionarios
				.add(new Funcionario("Miguel", LocalDate.parse("1988-10-14"), BigDecimal.valueOf(19119.88), "Diretor"));

		funcionarios.add(
				new Funcionario("Alice", LocalDate.parse("1995-01-05"), BigDecimal.valueOf(2234.68), "Recepcionista"));

		funcionarios
				.add(new Funcionario("Heitor", LocalDate.parse("1999-11-19"), BigDecimal.valueOf(1582.72), "Operador"));

		funcionarios
				.add(new Funcionario("Arthur", LocalDate.parse("1993-03-31"), BigDecimal.valueOf(4071.84), "Contador"));

		funcionarios
				.add(new Funcionario("Laura", LocalDate.parse("1994-07-08"), BigDecimal.valueOf(3017.45), "Gerente"));

		funcionarios.add(
				new Funcionario("Heloísa", LocalDate.parse("2003-05-24"), BigDecimal.valueOf(1606.85), "Eletricista"));

		funcionarios
				.add(new Funcionario("Helena", LocalDate.parse("1996-09-02"), BigDecimal.valueOf(2799.93), "Gerente"));
	}

	private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
		int tamLinha = 70; // Tamanho da linha divisória horizontal da tabela

		Locale.setDefault(new Locale("pt", "BR")); // Define '.' como separador de milhar e ',' como separador decimal

		System.out.printf("\n%-15s | %-15s | %-15s | %-15s\n%s\n", "Nome", "Data Nascimento", "Salário", "Função",
				"-".repeat(tamLinha));

		for (Funcionario f : funcionarios) {
			System.out.printf("%-15s | %-15s | R$ %,-12.2f | %s\n%s\n", f.getNome(),
					f.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), f.getSalario(),
					f.getFuncao(), "-".repeat(tamLinha));
		}
	}
}
