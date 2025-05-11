public class BatchSolver {
    public static void main(String[] args) throws Exception {
        int start = Integer.parseInt(args[0]);
        int end = Integer.parseInt(args[1]);
        int count = end - start + 1;
        double totalTime = 0;
        double unitPropagationCount = 0;

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
            System.out.println();
        }
        System.out.println("Solved " + count + " problems");
        System.out.printf("Average time: %.5f\nTotal time %.5f\n", totalTime/ count, totalTime);
        System.out.printf("Average propagation count: %.5f\n", unitPropagationCount/count);
    }
}
