package ficha;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestDados {
    public static final Random dadoOriginal = Dados.dado;
    public static final Random mockedDado = mock(Random.class);

    @BeforeAll
    public static void incializa() {
        // substitui o Random original por um mock
        Dados.dado = mockedDado;
    }

    @AfterAll
    public static void finaliza() {
        // restaura o Random original
        Dados.dado = dadoOriginal;
    }

    @Test
    void testRolarDadosComQuantidadeDeDadosNegativa() {
        final int QUANTIDADE = -1;
        final int LADO = 6;
        assertThrows(
            IllegalArgumentException.class,
            () -> Dados.rolarDados(QUANTIDADE, LADO)
        );
    }

    @Test
    void testRolarDadosComLadoNegativo() {
        final int QUANTIDADE = 1;
        final int LADO = -1;
        assertThrows(
            IllegalArgumentException.class,
            () -> Dados.rolarDados(QUANTIDADE, LADO)
            );
        }

    @Test
    void testRolarDadosComQuantidadeDeDadosIgualZero() {
        final int QUANTIDADE = 0;
        final int LADO = 6;
        assertThrows(
            IllegalArgumentException.class,
            () -> Dados.rolarDados(QUANTIDADE, LADO)
        );
    }

    @Test
    void testRolarDadosComValorLadoIgualZero() {
        final int QUANTIDADE = 1;
        final int LADO = 0;
        assertThrows(
            IllegalArgumentException.class,
            () -> Dados.rolarDados(QUANTIDADE, LADO)
        );
    }

    @Test
    void testRolarDadosCom1DadoD6() {
        final int QUANTIDADE = 1;
        final int LADO = 6;

        // nextInt(lado) retorna 5
        when(mockedDado.nextInt(LADO)).thenReturn(5);
        int resultado = Dados.rolarDados(QUANTIDADE, LADO);

        // cálculo: (5+1) = 6
        assertEquals(6, resultado);
    }

    @Test
    void testRolarDadosCom3DadosD6() {
        final int QUANTIDADE = 3;
        final int LADO = 6;

        // nextInt(lado) retorna 0, depois 3, depois 5
        when(mockedDado.nextInt(LADO)).thenReturn(0, 3, 5);
        int resultado = Dados.rolarDados(QUANTIDADE, LADO);

        // cálculo: (0+1) + (3+1) + (5+1) = 1 + 4 + 6 = 11
        assertEquals(11, resultado);
    }


    @Test
    void testRolar4DadosD6() {
        final int LADO = 6;

        // nextInt(lado) retorna 1, depois 2, depois 3, depois 4
        when(mockedDado.nextInt(LADO)).thenReturn(1, 2, 3, 4);
        int[] resultado = Dados.rolar4DadosD6();

        // cálculo: (1+1), (2+1), (3+1), (4+1) = 2, 3, 4, 5
        assertArrayEquals(new int[] {2, 3, 4, 5}, resultado);
    }


    @Test
    void testDescartaMenorComMenorValorNaPrimeiraPosicao() {
        // menor valor na primeira posição
        int[] dados = new int[] {1, 4, 2, 6};
        // Esperado: Remover o primeiro valor (1)
        List<Integer> esperado = List.of(4, 2, 6);
        // Descarte do 1
        List<Integer> resultado = Dados.descartaMenor(dados);

        assertEquals(esperado, resultado);
    }


    @Test
    void testDescartaMenorComMenorValorNaSegundaPosicao() {
        // menor valor na primeira posição
        int[] dados = new int[] {4, 1, 2, 6};
        // Esperado: Remover o segundo valor (1)
        List<Integer> esperado = List.of(4, 2, 6);
        // Descarte do 1
        List<Integer> resultado = Dados.descartaMenor(dados);

        assertEquals(esperado, resultado);
    }


    @Test
    void testDescartaMenorComMenorValorNaUltimaPosicao() {
        // menor valor na última posição
        int[] dados = new int[] {4, 2, 6, 1};
        // Esperado: Remover o último valor (1)
        List<Integer> esperado = List.of(4, 2, 6);
        // Descarte do 1
        List<Integer> resultado = Dados.descartaMenor(dados);

        assertEquals(esperado, resultado);
    }

    @Test
    public void testConfereNumeroNaLista() {
        List<Integer> lista = List.of(1, 2, 3, 4, 5);
        assertTrue(Dados.confere(lista, 3));
    }

    @Test
    public void testConfereNumeroNaoEstaNaLista() {
        List<Integer> lista = List.of(1, 2, 3, 4, 5);
        assertFalse(Dados.confere(lista, 0));
    }
}
