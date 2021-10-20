import java.util.ArrayList;


public final class Database {
    private ArrayList<Consumer> consumers =  new ArrayList<Consumer>();
    private ArrayList<Distributor> distributors = new ArrayList<Distributor>();
    private ArrayList<MonthlyChange> monthlyChanges = new ArrayList<MonthlyChange>();



    /**
     *
     * @param index
     * @return the distributor that has a certain index
     */
    public Distributor getDistributorByIndex(final long index) {
        for (Distributor distributor : distributors) {
            if (distributor.getId() == index) {
                return distributor;
            }
        }
        return null;
    }

    /**
     *
     * @return the distributor with the cheapest contract
     */
    public Distributor getDistributorMinContract() {
        long min = 9999;//I chose a big number in order to be sure that the min field is changed
        long index = 0;
        for (int i = 0; i < distributors.size(); i++) {
            if (!distributors.get(i).isBankrupt()) {
                if (distributors.get(i).getContract() < min) {
                    min = distributors.get(i).getContract();
                    index = distributors.get(i).getId();
                }
            }
        }
        return getDistributorByIndex(index);
    }


    public ArrayList<MonthlyChange> getMonthlyChanges() {
        return monthlyChanges;
    }

    public void setMonthlyChanges(final ArrayList<MonthlyChange> monthlyChanges) {
        this.monthlyChanges = monthlyChanges;
    }

    public ArrayList<Consumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<Consumer> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<Distributor> distributors) {
        this.distributors = distributors;
    }


}
