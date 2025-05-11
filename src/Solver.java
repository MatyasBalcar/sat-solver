import java.util.*;

public class Solver {
    public static void main(String[] args) throws Exception {
        compute(args);
    }
    public static Double[] compute(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Použití: java Solver vstup.cnf");
            System.exit(1);
        }

        String filename = args[0];
        Timer initTimer = new Timer();
        initTimer.start();
        CNFFormula formula = DIMACSParser.parse(filename);
        double initTime = initTimer.stop();

        Timer solveTimer = new Timer();
        solveTimer.start();
        DPLLSolver solver = new DPLLSolver();
        Map<Integer, Boolean> assignment = new HashMap<>();
        boolean result = solver.solve(formula, assignment);
        double solveTime = solveTimer.stop();

        System.out.println(result ? "SAT" : "UNSAT");

        if (result) {
            List<Integer> sortedLiterals = new ArrayList<>();
            for (int var = 1; var <= formula.numVariables; var++) {
                if (assignment.getOrDefault(var, false)) sortedLiterals.add(var);
                else sortedLiterals.add(-var);
            }
            Collections.sort(sortedLiterals, Comparator.comparingInt(Math::abs));
            for (int lit : sortedLiterals) System.out.print(lit + " ");
            System.out.println();
        } else {
            System.out.println();
        }

        System.out.printf("%.5f\n", initTime);
        System.out.printf("%.5f\n", solveTime);
        System.out.println(solver.unitPropagationCount);
        System.out.println(solver.decisionCount);
        Double[] resultArray= new Double[]{0.0, 0.0};
        resultArray[0] = solveTime;
        resultArray[1] = Double.parseDouble(String.valueOf(solver.unitPropagationCount));
        return resultArray;
    }
}
