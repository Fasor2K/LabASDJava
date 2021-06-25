import java.util.concurrent.TimeUnit;

public class PeriodNaive {
    public static int calculatePeriod(String s){
        int n=s.length();

        for(int i=1;i<=n;i++){
            String s1=s.substring(0,n-i);
            String s2=s.substring(i,n);
            if(s1.equals(s2)){
                return i;
            }
        }
        return n;
    }

    public static long averageTime(int iterations){
        long start,stop,avg=0;
        String s;

        for(int i=1;i<=iterations;i++){ //Ripeto il calcolo del periodo iterations volte con stringhe diverse e aggiungo il tempo impiegato per calcolare il periodo alla variabile avg
            s=GenerazioneStringhe.genera(); //La procedura di generazione della striga è esclusa dal calcolo dei tempi

            start=System.nanoTime();
            PeriodNaive.calculatePeriod(s);
            stop=System.nanoTime();
            avg+=(stop-start);
        }


        avg/=iterations; //Calcolo il tempo medio di esecuzione
        avg= TimeUnit.NANOSECONDS.toMillis(avg); //Converto il risultato da nanosecondi a millisecondi
        return avg;


        //System.out.println("Periodo: "+period+"\nTempo impiegato: "+(stop-start));


    }
}
