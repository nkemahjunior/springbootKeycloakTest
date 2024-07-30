package com.zeco.keycloakDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class KeycloackDemoController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;



    @GetMapping(path = "/test")
    public String index() {
        return "external";
    }

    @GetMapping(path = "/test2")
    public String index2() {
        return "external22222222222";
    }


    @GetMapping(path = "/customers")
    public String customers(Authentication authentication) {


        /**
         * The default implementation of OAuth2AuthorizedClientService is InMemoryOAuth2AuthorizedClientService, which stores OAuth2AuthorizedClient objects in-memory.
         *
         * Alternatively, you can configure the JDBC implementation JdbcOAuth2AuthorizedClientService to persist OAuth2AuthorizedClient instances in a database.
         * */

        OAuth2AuthorizedClient authorizedClient = this.authorizedClientService.loadAuthorizedClient("keycloak", authentication.getName());
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

        System.out.println("---------------------------------------------------");
        System.out.println("---------------------------------------------------");
        System.out.println(accessToken.getTokenValue());
        System.out.println("---------------------------------------------------");
        System.out.println("---------------------------------------------------");



        return authentication.getName();
    }

}
