public class City {
    private String CITY_NAME;
    private Integer REGION;
    private static String[] STREETS;

    City(String CITY_NAME) {
        this.CITY_NAME = CITY_NAME;
    }
    City(String CITY_NAME, Integer REGION, String[] STREETS) {
        this.CITY_NAME = CITY_NAME;
        this.REGION = REGION;
        this.STREETS = STREETS;
    }

    public String getCity(){
        return this.CITY_NAME;
    }
    public String[] getStreets(){
        return this.STREETS;
    }
}
