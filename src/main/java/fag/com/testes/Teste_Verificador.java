package fag.com.testes;

import static org.junit.Assert.*;
import org.junit.Test;

public class Teste_Verificador {
    @Test
    public void testVerificador_ArraysIguais() {
        // Caso de teste: vai verificar se o método retorna true quando os arrays de números são iguais.
        int[] array1 = {1, 2, 3, 4, 5, 6};
        int[] array2 = {1, 2, 3, 4, 5, 6};
        boolean resultado = Novo_Manipulacao_MegaSena.Verificador(array1, array2);
        assertTrue(resultado);
        System.out.println(resultado);
    }

    @Test
    public void testVerificador_ArraysDiferentes() {
        // Caso de teste: vai verificar se o método retorna false quando os arrays de números são diferentes.
        int[] array3 = {1, 2, 3, 4, 5, 6};
        int[] array4 = {6, 5, 4, 3, 2, 1};
        boolean resultado = Novo_Manipulacao_MegaSena.Verificador(array3, array4);
        assertFalse(resultado);
        System.out.println(resultado);
    }

    @Test
    public void testVerificador_ArraysDiferentesTamanho() {
        // Caso de teste: vai verificar se o método lida corretamente com arrays de diferentes tamanhos.
        int[] array1 = {1, 2, 3, 4, 5, 6};
        int[] array2 = {1, 2, 3, 4, 5};
        boolean resultado = Novo_Manipulacao_MegaSena.Verificador(array1, array2);
        assertFalse(resultado);
        System.out.println(resultado);
    }
}
