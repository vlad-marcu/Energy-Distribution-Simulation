import java.util.ArrayList;

public final class Distributor extends Participant {

    private long initialBudget;
    private long contractLength;
    private long id;
    private long infrastructureCost;
    private long productionCost;
    private long profit = 0;
    private long contract = 0;
   private ArrayList<Consumer> consumersInput = new ArrayList<Consumer>();
    private boolean isBankrupt = false;

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public ArrayList<Consumer> getConsumersInput() {
        return consumersInput;
    }

    public void setConsumersInput(final ArrayList<Consumer> consumersInput) {
        this.consumersInput = consumersInput;
    }

    public long getContract() {
        return contract;
    }

    public void setContract(final long contract) {
        this.contract = contract;
    }





    /**
     * updates a distributor
     */
    public void update() {

        initialBudget = initialBudget
                        - infrastructureCost
                        - productionCost * consumersInput.size();





    }

    /**
     * calculates the contract price
     */
    public void contractCalculator() {
        if (consumersInput.size() == 0) {
            contract = infrastructureCost
                    + productionCost
                    + profit;

        } else {
            contract = Math.round(Math.floor(infrastructureCost / consumersInput.size())
                    + productionCost
                    + profit);
        }
    }

    /**
     * updates the infrastructure and the production cost
     * @param change
     */
    public void updateCostChange(final CostChange change) {
        this.infrastructureCost = change.getInfrastructureCost();
        this.productionCost = change.getProductionCost();
    }

    /**
     * calculates the profit
     */
    public void profitCalculator() {
        profit = Math.round(Math.floor(0.2 * productionCost));
    }

    @Override
    public String toString() {
        return "Distributor{" + "initialBudget="
                + initialBudget
                + ", id="
                + id
                + ", consumersInput="
                + consumersInput
                + ", isBankrupt="
                + isBankrupt
                + '}';
    }

    public long getProfit() {
        return profit;
    }

    public void setProfit(final long profit) {
        this.profit = profit;
    }

    public long getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(final long initialBudget) {
        this.initialBudget = initialBudget;
    }

    public long getContractLength() {
        return contractLength;
    }

    public void setContractLength(final long contractLength) {
        this.contractLength = contractLength;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(final long infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public long getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(final long productionCost) {
        this.productionCost = productionCost;
    }



}
