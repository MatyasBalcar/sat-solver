public class BatchSolver {
    public static void main(String[] args) throws Exception {
        int start = Integer.parseInt(args[0]);
        int end = Integer.parseInt(args[1]);
        int count = end - start + 1;
        double totalTime = 0;
        double unitPropagationCount = 0;
        double decisionCount = 0;

        String prefix = "";
        if (args.length > 3) {
            prefix = args[3];
        }

        String path = args[2];
        for (int i = start; i <= end; i++) {
            Double[] temp;

            String filename =path + "/" + prefix +  i + ".cnf";
            System.out.println("=== Solving " + filename + " ===");
            temp = Solver.compute(new String[]{filename});
            totalTime += temp[0];
            unitPropagationCount += temp[1];
            decisionCount += temp[2];
            System.out.println();
        }
        System.out.println("Solved " + count + " problems");
        System.out.printf("Average time: %.3f\nTotal time %.3f\n", totalTime/ count, totalTime);
        System.out.printf("Average propagation count: %.3f\n", unitPropagationCount/count);
        System.out.printf("Average decision count: %.3f\n", decisionCount/count);
    }
}
