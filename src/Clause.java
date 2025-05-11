import java.util.HashSet;
import java.util.Set;

public class Clause {
    Set<Integer> literals;

    public Clause(Set<Integer> literals) {
        this.literals = new HashSet<>(literals);
    }

    public boolean isUnit() {
        return literals.size() == 1;
    }

    public Integer getUnitLiteral() {
        if (isUnit()) return literals.iterator().next();
        return null;
    }

    public boolean isEmpty() {
        return literals.isEmpty();
    }

    @Override
    public String toString() {
        return literals.toString();
    }
}
