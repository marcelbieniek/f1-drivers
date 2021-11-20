package pl.edu.pg.eti.marcelbieniek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class F1driversApplication {

	public static void main(String[] args) {
		SpringApplication.run(F1driversApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route("teams", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/teams/{team_name}", "/api/teams")
						.uri("http://localhost:8081"))
				.route("drivers", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/drivers", "/api/drivers/**", "/api/teams/{team_name}/drivers", "/api/teams/{team_name}/drivers/**")
						.uri("http://localhost:8082"))
				.build();
	}

	@Bean
	public CorsWebFilter corsWebFilter() {

		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		corsConfig.addAllowedHeader("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}
}
