import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MisurazioneTempi {

    private static final double E=0.001;

    /**
     * Calcola il tempo medio di esecuzione di un determinato algoritmo su stringhe generate casualmente di lunghezza casuale
     * compresa tra 1000 e 500000
     * @param alg Algoritmo che si vuole utilizzare
     * @return Tempo medio di esecuzione in millisecondi
     */
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
    }

    /**
     * Scrive su file i tempi di esecuzione di un determinato algoritmo con la relativa lunghezza della stringa considerata
     * @param alg tipo di algoritmo da utilizzare
     * @param maxLength proverò stringhe lunghe da 1 a maxLength (passando una volta tutte le lunghezze)
     */
    public static void timesOnFile(Algortimo alg,int maxLength){
        long start,stop,avg=0;
        String s,filePath="";

        if(alg.equals(Algortimo.NAIVE)) {
            for(int i=1;i<=maxLength;i++){
                s = GenerazioneStringhe.genera(i);

                start = System.nanoTime();
                PeriodNaive.calculatePeriod(s);
                stop = System.nanoTime();
                //avg=TimeUnit.NANOSECONDS.toMillis(stop-start);
                avg=stop-start;
                if(i==1) { //Scrivo su file la lunghezza della stringa affiancata al tempo di esecuzione dell'algoritmo, se sono alla prima iterazione creo il file
                    filePath="TempiNaive.txt"; //Questo è il percorso ipotetico del file, se però il file esiste già il nuovo percorso verrà ritornato dalla funzione writeFile
                    filePath=writeFile(filePath, s.length() + " " + avg+"\n", true);
                }
                else{
                    filePath=writeFile(filePath, s.length() + " " + avg+"\n", false);
                }
            }
        }
        else{
            for(int i=1;i<=maxLength;i++){
                s = GenerazioneStringhe.genera(i);

                start = System.nanoTime();
                PeriodSmart.calculatePeriod(s);
                stop = System.nanoTime();
                avg =stop - start;
                if(i==1) {
                    filePath="TempiSmart.txt";
                    filePath=writeFile(filePath, s.length() + " " + avg+"\n", true);
                }
                else{
                    filePath=writeFile(filePath, s.length() + " " + avg+"\n", false);
                }
            }
        }
    }

    /**
     * Calcola la deviazione standard dei risultati di un determinato algoritmo, testato con un numero prefissato di stringhe
     * di lunghezza preifssata.
     * I risultati delle varie esecuzioni dell'algoritmo vengono salvati in un vettore, tale vettore viene poi passato alla
     * funzione calcolaSD che calcola la deviazione standard di tutti i valori in esso contenuti
     * @param length Lunghezza prefissata delle stringhe
     * @param alg Tipo di algoritmo da utilizzare
     * @return Deviazione standard
     */
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

    /**
     * Calcola la deviazione standard di un array di valori
     */
    public static double calculateSD(double[] numArray){
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

    /**
     * Scrive su file una stringa passata come parametro, è in grado di creare il file se esplicitamente richiesto.
     * Se il file è gia esistente, modifica il nome del file finchè non ne trova uno non ancora utilizzato
     * @param path Percorso del file desiderato
     * @param text Testo da scrivere nel file
     * @param create Booleano che mi consente di decidere se il file va creato oppure se devo scrivere in coda ad un file già esistente
     * @return Eventuale nuovo percorso del file se quello desiderato (parametro path) è già in uso
     */
    private static String writeFile(String path,String text,boolean create){
        File f=new File(path);
        FileWriter fw;
        int k=1,l;
        final String oldpath=path; //Mi salvo il percorso originario del file

        if(create) { //Se create==true allora voglio creare il file
            while (f.exists()) { //Se il file esiste già cambia il nome del file aggiungendoci un'appendice "_k" finchè non trova un nome libero
                path=oldpath;
                if (path.contains(".")) { //Se il file ha un'estensione divido l'estensione dal resto della stringa
                    String[] arr = path.split("\\.");
                    l = arr.length;
                    arr[l - 2] += "_" + k + "."; //Aggiungo alla parte senza estensione l'appendice _k
                    path = arr[l - 2] + arr[l - 1]; //Aggiungo nuovamente l'estensione al nuovo percorso del file
                } else { //Se il file non ha estensione aggiungo semplicemente l'appendice alla fine del percorso
                    path += "_" + k;
                }
                k++;
                f = new File(path);
                /**
                 * Istanzio nuovamente l'oggetto f di tipo File con il nuovo percorso, alla successiva iterazione controllerò
                 * Se anche il nuovo percorso è già occupato, in tal caso, riprendo il percorso originario del file e ripeto la
                 * procedura con k incrementato finchè non trovo una posizione libera
                 */
            }

            try {
                f.createNewFile();
                fw = new FileWriter(f);
                fw.write(text);
                fw.close();
            } catch (IOException e) {
                System.out.println("ERRORE NELLA CREAZIONE DEL FILE");
                e.printStackTrace();
            }
        }
        else{
            f=new File(path);
            try {
                fw=new FileWriter(path,true);
                fw.write(text);
                fw.close();
            } catch (IOException e) {
                System.out.println("ERRORE NELLA SCRITTURA DEL FILE");
                e.printStackTrace();
            }
        }
        return path;
    }
}