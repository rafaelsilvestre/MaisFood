package br.com.omaisfood.endpoint;

import br.com.omaisfood.model.Card;
import br.com.omaisfood.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("card")
public class CardEndPoint {
    @Autowired
    private CardService cardService;

    CardEndPoint(CardService cardService){
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards(){
        List<Card> cards = this.cardService.gatAllCards();
        return new ResponseEntity<List<Card>>(cards, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Card> saveCard(@RequestBody Card card){
        return new ResponseEntity<Card>(this.cardService.saveCard(card), HttpStatus.OK);
    }
}
