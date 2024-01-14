package fag.com.testes;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Teste_AtualizarFrequencia {
	private Map<Integer, Integer> frequenciaNumeros;

    @Before
    public void setUp() {
        frequenciaNumeros = new HashMap<>();
    }

    @Test
    public void testAtualizarFrequencia_NumeroExistente() {
        // Caso de teste: vai verificar se a frequência de um número existente é atualizada corretamente.
        Novo_Manipulacao_MegaSena.atualizarFrequencia(frequenciaNumeros, 5);
        assertEquals(Integer.valueOf(1), frequenciaNumeros.get(5));
    }


    @Test
    public void testAtualizarFrequencia_NumeroNaoExistente() {
        // Caso de teste: vai verificar se a frequência de um número não existente é adicionada corretamente.
        Novo_Manipulacao_MegaSena.atualizarFrequencia(frequenciaNumeros, 10);
        assertEquals(Integer.valueOf(1), frequenciaNumeros.get(10));
    }

    @Test
    public void testAtualizarFrequencia_NumeroExistenteIncremento() {
        // Caso de teste: vai verificar se a frequência de um número existente é incrementada corretamente.
        frequenciaNumeros.put(7, 2); // Número 7 já possui frequência 2
        Novo_Manipulacao_MegaSena.atualizarFrequencia(frequenciaNumeros, 7);
        assertEquals(Integer.valueOf(3), frequenciaNumeros.get(7)); // A frequência deve ser incrementada para 3
    }
}
