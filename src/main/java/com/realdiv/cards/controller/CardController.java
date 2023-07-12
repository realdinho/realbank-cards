package com.realdiv.cards.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.realdiv.cards.config.CardConfig;
import com.realdiv.cards.model.Card;
import com.realdiv.cards.model.Property;
import com.realdiv.cards.repository.CardRepository;

@RestController
public class CardController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);
    
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    CardConfig cardConfig;

    @GetMapping("/{customerId}")
    public List<Card> getCardsList(
        @RequestHeader("realbank-correlation-id") String correlationId,
        @PathVariable int customerId
    ) {
        logger.info("RealBank - getting cards of customer {}. Correlation ID: {}", customerId, correlationId);
        List<Card> cards = cardRepository.findByCustomerId(customerId);
        logger.info("RealBank - found {} cards of customer {}", cards.size(), customerId);
        return cards;
    }

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Property props = new Property(
                            cardConfig.getMsg(), 
                            cardConfig.getBuildVersion(), 
                            cardConfig.getMailDetails(), 
                            cardConfig.getActiveBranches()
                        );
        return ow.writeValueAsString(props);
    }
}
