package threads;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Cavaleiro[] cavaleiros = {
            new Cavaleiro("Cavaleiro 1"),
            new Cavaleiro("Cavaleiro 2"),
            new Cavaleiro("Cavaleiro 3"),
            new Cavaleiro("Cavaleiro 4")
        };

        View view = new View(); // Instância da classe View para exibir as informações

        int distanciaCorredor = 2000; // 2 km = 2000 metros
        int posicaoTocha = 500;
        int posicaoPedra = 1500;

        // Simulação do percurso
        boolean tochaPega = false;
        boolean pedraPega = false;

        while (true) {
            boolean todosChegaram = true;

            for (Cavaleiro cavaleiro : cavaleiros) {
                cavaleiro.andar();
                view.exibirPosicaoCavaleiro(cavaleiro);

                // Cavaleiro pega a tocha
                if (!tochaPega && cavaleiro.posicao >= posicaoTocha) {
                    cavaleiro.pegarTocha();
                    tochaPega = true;
                    view.exibirTochaPegada(cavaleiro);
                }

                // Cavaleiro pega a pedra
                if (!pedraPega && cavaleiro.posicao >= posicaoPedra) {
                    cavaleiro.pegarPedra();
                    pedraPega = true;
                    view.exibirPedraPegada(cavaleiro);
                }

                // Verifica se todos já chegaram ao final do corredor
                if (cavaleiro.posicao < distanciaCorredor) {
                    todosChegaram = false;
                }
            }

            // Termina a simulação quando todos chegarem ao fim
            if (todosChegaram) {
                break;
            }

            // Espera 50 ms (simula o intervalo de tempo)
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Escolha das portas
        boolean[] portas = {true, false, false, false}; // Uma porta leva à saída
        embaralharArray(portas);

        for (Cavaleiro cavaleiro : cavaleiros) {
            int escolhaPorta = random.nextInt(4);
            boolean escapou = portas[escolhaPorta];
            view.exibirEscolhaPorta(cavaleiro, escolhaPorta, escapou);
        }
    }

    // Método para embaralhar as portas
    public static void embaralharArray(boolean[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            boolean temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
