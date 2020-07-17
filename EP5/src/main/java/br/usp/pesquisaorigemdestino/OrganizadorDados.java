package br.usp.pesquisaorigemdestino;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganizadorDados {
		
	private static final int POSICAO_COORDENADA_X = 88;
	private static final int POSICAO_COORDENADA_Y = 89;
	private static final int POSICAO_ID = 125;

	private Map<String, Local> mapa;

	public OrganizadorDados() {
		this.mapa = new HashMap<>();
	}
	
	public Map<String, Local> organizar(List<String[]> dados) {
		
		for (String[] d : dados) {
			adicionarEntrevistado(Integer.valueOf(d[POSICAO_COORDENADA_X]), 
					Integer.valueOf(d[POSICAO_COORDENADA_Y]), 
					Integer.valueOf(d[POSICAO_ID]));
		}
		return mapa;
	}
	
	public void adicionarEntrevistado(int coordenada_x, int coordenada_y, int frequentador) {
		
		String chave = montarChave(coordenada_x, coordenada_y);
		
		if (mapa.containsKey(chave)) {
			mapa.get(chave).adicionaFrequentador(frequentador);
		} else {
			mapa.put(chave, new Local(coordenada_x, coordenada_y, frequentador));
		}
	}
	
	private String montarChave(int coordenada_x, int coordenada_y) {
		String chave = String.format("%s/%s", coordenada_x, coordenada_y);
		return  chave; 
	}

	public Map<String, Local> getMapa() {
		return mapa;
	}

	public void setMapa(Map<String, Local> mapa) {
		this.mapa = mapa;
	}
	
}
