import java.util.ArrayList;
import java.util.List;

public class CNFFormula {
    List<Clause> clauses;
    int numVariables;

    public CNFFormula(int numVariables) {
        this.numVariables = numVariables;
        clauses = new ArrayList<>();
    }

    public void addClause(Clause clause) {
        clauses.add(clause);
    }
}
