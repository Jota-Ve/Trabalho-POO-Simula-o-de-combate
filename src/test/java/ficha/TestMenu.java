package ficha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMenu {
    private static final PrintStream originalOut = System.out;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public static void inicializa(){
        // redireciona a saída padrão para um ByteArrayOutputStream
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    public void limpaBuffer(){
        // limpa o conteúdo do buffer antes de cada teste
        outContent.reset();
    }

    @AfterAll
    public static void finaliza(){
        // restaura a saída padrão
        System.setOut(originalOut);
    }


    @Test
    void testImprimirMenuPrincipal() {
        // define a saída esperada
        String SAIDA_ESPERADA = """
                O que deseja fazer?
                1 - Imprimir Racas
                2 - Imprimir Classes
                3 - Escolher Raca
                4 - Escolher Classe
                5 - Rolar Dados
                6 - Fechar
                """;

        // chama o método que imprime no console
        Menu.imprimirMenuPrincipal();

        // Formata a saída para evitar falhas por causa de diferenças de quebra de linha
        String esperado = SAIDA_ESPERADA.replace("\r\n", "\n");
        String atual = outContent.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");

        assertEquals(esperado, atual);
    }


    @Test
    void testImprimirMenuEquipamento() {
        // define a saída esperada
        String SAIDA_ESPERADA = """

            O que deseja fazer?
            1 - Imprimir Armas
            2 - Imprimir Armaduras
            3 - Escolher Arma
            4 - Escolher Armadura
            5 - Fechar
            """;

        // chama o método que imprime no console
        Menu.imprimirMenuEquipamentos();

        // Formata a saída para evitar falhas por causa de diferenças de quebra de linha
        String esperado = SAIDA_ESPERADA.replace("\r\n", "\n");
        String atual = outContent.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");

        assertEquals(esperado, atual);
    }


    @Test
    void quandoEntradaNaoNumerica_deveImprimirMensagemDeErro() {
        // cria mock do Scanner
        Scanner mockScanner = mock(Scanner.class);

        // simula comportamento: nextInt() lança InputMismatchException
        when(mockScanner.nextInt()).thenThrow(new InputMismatchException());

        // chama o método que usa o Scanner; adapte se sua API for diferente
        // Exemplo: Menu.exec(Scanner teclado) onde exec trata o try/catch mostrado
        Menu.exec(mockScanner);

        String atual = outContent.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");
        String esperado = "Opcao invalida. Por favor, digite um numero.\n";
        assertEquals(esperado, atual);
    }

}
