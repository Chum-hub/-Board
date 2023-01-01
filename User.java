public class User {
    private String name;
    private String password;
    private String numberTel;
    private boolean isBroker;
    private Property[] publications;

    User() {
        this.publications = new Property[0];
    }

    User(String name, String password, String numberTel, boolean isBroker) {
        this.name = name;
        this.password = password;
        this.numberTel = numberTel;
        this.isBroker = isBroker;
        this.publications = new Property[0];
    }

    public void setPublications(Property[] publications){
        this.publications = publications;
    }
    public Property[] getPublications() {
        return this.publications;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPhoneNumber(String numberTel) {
        this.numberTel = numberTel;
    }

    public String getPhoneNumber() {
        return this.numberTel;
    }

    public void setBroker(int broker) {
        if (broker == 1) {
            this.isBroker = true;
        } else {
            this.isBroker = false;
        }
    }

    public boolean getBroker() {
        return this.isBroker;
    }
}
