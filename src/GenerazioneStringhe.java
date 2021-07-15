import java.util.Random;

public class GenerazioneStringhe {

    /*
    public static String genera(){
        Random random = new Random();

        int leftLimit = 97; // lettera 'a'
        int rightLimit = 99; // lettera 'c'
        int targetStringLength =random.nextInt(499001)+999; //Lunghezza della stringa da generare, numero randomico da 1000 a 500000;
        //System.out.println(targetStringLength+"\n\n\n");
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
     */

    /**
     *  Grazie all'overloading se il metodo genera viene chiamato senza parametri la lunghezza della stringa da generare viene determinata
     *  in maniera randomica, se invece la lunghezza viene passata come parametro allora sarà generata una stringa di tale lunghezza
     */

    /**
     * Metodo che genera stringhe casuali con alfabeto ternario {a,b,c} di lunghezza compresa tra 1000 e 500000.
     * Itera length volte dove length è la lunghezza della stringa da generare, ad ogni iterazione genera un nuovo
     * carattere della stringa in maniera casuale
     * @return La stringa generata
     *
    public static String genera(){
        Random random=new Random();
        String ret=""; //Stringa da generare
        char rand;
        //nextInt(x) genera un numero compreso tra 0 e x-1 (inclusi)
        int length=random.nextInt(499001)+1000; //Lunghezza della stringa da generare, numero randomico da 1000 a 500000;

        for(int i=0;i<length;i++){ //Itero length volte e ad ogni iterazione generero randomicamente un singolo carattere della stringa
            //Genero un numero randomico tra 0 e 2 (inclusi), sommando a tale risultato 97 ottengo 97, 98 o 99, che sono i codici ASCII delle lettere a, b, c. Utizlizzo quindi un alfabeto ternarwio composto da queste 3 lettere
            rand= (char)(random.nextInt(3)+97);
            ret+=rand;
        }

        return ret;
    }*/

    /**
     * Overloading del metodo genera(), funziona alla stessa maniera ma la lunghezza della stringa da generare
     * viene passata come parametro
     */
    public static String genera(int length){
        Random random=new Random();
        String ret=""; //Stringa da generare
        char rand;

        for(int i=0;i<length;i++){ //Itero length volte e ad ogni iterazione generero randomicamente un singolo carattere della stringa
            //Genero un numero randomico tra 0 e 2 (inclusi), sommando a tale risultato 97 ottengo 97, 98 o 99, che sono i codici ASCII delle lettere a, b, c. Utizlizzo quindi un alfabeto ternarwio composto da queste 3 lettere
            //nextInt(x) genera un numero compreso tra 0 e x-1 (inclusi)
            rand= (char)(random.nextInt(3)+97);
            ret+=rand;
        }

        return ret;
    }
}
