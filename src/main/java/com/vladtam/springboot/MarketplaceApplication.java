package com.vladtam.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class MarketplaceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarketplaceApplication.class, args);
	}
}


