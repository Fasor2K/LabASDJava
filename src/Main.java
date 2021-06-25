public class Main {
    public static void main(String[] args){
        double start,stop;
        String s;
        int period;

        s=GenerazioneStringhe.genera();

        start=System.nanoTime();
        period=PeriodNaive.calculatePeriod(s);
        stop=System.nanoTime();

        System.out.println("Periodo: "+period+"\nTempo impiegato: "+(stop-start));
    }
}
