public class Main {
    public static void main(String[] args){
        //long t=MisurazioneTempi.averageTime(Algortimo.NAIVE);

        //System.out.println("Deviazione standard: "+MisurazioneTempi.standardDeviation(100,Algortimo.NAIVE));

        MisurazioneTempi.timesOnFile(Algortimo.NAIVE,500000);
    }
}
