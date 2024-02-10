package Model;

public class ItemDataModel {
    private String status;
    private boolean fav;
    private String name;

    public ItemDataModel(String status, boolean fav, String name) {
        this.status = status;
        this.fav = fav;
        this.name = name;
    }

    public ItemDataModel() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
