import java.util.*;

public class DPLLSolver {
    public int unitPropagationCount = 0;
    public int decisionCount = 0;

    public boolean solve(CNFFormula formula, Map<Integer, Boolean> assignment) {
        while (true) {
            Clause unitClause = findUnitClause(formula);
            if (unitClause == null) break;

            int literal = unitClause.getUnitLiteral();
            boolean value = literal > 0;
            assignment.put(Math.abs(literal), value);
            applyAssignment(formula, literal);
            unitPropagationCount++;
        }

        if (formula.clauses.stream().anyMatch(Clause::isEmpty)) return false;
        if (formula.clauses.isEmpty()) return true;

        int variable = chooseVariable(formula);
        decisionCount++;

        CNFFormula formulaTrue = copyFormula(formula);
        Map<Integer, Boolean> assignmentTrue = new HashMap<>(assignment);
        assignmentTrue.put(variable, true);
        applyAssignment(formulaTrue, variable);
        if (solve(formulaTrue, assignmentTrue)) {
            assignment.clear();
            assignment.putAll(assignmentTrue);
            return true;
        }

        CNFFormula formulaFalse = copyFormula(formula);
        Map<Integer, Boolean> assignmentFalse = new HashMap<>(assignment);
        assignmentFalse.put(variable, false);
        applyAssignment(formulaFalse, -variable);
        if (solve(formulaFalse, assignmentFalse)) {
            assignment.clear();
            assignment.putAll(assignmentFalse);
            return true;
        }

        return false;
    }

    private Clause findUnitClause(CNFFormula formula) {
        for (Clause c : formula.clauses) {
            if (c.isUnit()) return c;
        }
        return null;
    }

    private int chooseVariable(CNFFormula formula) {
        for (Clause c : formula.clauses) {
            for (int lit : c.literals) return Math.abs(lit);
        }
        throw new RuntimeException("No variables left to choose.");
    }

    private void applyAssignment(CNFFormula formula, int literal) {
        formula.clauses.removeIf(c -> c.literals.contains(literal));
        for (Clause c : formula.clauses) {
            c.literals.remove(-literal);
        }
    }

    private CNFFormula copyFormula(CNFFormula formula) {
        CNFFormula copy = new CNFFormula(formula.numVariables);
        for (Clause c : formula.clauses) {
            copy.addClause(new Clause(c.literals));
        }
        return copy;
    }
}
