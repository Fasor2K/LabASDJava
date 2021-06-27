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
}
