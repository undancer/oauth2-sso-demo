package com.undancer.id.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by undancer on 2017/5/24.
 */
public class ClientDetailsServiceImpl implements ClientDetailsService {
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails details = new BaseClientDetails();
        details.setClientId("demo");
        details.setClientSecret("demo");
        details.setResourceIds(details.getResourceIds());
        details.setAutoApproveScopes(Arrays.asList(".*"));
        details.setScope(Arrays.asList("read", "write"));
        details.setAuthorizedGrantTypes(Arrays.asList("authorization_code",
                "password", "client_credentials", "implicit", "refresh_token"));
        details.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
        details.setRegisteredRedirectUri(Collections.<String>emptySet());
//                details.setAccessTokenValiditySeconds(null);
//                details.setRefreshTokenValiditySeconds(null);
        return details;
    }
}
