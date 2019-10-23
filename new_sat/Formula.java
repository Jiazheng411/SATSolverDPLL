package new_sat;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Formula {
    public HashMap<Integer, LinkedList<Clause>> ClauseMap = new HashMap<Integer, LinkedList<Clause>>();
    public int size;

    public int getSize(){
        return this.size;
    }
    public Formula(){

    }

    public Formula(int[][] clauseList) {
        for (int[] clause : clauseList) {
            Clause newClause = new Clause(clause);
            this.add(newClause);
        }

    }

    public Formula(ArrayList<int[]> clauseList) {
        for (int[] clause : clauseList) {
            Clause newClause = new Clause(clause);
            this.add(newClause);
        }
    }


    public void add(Clause clause) {
        int clauseSize = clause.size;
        if (clause == null) return;
        if (ClauseMap.containsKey(clauseSize)) {
            ClauseMap.get(clauseSize).add(clause);
            this.size += 1;
        } else {
            LinkedList<Clause> emptyList = new LinkedList<Clause>();
            emptyList.add(clause);
            ClauseMap.put(clauseSize, emptyList);
            this.size += 1;
        }

    }


    public Formula reduceFormula(int literal) {
        Formula newFormula = new Formula();
        for (LinkedList<Clause> list : this.ClauseMap.values()) {
            for (Clause clause : list) {
                Clause reducedClause = clause.reduceLiteral(literal);
                if(reducedClause != null) {
                    newFormula.add(reducedClause);
                }
            }
        }
        return newFormula;
    }


    public int getLiteral(){
        if(this.ClauseMap.containsKey(1)){
             LinkedList<Clause> clauseLengthOne = this.ClauseMap.get(1);
             Clause clause = clauseLengthOne.getFirst();
             int literal = clause.literals.keySet().iterator().next();
             return literal;
        }else if(this.ClauseMap.containsKey(0)){
            LinkedList<Clause> randomClauseList = this.ClauseMap.values().iterator().next();
            int literal = randomClauseList.getFirst().literals.keySet().iterator().next();
            return literal;
        }
        else {
            LinkedList<Clause> randomClauseList = this.ClauseMap.values().iterator().next();
            int literal = randomClauseList.getFirst().literals.keySet().iterator().next();
            return literal;
        }
    }


    public String toString(){
        String result = "problem is: ";
        for (int clauseLen: this.ClauseMap.keySet()){
            result += "\n" + "clauses with length " + clauseLen;
            LinkedList<Clause> clauseList = this.ClauseMap.get(clauseLen);
            for(Clause clause: clauseList){
                result += "\n" + clause.toString();
            }
        }
        return result;
    }
}
