
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public final class SingletonReader {
    private static SingletonReader instance;

    static {
        instance = new SingletonReader();
    }

    public static SingletonReader getInstance() {
        return instance;
    }

    /**
     * it reads the data from the JSON file
     * @param database class where all the input is
     * @param fileName the file we are reading from
     * @return return de number of turns
     * @throws IOException
     * @throws ParseException
     */
    public long readData(final Database database, final String fileName)
            throws IOException, ParseException {
       Object object = new JSONParser().parse(new FileReader(fileName));
       JSONObject jsonObject = (JSONObject) object;
       long noOfturns = (long) jsonObject.get("numberOfTurns");
       JSONObject initialData = (JSONObject) jsonObject.get("initialData");
       JSONArray consumers = (JSONArray) initialData.get("consumers");
        for (Object jsonConsumer : consumers) {
           Consumer consumer = (Consumer) ParticipantFactory.createParticipant(
                   ParticipantFactory.ParticipantType.Consumer);
           consumer.setInitialBudget((long) ((JSONObject) jsonConsumer).get("initialBudget"));
           consumer.setMonthlyIncome((long) ((JSONObject) jsonConsumer).get("monthlyIncome"));
           consumer.setId((long) ((JSONObject) jsonConsumer).get("id"));
           database.getConsumers().add(consumer);

        }
        JSONArray distributors = (JSONArray) initialData.get("distributors");
        for (Object jsonDistributor : distributors) {
          Distributor distributor = (Distributor) ParticipantFactory.createParticipant(
                  ParticipantFactory.ParticipantType.Distributor);

          distributor.setInitialBudget((long) ((JSONObject) jsonDistributor).get("initialBudget"));
          distributor.setContractLength((long)
                  ((JSONObject) jsonDistributor).get("contractLength"));
          distributor.setId((long) ((JSONObject) jsonDistributor).get("id"));
          distributor.setInfrastructureCost((long)
                  ((JSONObject) jsonDistributor).get("initialInfrastructureCost"));
          distributor.setProductionCost((long)
                  ((JSONObject) jsonDistributor).get("initialProductionCost"));
          database.getDistributors().add(distributor);


        }
        JSONArray monthlyUpdates = (JSONArray) jsonObject.get("monthlyUpdates");
        int counter = 0;
        for (Object jsonMonthlyUpdate : monthlyUpdates) {
            JSONObject monthlyUpdate = (JSONObject) jsonMonthlyUpdate;
            ArrayList<Consumer> consumersToAdd = new ArrayList<>();
            ArrayList<CostChange> costChangesToAdd = new ArrayList<>();
            JSONArray newConsumers = (JSONArray) monthlyUpdate.get("newConsumers");
            if (newConsumers != null) {
                for (Object newConsumer : newConsumers) {
                    Consumer consumer = (Consumer) ParticipantFactory.createParticipant(
                            ParticipantFactory.ParticipantType.Consumer);

              consumer.setId((long) ((JSONObject) newConsumer).get("id"));
              consumer.setInitialBudget((long) ((JSONObject) newConsumer).get("initialBudget"));
              consumer.setMonthlyIncome((long) ((JSONObject) newConsumer).get("monthlyIncome"));
                            consumersToAdd.add(consumer);


                }
        }
            JSONArray costChanges = (JSONArray) monthlyUpdate.get("costsChanges");
            if (costChanges != null) {
                for (Object costChange : costChanges) {
                    costChangesToAdd.add(new CostChange(
                                    ((long) ((JSONObject) costChange).get("id")),
                                    ((long) ((JSONObject) costChange).get("infrastructureCost")),
                                    ((long) ((JSONObject) costChange).get("productionCost"))
                            )
                    );
                }
            }
            database.getMonthlyChanges().add(
                    new MonthlyChange(consumersToAdd, costChangesToAdd, counter));
            counter++;


        }


        return noOfturns;

    }
}
