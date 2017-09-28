package jekirdek.com.t3mobil.model;

/**
 * Created by cem
 */
public class AttendeceListModel {

    private String name;
    private boolean isChecked;

    public AttendeceListModel(String name, boolean checked) {
        this.name = name;
        this.isChecked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }
}
