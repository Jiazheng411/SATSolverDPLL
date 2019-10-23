package new_sat;

import java.util.HashMap;

public class Environment {
    private HashMap<Integer, Boolean> envMap = new HashMap<Integer, Boolean>();

    public Environment(){
    }

    public void addTrueLiteral(int literal){
        if(literal < 0) {
            this.envMap.put(-literal, false);
        }else{
            this.envMap.put(literal, true);
        }
    }

    public void addFalseLiteral(int literal){
        if(literal < 0) {
            this.envMap.put(-literal, true);
        }else{
            this.envMap.put(literal, false);
        }
    }

    public void removeLiteral(int literal){
        this.envMap.remove(literal);
    }

    public String toString(){
        return this.envMap.toString();
    }

}
