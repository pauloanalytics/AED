package br.usp.pesquisaorigemdestino;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVWriter;

public class CSVService {
	
	public List<String[]> ler(String pathCsv) throws IOException {
		List<String[]> dados = new LinkedList<String[]>();
		BufferedReader reader = new BufferedReader(new FileReader(pathCsv));
		String linha;
		while(((linha = reader.readLine()) != null)) {
			dados.add(linha.split(","));
		}
		dados.remove(0);
		reader.close();
		return dados;
	}
	
	
	public void escrever(String pathCsv, Map<String, Local> mapa) throws IOException {
		File file = new File(pathCsv);
		Map<Integer, Integer> map = obterLocaisPorQuantidadePessoa(mapa);
		CSVWriter writer = new CSVWriter(
				new OutputStreamWriter (new FileOutputStream(file)),  ';', '"', '"', "\n");
		List<String[]> dadosSaida = new LinkedList<String[]>();
		dadosSaida.add(new String[]{"Quantidade Frequentadores", "Quantidade Locais"});
		
		for (Integer key : map.keySet()) {
			dadosSaida.add(new String[]{String.valueOf(key), String.valueOf(map.get(key))});
		}
		
		writer.writeAll(dadosSaida);
		writer.close();
			
	}
	
	private Map<Integer, Integer> obterLocaisPorQuantidadePessoa(Map<String, Local> mapa) {

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		int freq = 0;
		int qnt = 0;
		
		for (String key : mapa.keySet()) {
			freq = mapa.get(key).getFrequentadores().size();
			if(map.containsKey(freq)) {
				qnt = map.get(freq);
				map.put(freq, (qnt+1));
			} else {
				map.put(freq, 1);
			}
		}
		return map;
	}
	
	
}
