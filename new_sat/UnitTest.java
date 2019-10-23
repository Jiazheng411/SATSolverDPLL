package new_sat;

import org.junit.Test;

public class UnitTest {

    @Test
    public void testClause(){
        int[] ar1 = {1,2,3};
        Clause cl1 = new Clause(ar1);
        System.out.println(cl1.toString());
    }

    @Test public void testClauseReduce(){
        int[] ar1 = {1,2,3,4,5,6,-7};
        Clause cl1 = new Clause(ar1);
        System.out.println(cl1.toString());
        Clause newclause = cl1.reduceLiteral(7);
        System.out.println(newclause.toString());
        System.out.println(cl1.toString());

        Clause newclause2 = cl1.reduceLiteral(1);
        if(newclause2 == null){System.out.println("clause in none");}
        System.out.println(cl1.toString());
    }

    @Test public void testEnv(){
        Environment env = new Environment();
        env.addFalseLiteral(1);
        System.out.println((env.toString()));
        env.addTrueLiteral(2);
        System.out.println((env.toString()));
        env.removeLiteral(2);
        System.out.println((env.toString()));
        env.addTrueLiteral(1);
        System.out.println((env.toString()));
    }


    @Test public void testFormula(){
        int[][] ar1 = {{1,2,3},{-1},{4,5,-2,-1},{3,4},{1,5,6,-7},{3,4,1,5,6,2},{1}};
        Formula fm1 = new Formula(ar1);
        System.out.println(fm1.size);
        System.out.println(fm1.toString());
        int literal = (fm1.getLiteral());
        Formula fm2 = fm1.reduceFormula(literal);
        System.out.println(fm2.toString());
    }

    @Test public void testbaseSATSolver(){
        Formula fm1 = new Formula();
        Environment ans = SATSolver.solve(fm1);
        System.out.println(ans.toString());

        int[][] ar1 = {{},{1},{2,3},{3,4},{6,5}};
        Formula fm2 = new Formula(ar1);
        Environment ans1 = SATSolver.solve(fm2);
        if (ans1 == null){System.out.println("not satisfied");}
        else{System.out.println(ans1.toString());}


        int[][] ar2 = {{1},{1,3},{2,3},{3,4},{6,5}};
        Formula fm3 = new Formula(ar2);
        Environment ans2 = SATSolver.solve(fm3);
        if (ans2 == null){System.out.println("not satisfied");}
        else{System.out.println(ans2.toString());}
    }

    @Test public void testSatsolver(){
        int[][] ar1 = {{-1,2,3},{-2,1},{-3,1},{-2,4},{-2,-5},{-4,5,2},{-3,-4},
                {-3,5}};
        Formula fm = new Formula(ar1);
        Environment ans = SATSolver.solve(fm);
        if (ans == null){System.out.println("not satisfied");}
        else{System.out.println(ans.toString());}
    }
}
