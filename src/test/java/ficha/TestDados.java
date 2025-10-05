package ficha;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class TestDados {

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
