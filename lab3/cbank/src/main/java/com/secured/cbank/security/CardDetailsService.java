package com.secured.cbank.security;

import com.secured.cbank.entity.Card;
import com.secured.cbank.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CardDetailsService implements UserDetailsService {
    private CardRepository cardRepository;

    @Autowired
    public CardDetailsService(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String cardNumber) throws UsernameNotFoundException {
        Card findCard = cardRepository.findCardByCardNumber(cardNumber).orElseThrow(
                () -> new UsernameNotFoundException(String.format("card %s not found", cardNumber))
        );

        return new CardDetails(findCard);
    }
}
