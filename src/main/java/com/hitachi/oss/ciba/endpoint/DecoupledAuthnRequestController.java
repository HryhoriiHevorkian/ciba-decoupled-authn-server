package com.hitachi.oss.ciba.endpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hitachi.oss.ciba.DecoupledAuthnControlParameterStore;
import com.hitachi.oss.ciba.bean.DecoupledAuthenticationRequest;
import com.hitachi.oss.ciba.service.CallbackAsyncThread;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/request-decoupled-authentication")
public class DecoupledAuthnRequestController {

    private static final Log log = LogFactory.getLog(DecoupledAuthnRequestController.class);

    @Autowired
    private ApplicationContext cibaContext;

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<String> doDecoupledAuthenticationAndConsent(@RequestBody MultiValueMap<String,String> request) {
        DecoupledAuthenticationRequest decoupledAuthnRequest = new DecoupledAuthenticationRequest();
        decoupledAuthnRequest.setBindingMessage(request.getFirst("binding_message"));
        decoupledAuthnRequest.setConsentRequired(Boolean.valueOf(request.getFirst("is_consent_required")));
        decoupledAuthnRequest.setDecoupledAuhtnBidingId(request.getFirst("decoupled_auth_id"));
        decoupledAuthnRequest.setLoginHint(request.getFirst("login_hint"));
        decoupledAuthnRequest.setScope(request.getFirst("scope"));
        dumpDecoupledAuthenticationRequest(decoupledAuthnRequest);

        log.warn("request started");
        CallbackAsyncThread callbackProcess = new CallbackAsyncThread(
                decoupledAuthnRequest.getLoginHint(),
                decoupledAuthnRequest.getDecoupledAuhtnBidingId(),
                DecoupledAuthnControlParameterStore.getInstance().getAuthnResult(),
                DecoupledAuthnControlParameterStore.getInstance().getWaitingInSec(),
                cibaContext.getCallbackUri(),
                cibaContext.getClientId(),
                cibaContext.getClientSecret());
        callbackProcess.start();

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(status);
    }

    private void dumpDecoupledAuthenticationRequest(DecoupledAuthenticationRequest request) {
        log.info(" binding_message = " + request.getBindingMessage());
        log.info(" is_consent_required = " + request.isConsentRequired());
        log.info(" decoupled_auhtn_binding_id = " + request.getDecoupledAuhtnBidingId());
        log.info(" login_hint = " + request.getLoginHint());
        log.info(" scope = " + request.getScope());
    }

}