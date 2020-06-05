package br.usp.pesquisaorigemdestino;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PesquisaOrigemDestinoApplication {
	
	private static final String PATH_CSV_EP1 = "/home/paulo/Área de Trabalho/OD_20171.csv";
	private static final String PATH_CSV_RESULTADO_EP1 = "/home/paulo/Área de Trabalho/Resultado_EP1.csv";

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PesquisaOrigemDestinoApplication.class, args);
		run();
	}
	
	private static void run() throws IOException {
		
		//EP1
		long inicial = System.currentTimeMillis();
		CSVService csvService = new CSVService();
		List<String[]> dados = csvService.ler(PATH_CSV_EP1);
		OrganizadorDados org = new OrganizadorDados();
		Map<String, Local> mapa = org.organizar(dados);
		Map<Integer, Integer> m = org.obterDistribuicao(mapa);
		List<String[]> infoAdicional = new LinkedList<String[]>();
		infoAdicional.add(new String[]{"Quantidade Frequentadores", "Quantidade Locais"});
		csvService.escrever(PATH_CSV_RESULTADO_EP1, infoAdicional, m);
		long finalT = System.currentTimeMillis();
		System.out.println(finalT-inicial);
	}

}
