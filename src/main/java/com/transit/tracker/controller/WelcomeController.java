package com.transit.tracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    
    @GetMapping("/")
    public String welcome() {
        return "<html>" +
                "<head>" +
                "<title>TTC Transit Tracker API</title>" +
                "<style>" +
                "body { font-family: Arial; max-width: 800px; margin: 50px auto; padding: 20px; }" +
                "h1 { color: #ED1B2E; }" +
                "code { background: #f4f4f4; padding: 2px 6px; border-radius: 3px; }" +
                "a { color: #ED1B2E; text-decoration: none; }" +
                "a:hover { text-decoration: underline; }" +
                ".endpoint { background: #f9f9f9; padding: 15px; margin: 10px 0; border-left: 4px solid #ED1B2E; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<h1>🚌 TTC Transit Tracker API</h1>" +
                "<p>Real-time Toronto Transit Commission tracking REST API built with Spring Boot</p>" +
                
                "<h2>API Endpoints</h2>" +
                
                "<div class='endpoint'>" +
                "<strong>GET /api/test</strong><br>" +
                "Health check endpoint<br>" +
                "<a href='/api/test'>Try it →</a>" +
                "</div>" +
                
                "<div class='endpoint'>" +
                "<strong>GET /api/routes</strong><br>" +
                "Get all TTC routes<br>" +
                "<a href='/api/routes'>Try it →</a>" +
                "</div>" +
                
                "<div class='endpoint'>" +
                "<strong>GET /api/routes/{routeTag}/stops</strong><br>" +
                "Get stops for a specific route<br>" +
                "<a href='/api/routes/501/stops'>Try it (501 Queen) →</a>" +
                "</div>" +
                
                "<div class='endpoint'>" +
                "<strong>GET /api/stops/{stopId}/predictions</strong><br>" +
                "Get arrival predictions for a stop<br>" +
                "<a href='/api/stops/1/predictions'>Try it (Stop 1) →</a>" +
                "</div>" +
                
                "<div class='endpoint'>" +
                "<strong>GET /api/routes/{routeTag}/vehicles</strong><br>" +
                "Get real-time vehicle locations<br>" +
                "<a href='/api/routes/501/vehicles'>Try it (501 Queen) →</a>" +
                "</div>" +
                
                "<h2>Tech Stack</h2>" +
                "<ul>" +
                "<li>Java 17</li>" +
                "<li>Spring Boot 3.2</li>" +
                "<li>RESTful API Architecture</li>" +
                "<li>Maven Build Tool</li>" +
                "</ul>" +
                
                "<h2>GitHub</h2>" +
                "<p><a href='https://github.com/synshaikh-a11y/TTC_Tracker' target='_blank'>View Source Code →</a></p>" +
                
                "</body>" +
                "</html>";
    }
}