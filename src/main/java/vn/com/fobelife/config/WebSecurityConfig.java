/**
 * 
 */
package vn.com.fobelife.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ahuynh
 *         https://o7planning.org/en/11543/create-a-login-application-with-spring-boot-spring-security-spring-jdbc
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Qualifier("loginSuccessHandler")
    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;
    
    @Autowired
    @Qualifier("authenticationProviderImpl")
    private AuthenticationProvider authProvider;

    private static final String[] AUTH_WHITELIST = { "/",
            // -- swagger ui
            "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
            "/configuration/security", "/swagger-ui.html", "/webjars/**", "/nganluong/return", "/nganluong/cancel"
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        log.info("***** WebSecurityConfig *****");
        web.ignoring().antMatchers(HttpMethod.GET, "/user/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("WebSecurityPermission");
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/signup").hasAuthority("ADMIN")
                .antMatchers("/product").hasAuthority("ADMIN")
             // .anyRequest().permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/user/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/cart/fobelife", true)
                    .successHandler(loginSuccessHandler)
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/user/login")
                    .deleteCookies("JSESSIONID")
        ;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        //.authenticationProvider(authProvider)
        .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
        ;
    }
}
