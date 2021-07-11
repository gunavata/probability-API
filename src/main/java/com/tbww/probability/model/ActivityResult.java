package com.tbww.probability.model;

public enum ActivityResult {
    WIN("Won"),
    LOSE("Lost"),
    TIE("Tied");

    private String activity;

    ActivityResult(String activity) {
        this.activity = activity;
    }

    public String getActivity() {
        return activity;
    }
}
