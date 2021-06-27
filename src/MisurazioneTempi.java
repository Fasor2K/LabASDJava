import java.util.concurrent.TimeUnit;

public class MisurazioneTempi {

    private static final double E=0.001;

    public static long averageTime(Algortimo alg){
        long start,stop,avg=0;
        String s;
        double Tmin=getResolution()*((1/E)+1);
        int iterations=0;

        if(alg.equals(Algortimo.NAIVE)) {
            do{
                s = GenerazioneStringhe.genera(); //La procedura di generazione della striga è esclusa dal calcolo dei tempi

                start = System.nanoTime();
                PeriodNaive.calculatePeriod(s);
                stop = System.nanoTime();
                avg += (stop - start);
                iterations++;
            }while(avg<Tmin);
        }
        else{
            do{
                s = GenerazioneStringhe.genera(); //La procedura di generazione della striga è esclusa dal calcolo dei tempi

                start = System.nanoTime();
                PeriodSmart.calculatePeriod(s);
                stop = System.nanoTime();
                avg += (stop - start);
                iterations++;
            }while(avg<Tmin);
        }


        avg/=iterations; //Calcolo il tempo medio di esecuzione
        avg= TimeUnit.NANOSECONDS.toMillis(avg); //Converto il risultato da nanosecondi a millisecondi
        return avg;


        //System.out.println("Periodo: "+period+"\nTempo impiegato: "+(stop-start));


    }

    public static double standardDeviation(int length,Algortimo alg){
        long start,stop;
        final int misurazioni=100; //Numero di iterazioni da eseguire
        double[] risultati=new double[misurazioni];
        String s;

        if(alg.equals(Algortimo.NAIVE)){
            for(int i=0;i<misurazioni;i++){
                s = GenerazioneStringhe.genera(length); //Genero stringhe di una lunghezza prefissata, passata come parametro

                start = System.nanoTime();
                PeriodNaive.calculatePeriod(s);
                stop = System.nanoTime();
                risultati[i]=stop-start;
            }
        }
        else {
            for(int i=0;i<misurazioni;i++){
                s = GenerazioneStringhe.genera(length);

                start = System.nanoTime();
                PeriodSmart.calculatePeriod(s);
                stop = System.nanoTime();
                risultati[i]=stop-start;
            }
        }

        return calculateSD(risultati);
    }

    private static double getResolution() {
        double start = System.nanoTime();
        double end;
        do {
            end = System.nanoTime();
        } while (start == end);
        return end - start;
    }

    public static double calculateSD(double[] numArray){ //Calcola la deviazione standard di un array di valori
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.length;

        for(double num : numArray) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }
}
