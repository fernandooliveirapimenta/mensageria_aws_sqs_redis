package br.com.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Greeting"})
@RestController
@RequestMapping(value = "v1/greeting")
public class GreetingController {

  @ApiOperation(value = "Greets the world or Niteroi")
  @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Greeting hello(@RequestParam(required = false) boolean niteroi) {
    Greeting greeting = new Greeting("Hello world");
    if (niteroi) {
      greeting.setMessage(greeting.getMessage().replace("world", "Niteroi"));
    }
    return greeting;
  }

  @ApiOperation(value = "Greets a person given her name")
  @GetMapping(value = "/hello/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Greeting get(@PathVariable String name) {
    return new Greeting("Hello, " + name);
  }

}