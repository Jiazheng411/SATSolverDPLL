package new_sat;

import java.util.LinkedList;

public class SATSolver {

        /**
         * Solve the problem using a simple version of DPLL with backtracking and
         * unit propagation. The returned environment binds literals of class
         * bool.Variable rather than the special literals used in clausification of
         * class clausal.Literal, so that clients can more readily use it.
         *
         * @return an environment for which the problem evaluates to Bool.TRUE, or
         *         null if no such environment exists.
         */
        public static Environment solve(Formula formula) {
            Environment env = new Environment();
            Environment ans = solve(formula, env);
            return ans;
        }

        public static Environment solve(Formula formula, Environment env) {
            // no clauses in the formula so its satisfied;
            if ((formula.getSize()) == 0) return env;
            // empty clauses in the formula, which means false is one clause, so not satisfied.
            else if (formula.ClauseMap.containsKey(0)) {
                return null;
            } else {
                if (formula.ClauseMap.containsKey(1)) {
                    Clause chosenClause = formula.ClauseMap.get(1).getFirst();
                    int literal = chosenClause.literals.keySet().iterator().next();
                    env.addTrueLiteral(literal);
                    Formula newFormula = formula.reduceFormula(literal);
                    Environment ansEnv = solve(newFormula, env);
                    return ansEnv;
                } else {
                    LinkedList<Clause> randomClauseList = formula.ClauseMap.values().iterator().next();
                    int literal = randomClauseList.getFirst().literals.keySet().iterator().next();
                    env.addTrueLiteral(literal);
                    Formula newTrueFormula = formula.reduceFormula(literal);
                    Environment ansEnv = solve(newTrueFormula, env);
                    if (ansEnv == null) {
                        env.addFalseLiteral(literal);
                        Formula newFalseFormula = formula.reduceFormula(-literal);
                        Environment ansFalseEnv = solve(newFalseFormula, env);
                        return ansFalseEnv;
                    } else {
                        return ansEnv;
                    }
                }
            }
        }
}
