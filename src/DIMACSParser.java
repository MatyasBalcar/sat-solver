import java.io.*;
import java.util.*;

public class DIMACSParser {
    public static CNFFormula parse(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int numVariables = 0;
        CNFFormula formula = null;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("c") || line.isEmpty()) continue;
            if (line.startsWith("p")) {
                String[] parts = line.split("\\s+");
                numVariables = Integer.parseInt(parts[2]);
                formula = new CNFFormula(numVariables);
            } else {
                String[] literals = line.split("\\s+");
                Set<Integer> clauseLiterals = new HashSet<>();
                for (String lit : literals) {
                    int val = Integer.parseInt(lit);
                    if (val == 0) break;
                    clauseLiterals.add(val);
                }
                formula.addClause(new Clause(clauseLiterals));
            }
        }
        reader.close();
        return formula;
    }
}
