package fag.com.testes;

import static org.junit.Assert.*;
import org.junit.Test;

public class Teste_SorteioAleatorio {
	 @Test
	    public void testGerarSorteioAleatorio_IntervaloCorreto() {
	        // Caso de teste: vai verificar se o método gera números dentro do intervalo de 1 a 60.
	        int[] sorteio = Novo_Manipulacao_MegaSena.gerarSorteioAleatorio();
	        for (int numero : sorteio) {
	            assertTrue(numero >= 1 && numero <= 60);
	        }
	    }

	    @Test
	    public void testGerarSorteioAleatorio_NumerosDiferentes() {
	        // Caso de teste: vai verificar se o método gera números diferentes em cada chamada.
	        int[] sorteio1 = Novo_Manipulacao_MegaSena.gerarSorteioAleatorio();
	        int[] sorteio2 = Novo_Manipulacao_MegaSena.gerarSorteioAleatorio();

	        boolean saoIguais = true;
	        for (int i = 0; i < 6; i++) {
	            if (sorteio1[i] != sorteio2[i]) {
	                saoIguais = false;
	                break;
	            }
	        }

	        assertFalse(saoIguais);
	    }
}
