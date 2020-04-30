package com.hitachi.oss.ciba.bean;

public class DecoupledAuthenticationRequest {

    private String decoupledAuhtnBidingId;
    private String loginHint;
    private boolean isConsentRequred;
    private String scope;
    private String bindingMessage;

    public String getDecoupledAuhtnBidingId() {
        return decoupledAuhtnBidingId;
    }

    public void setDecoupledAuhtnBidingId(String decoupledAuhtnBidingId) {
        this.decoupledAuhtnBidingId = decoupledAuhtnBidingId;
    }

    public String getLoginHint() {
        return loginHint;
    }

    public void setLoginHint(String username) {
        this.loginHint = username;
    }

    public boolean isConsentRequired() {
        return isConsentRequred;
    }

    public void setConsentRequired(boolean isConsentRequred) {
        this.isConsentRequred = isConsentRequred;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getBindingMessage() {
        return bindingMessage;
    }

    public void setBindingMessage(String bindingMessage) {
        this.bindingMessage = bindingMessage;
    }
}
