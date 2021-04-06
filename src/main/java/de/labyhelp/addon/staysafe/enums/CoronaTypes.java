package de.labyhelp.addon.staysafe.enums;

public enum CoronaTypes {

    NEWCONFIRMED("{\"ID\":\"2dbd9222-7f50-47c1-b032-e99f5ae4d900\",\"Message\":\"\",\"Global\":{\"NewConfirmed\""),
    TOTALCONFIRMED("\"TotalConfirmed\""),
    NEWDEATHS("\"NewDeaths\""),
    NEWRECOVERED("\"NewRecovered\"");

    private final String jsonKey;

    CoronaTypes(String jsonKey) {
        this.jsonKey = jsonKey;
    }


    public String getJsonKey() {
        return jsonKey;
    }
}
