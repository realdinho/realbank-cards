package com.realdiv.cards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.realdiv.cards.config.CardConfig;
import com.realdiv.cards.model.Card;
import com.realdiv.cards.model.Property;
import com.realdiv.cards.repository.CardRepository;

@RestController
@RequestMapping("cards")
public class CardController {
    
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    CardConfig cardConfig;

    @GetMapping("/{customerId}")
    public List<Card> getCardsList(
        @PathVariable int customerId
    ) {
        List<Card> cards = cardRepository.findByCustomerId(customerId);
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
