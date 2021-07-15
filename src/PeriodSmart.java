public class PeriodSmart {
        private static int[] r= new int[500001];
        public static int calculatePeriod(String s){
            int n =s.length();
            int z,tmp;
            r[0]=0; r[1]=0;
            for(int i=2; i<=n; i++){
                z= r[i-1];
                while (z>0 && s.charAt(i-1) != s.charAt(z)){
                    z= r[z];
                }
                if(s.charAt(i-1) == s.charAt(z))
                    r[i]=z+1;
                else
                    r[i]=0;
            }
            tmp=r[n];
            return n - tmp;
        }
}
