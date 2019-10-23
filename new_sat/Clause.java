package new_sat;

import java.util.HashMap;

public class Clause {
    public HashMap<Integer, Integer> literals = new HashMap<Integer,Integer>();
    public int size;

    public Clause(){
    }

    public Clause(int[] literals){
        this();
        for(int literal:literals){
            this.literals.put(literal, 0);
        }
        this.size = literals.length;
    }

    public Clause(HashMap<Integer, Integer> literals){
        this();
        this.literals = literals;
        this.size = literals.size();
    }

    public int addLiteral(int literal){
        if(this.literals.containsKey(literal)) return 1;
        else if(this.literals.containsKey(-literal)) return 0;
        else {
            this.literals.put(literal, 0);
            this.size += 1;
        }
        return 1;
    }

    public Clause reduceLiteral(int literal){
        if (this.literals == null || this.size == 0) return this;
        else{
            if (this.literals.containsKey(literal)) return null;
            else if (this.literals.containsKey(-literal)){
                HashMap<Integer,Integer> newLiterals = (HashMap<Integer, Integer>) this.literals.clone();
                newLiterals.remove(-literal);
                Clause newClause = new Clause(newLiterals);
                return newClause;
            }else return this;
         }
    }

    @Override
    public String toString(){
        return "the clause contains literals: " + this.literals.keySet().toString() + "\n" + "this claus size is " + this.size;
    }
}
