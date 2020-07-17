package br.usp.pesquisaorigemdestino;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PesquisaOrigemDestinoApplication {
	
	private static final String PATH_CSV = "C:\\Users\\paulo\\Desktop\\CURSOS\\USP\\AED2\\OD_2017.csv";
	private static final String PATH_CSV_R = "C:\\Users\\paulo\\Desktop\\CURSOS\\USP\\AED2\\RESULTADO.csv";

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PesquisaOrigemDestinoApplication.class, args);
		run();
	}
	
	private static void run() throws IOException {
		long inicial = System.currentTimeMillis();
		CSVService csvService = new CSVService();
		List<String[]> dados = csvService.ler(PATH_CSV);
		OrganizadorDados org = new OrganizadorDados();
		Map<String, Local> mapa = org.organizar(dados);
		csvService.escrever(PATH_CSV_R, mapa);
		long finalT = System.currentTimeMillis();
		System.out.println(finalT-inicial);
		System.exit(0);

	}

}
