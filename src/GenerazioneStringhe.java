import java.util.Random;

public class GenerazioneStringhe {
    public static String genera(){
        Random random = new Random();

        int leftLimit = 97; // lettera 'a'
        int rightLimit = 99; // lettera 'c'
        int targetStringLength = random.nextInt(499001)+999; //Lunghezza della stringa da generare, numero randomico da 1000 a 500000;
        System.out.println(targetStringLength+"\n\n\n");
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
