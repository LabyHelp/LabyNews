package de.marvhuelsmann.labynews.enums;

public enum CoronaTypes {

    NEWCONFIRMED("{\"Message\":\"\",\"Global\":{\"NewConfirmedNewConfirmed\""),
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
