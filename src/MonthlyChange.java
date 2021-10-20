import java.util.ArrayList;

public final class MonthlyChange {
    private ArrayList<Consumer> newConsumers = new ArrayList<Consumer>();
    private ArrayList<CostChange> costChanges = new ArrayList<CostChange>();
    private int id;


    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public ArrayList<CostChange> getCostChanges() {
        return costChanges;
    }

    public ArrayList<Consumer> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final ArrayList<Consumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    @Override
    public String toString() {
        return "MonthlyChange{" + "newConsumers="
                + newConsumers
                + ", costChanges="
                + costChanges
                + ", id="
                + id
                + '}';
    }

    public void setCostChanges(final ArrayList<CostChange> costChanges) {
        this.costChanges = costChanges;
    }

    public MonthlyChange(final ArrayList<Consumer> newConsumers,
                         final ArrayList<CostChange> costChanges,
                         final int id) {
        this.newConsumers = newConsumers;
        this.costChanges = costChanges;
        this.id = id;
    }
}
