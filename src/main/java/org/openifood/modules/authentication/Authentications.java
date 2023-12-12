package org.openifood.modules.authentication;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@ApplicationScoped
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Authentications {

    @Inject
    MailAuthentication mailAuthentication;

    @Inject
    PhoneAuthentication phoneAuthentication;

    public MailAuthentication mail() {
        return mailAuthentication;
    }

    public PhoneAuthentication phone() {
        return phoneAuthentication;
    }

}
