package new_sat;


import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TestSolver {
    public static void main(String[] args) {
        ArrayList<int[]> fm1 = loadCNF("C:\\Users\\User\\Desktop\\Java\\Project-2D\\Project-2D-starting\\sampleCNF\\largeSat.cnf");

        Formula newfm = new Formula(fm1);
        System.out.println("SAT solver starts!!!");
        long started = System.nanoTime();

        //System.out.println(newfm.size);
        //System.out.println(newfm.toString());
        Environment ans = SATSolver.solve(newfm);
        long time = System.nanoTime();
        long timeTaken= time - started;
        System.out.println("Time:" + timeTaken/1000000.0 + "ms");
        if(ans == null){System.out.println("not satisfied");}
        else{System.out.println(ans.toString());}
    }


    private static ArrayList<int[]> loadCNF(String filePath)
    {
        File file = new File( filePath );

        //System.out.println(file.exists());

        ArrayList<String> strs = new ArrayList<>();
        String str = null;
        BufferedReader br = null;

        ArrayList<int[]> content = new ArrayList<>();

        try
        {
            br = new BufferedReader( new FileReader(file) );

            while ( (str = br.readLine())!= null )
            {
                if ( !str.equals("")) strs.add(str);
            }
        }
        catch ( FileNotFoundException e )
        {
            e.printStackTrace();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }


        for ( String s : strs )
        {
            //System.out.println(s.charAt(0));

            if ( s.charAt(0)!='c' && s.charAt(0)!='p'  )
            {
                //System.out.println("here");
                String[] strSplit = s.split(" ");

                int[] a = new int[strSplit.length-1];

                for ( int i=0; i<a.length; i++ )
                {
                    a[i] = Integer.valueOf(strSplit[i]);
                    //System.out.print(a[i]+"  ");
                }
                //System.out.println("  ");

                content.add(a);
            }
        }
        return content;
    }
}

