package com.palibra.walletapi.controller.common;

import com.palibra.walletapi.security.AuthenticationFacade;
import com.palibra.walletapi.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

public abstract class TokenBaseController {

    private AuthenticationFacade authenticationFacade;

    @Autowired
    public final void setAuthenticationFacade(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    public UserPrincipal getAuthedUserInfo() {
        Authentication auth = authenticationFacade.getAuthentication();
        return (UserPrincipal) auth.getPrincipal();
    }


}
