package com.realdiv.cards.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.realdiv.cards.model.Card;

public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findByCustomerId(int customerId);
}
