package br.com.zupacademy.proposta.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSpringSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_usuario:read")
                        .antMatchers(HttpMethod.POST, "/propostas/**").hasAuthority("SCOPE_usuario:write")
                        .antMatchers(HttpMethod.POST, "/cartoes/**").hasAuthority("SCOPE_usuario:write")
                        .antMatchers(HttpMethod.GET, "/actuator/**").hasAuthority("SCOPE_actuator:read"))
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
