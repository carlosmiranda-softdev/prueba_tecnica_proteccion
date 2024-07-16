package com.proteccion.pruebatecnica.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proteccion.pruebatecnica.model.Fibonacci;
import com.proteccion.pruebatecnica.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("api/v1/")
public class FibonacciController {

    @Autowired
    private EmailService emailService;

    /**
     * Post request to path "execute" that handle fibonacci series
     * and email service
     *
     * @param data Json with
     * @return Message with the notification of success or failure
     */
    @PostMapping("execute")
    public ResponseEntity<String> executeFibonacciAtTime(@RequestBody String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(data);
            if (jsonNode.has("time")) {
                String strTime = jsonNode.get("time").asText();
                formatter.parse(strTime);

                LocalTime time = LocalTime.parse(strTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
                int minutes = time.getMinute();
                int seconds = time.getSecond();

                int firstDigitOfMinutes = minutes / 10;
                int secondDigitOfMinutes = minutes % 10;

                if (firstDigitOfMinutes == 0 && secondDigitOfMinutes == 0) {
                    return ResponseEntity.badRequest().body("Ambas semillas no pueden ser igual a 0");
                }

                Fibonacci fibonacci = new Fibonacci();
                fibonacci.execute(firstDigitOfMinutes, secondDigitOfMinutes, seconds);

                String[] toEmails = {"didier.correa@proteccion.com.co", "correalondon@gmail.com", "carlosmrnd16@gmail.com"};

                try {
                    emailService.sendEmail(toEmails,
                            "Prueba Técnica - Carlos de Jesús Miranda de la Hoz",
                            "Hola !, aquí puedes revisar el resultado de la ejecución de la serie Fibonnacci:\n" +
                                    "\nFecha y hora de ejecucion: " + strTime +
                                    "\n\nResultado de la serie Fibonacci: " + fibonacci.getSerie().toString() +
                                    "\n\nBuen dia.");
                } catch (MessagingException e) {
                    return ResponseEntity.badRequest().body("Error while trying to send the email.");
                }

                return ResponseEntity.ok("Received time for Fibonacci: " + time +
                        "\nFibonacci result: " + fibonacci.getSerie());
            } else {
                return ResponseEntity.badRequest().body("The 'time' property is missing in the Json" +
                        "\nValid Json structure: { 'time':'HH:mm:ss' }");
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid time format:\n" + data +
                    "\nThe format should be HH:mm:ss, for instance: 08:50:10");
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body("Error while processing the Json data, try again!");
        }
    }

}
