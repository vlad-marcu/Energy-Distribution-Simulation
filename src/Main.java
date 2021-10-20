public final class Main {
    private Main() {

    }

    /**
     *This is the whole game.It has multiple steps that it executes.
     * @param args arguments from the command line
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        Database database = new Database();
       long turns = SingletonReader.getInstance().readData(database, args[0]);
       for (int i = 0; i <= turns; i++) {
           SingletonSolver.getInstance().round(database, i);
       }
        SingletonWriter.getInstance().writer(database, args[1]);



    }
}
