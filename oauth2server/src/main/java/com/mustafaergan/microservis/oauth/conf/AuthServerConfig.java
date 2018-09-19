//package com.mustafaergan.microservis.oauth.conf;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//
//
//@Configuration
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@EnableAuthorizationServer
//public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//
////    @Override
////    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
////        oauthServer.passwordEncoder(passwordEncoder);
////    }
//
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.tokenKeyAccess("permitAll()")
//            .checkTokenAccess("isAuthenticated()").passwordEncoder(passwordEncoder);
//    }
//
//    @Override
//    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
////        clients.inMemory()
////            .withClient("SampleClientId")
////            .secret(passwordEncoder.encode("secret"))
////            .authorizedGrantTypes("authorization_code")
////            .scopes("user_info")
////            .autoApprove(true)
////            .redirectUris("http://localhost:8080/login", "http://localhost:8082/login")
////        // .accessTokenValiditySeconds(3600)
////        ; // 1 hour
//
//        clients.inMemory()
//                .withClient("test")
//                .secret("test")
//                .authorizedGrantTypes("password","client_credentials", "refresh_token","authorization_code")
//                .scopes("read", "write");
//    }
//
//
//}
