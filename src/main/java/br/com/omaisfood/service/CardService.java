package br.com.omaisfood.service;

import br.com.omaisfood.model.Card;
import br.com.omaisfood.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    CardService(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    public List<Card> gatAllCards(){
        return this.cardRepository.findAll();
    }

    public Card saveCard(Card card){
        return this.cardRepository.save(card);
    }
}
