//package com.example.board.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration // Bean 등록
//public class SwaggerConfig {
//  @Bean
//    public Docket api() {
//      return new Docket(DocumentationType.OAS_30)
//              .select()
//              .apis(RequestHandlerSelectors.basePackage("com.example.board.controller")) // 해당 패키지 내부 api가 swagger에 포함됨.
//              .paths(PathSelectors.any())
//              .build().apiInfo(apiInfo());
//  }
//
//  private ApiInfo apiInfo() {
//      return new ApiInfoBuilder()
//              .title("SWAGGER TEST")
//              .description("Boards Project OpenAPI v1.0")
//              .version("1.0")
//              .build();
//  }
//}