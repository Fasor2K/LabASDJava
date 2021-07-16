public class PeriodSmart {
    /**
     * Il vettore dei bordi r viene istanziato a priori, con la lunghezza massima possibile.
     * Questo fa in modo che il vettore non venga istanziato ad ogni esecuzione dell'algoritmo, evitando
     * così che venga attivato il garbage collector, ciò creerebbe dei picchi nelle misurazioni
     * dei tempi
     */
    private static int[] r= new int[500001];
        public static int calculatePeriod(String s){
            int l =s.length(),w,temp;
            r[0]=0; r[1]=0;
            for(int i=2; i<=l; i++){
                w= r[i-1];
                while (w>0 && s.charAt(i-1) != s.charAt(w)){
                    w= r[w];
                }
                if(s.charAt(i-1) == s.charAt(w))
                    r[i]=w+1;
                else
                    r[i]=0;
            }
            temp=r[l];
            return l - temp;
        }
}
