package com.yakauleu.ibank.service;

import com.yakauleu.ibank.database.model.Card;
import com.yakauleu.ibank.database.model.Person;
import com.yakauleu.ibank.database.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final CardRepository cardRepository;

    public List<Card> getPersonCards(Person person) {
         return cardRepository.findAllByPerson(person.getId());
    }
    //payments
    //make a payment
}
