import java.util.function.Function;
import java.util.function.Predicate;

public class Lista<E> {

	private Celula<E> primeiro;
	private Celula<E> ultimo;
	private int tamanho;
	
	public Lista() {
		
		Celula<E> sentinela = new Celula<>();
		
		this.primeiro = this.ultimo = sentinela;
		this.tamanho = 0;
	}
	
	public boolean vazia() {
		
		return (this.primeiro == this.ultimo);
	}
	
	public void inserir(E novo, int posicao) {
		
		Celula<E> anterior, novaCelula, proximaCelula;
		
		if ((posicao < 0) || (posicao > this.tamanho))
			throw new IndexOutOfBoundsException("Não foi possível inserir o item na lista: "
					+ "a posição informada é inválida!");
		
		anterior = this.primeiro;
		for (int i = 0; i < posicao; i++)
			anterior = anterior.getProximo();
				
		novaCelula = new Celula<>(novo);
			
		proximaCelula = anterior.getProximo();
			
		anterior.setProximo(novaCelula);
		novaCelula.setProximo(proximaCelula);
			
		if (posicao == this.tamanho)  // a inserção ocorreu na última posição da lista
			this.ultimo = novaCelula;
			
		this.tamanho++;		
	}
	
	public E remover(int posicao) {
		
		Celula<E> anterior, celulaRemovida, proximaCelula;
		
		if (vazia())
			throw new IllegalStateException("Não foi possível remover o item da lista: "
					+ "a lista está vazia!");
		
		if ((posicao < 0) || (posicao >= this.tamanho ))
			throw new IndexOutOfBoundsException("Não foi possível remover o item da lista: "
					+ "a posição informada é inválida!");
			
		anterior = this.primeiro;
		for (int i = 0; i < posicao; i++)
			anterior = anterior.getProximo();
				
		celulaRemovida = anterior.getProximo();
				
		proximaCelula = celulaRemovida.getProximo();
				
		anterior.setProximo(proximaCelula);
		celulaRemovida.setProximo(null);
				
		if (celulaRemovida == this.ultimo)
			this.ultimo = anterior;
				
		this.tamanho--;
				
		return (celulaRemovida.getItem());	
	}

	public void inserirNoFim(E item) {
    Celula<E> nova = new Celula<>(item);
    ultimo.setProximo(nova);
    ultimo = nova;
	}



	public int tamanho() {
    int count = 0;
    Celula<E> atual = primeiro.getProximo(); // pula a sentinela
    while (atual != null) {
        count++;
        atual = atual.getProximo();
    }
    return count;
	}

	public E getUltimo(){
		return ultimo.getItem();
	}

	public double calcularValorMedio(Function<E, Double> extrator, int quantidade) {
    Celula<E> temp = primeiro.getProximo();
    double soma = 0.0;
    int count = 0;

    while (temp != null && count < quantidade) {
        E elemento = temp.getItem();
        soma += extrator.apply(elemento);
        temp = temp.getProximo();
        count++;
    }

    return count > 0 ? soma / count : 0.0;
	}

	public double valorMedioInterativo(Function<E, Double> extrator, int quantidade){
		Celula<E> temp = primeiro.getProximo();
		double soma=0;
		for(int i=0;i <quantidade; i++){
		E elemento = temp.getItem();
		soma += extrator.apply(elemento);
		temp = temp.getProximo();

		}
		double media = soma/quantidade;
		return media;
	}
	Lista<E> filtrar(Predicate<E> condicional, int quantidade){
		Celula<E> temp = primeiro.getProximo();
		Lista<E> aux = new Lista<>();
		int encontrados = 0;
		while (encontrados < quantidade && temp != null) {
			if (condicional.test(temp.getItem())) {
				aux.inserirNoFim(temp.getItem());
				encontrados++;
			}
			temp = temp.getProximo();
		}
		return aux;
	}

	Lista<E> filtroPorProduto(Predicate<E> condicional){
		Lista<E> aux = new Lista<>();
		Celula<E> temp = primeiro.getProximo();
		while (temp != null) {
			if (condicional.test(temp.getItem())) {
				aux.inserirNoFim(temp.getItem());
			}
			temp = temp.getProximo();
		}
		return aux;
	}



	public static void main(String[] args) {
		Lista<Integer> l = new Lista<>();
	}
}
