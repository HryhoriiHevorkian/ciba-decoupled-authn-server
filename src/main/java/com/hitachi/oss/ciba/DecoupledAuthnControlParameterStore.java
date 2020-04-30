package com.hitachi.oss.ciba;

public class DecoupledAuthnControlParameterStore {

    private static DecoupledAuthnControlParameterStore SINGLETON = new DecoupledAuthnControlParameterStore();

    public static DecoupledAuthnControlParameterStore getInstance() {
        return SINGLETON;
    }

    private int waitingInSec = 60;
    private String authResult = DecoupledAuthnStatus.SUCCEEDED;

    public int getWaitingInSec() {
        return waitingInSec;
    }

    public void setWaitingInSec(int waitingInSec) {
        this.waitingInSec = waitingInSec;
    }

    public String getAuthnResult() {
        return authResult;
    }

    public void setAuthResult(String authnResult) {
        this.authResult = authnResult;
    }

    
}
