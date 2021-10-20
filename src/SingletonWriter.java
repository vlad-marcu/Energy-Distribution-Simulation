
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public final class SingletonWriter {
    private static SingletonWriter instance;

    static {
        instance = new SingletonWriter();
    }

    public static SingletonWriter getInstance() {
        return instance;
    }

    /**
     * writes the output to a JSON file
     * @param database
     * @param string
     * @throws FileNotFoundException
     */
    public void writer(final Database database, final String string) throws FileNotFoundException {
        JSONObject object = new JSONObject();
        JSONArray consumerArray = new JSONArray();
        JSONArray distributorArray = new JSONArray();
        for (Consumer consumer : database.getConsumers()) {

            Map m = new LinkedHashMap<>(3);
            m.put("id", consumer.getId());
            m.put("isBankrupt", consumer.isBankrupt());
            m.put("budget", consumer.getInitialBudget());
            consumerArray.add(m);

        }
        object.put("consumers", consumerArray);
        for (Distributor distributor : database.getDistributors()) {
            Map m = new LinkedHashMap(4);
            m.put("id", distributor.getId());
            m.put("isBankrupt", distributor.isBankrupt());
            m.put("budget", distributor.getInitialBudget());
            JSONArray consumerWithinDistributor = new JSONArray();
            for (Consumer consumer : distributor.getConsumersInput()) {
                Map n = new LinkedHashMap(3);
                n.put("consumerId", consumer.getId());
                n.put("price", consumer.getContract());
                n.put("remainedContractMonths", consumer.getRemainingMonths());
                consumerWithinDistributor.add(n);
            }
            m.put("contracts", consumerWithinDistributor);
            distributorArray.add(m);
        }
        object.put("distributors", distributorArray);
        PrintWriter pw = new PrintWriter(string);
        pw.write(object.toJSONString());

        pw.flush();
        pw.close();
    }
}
