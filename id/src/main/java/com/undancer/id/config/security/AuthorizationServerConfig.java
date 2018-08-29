package com.undancer.id.config.security;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Created by undancer on 2017/5/24.
 */
@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(AuthorizationServerProperties.class)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    ClientDetailsService clientDetailsService;

    UserDetailsService userDetailsService; //这东西在OAuth2.0的authorizationServer中的作用是什么？

    AuthenticationManager authenticationManager;

    AccessTokenConverter tokenConverter;

    private AuthorizationServerProperties properties;

    public AuthorizationServerConfig(ClientDetailsService clientDetailsService,
                                     UserDetailsService userDetailsService,
                                     AuthenticationConfiguration authenticationConfiguration,
                                     ObjectProvider<TokenStore> tokenStore,
                                     ObjectProvider<AccessTokenConverter> tokenConverter,
                                     AuthorizationServerProperties properties
    ) throws Exception {
        this.clientDetailsService = clientDetailsService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
//        this.tokenStore = tokenStore.getIfAvailable();
        this.tokenConverter = tokenConverter.getIfAvailable();
        this.properties = properties;
    }

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(this.clientDetailsService);
    }

    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        if (this.properties.getCheckTokenAccess() != null) {
            security.checkTokenAccess(this.properties.getCheckTokenAccess());
        }
        if (this.properties.getTokenKeyAccess() != null) {
            security.tokenKeyAccess(this.properties.getTokenKeyAccess());
        }
        if (this.properties.getRealm() != null) {
            security.realm(this.properties.getRealm());
        }
    }

    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.authenticationManager(this.authenticationManager);
        if (this.tokenConverter != null) {
            endpoints.accessTokenConverter(this.tokenConverter);
        }
        endpoints.tokenStore(new InMemoryTokenStore());
        endpoints.userDetailsService(this.userDetailsService);
    }
}
