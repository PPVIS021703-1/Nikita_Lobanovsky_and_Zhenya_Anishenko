package com.secured.cbank.service;

import com.secured.cbank.entity.Card;
import com.secured.cbank.entity.Operations;
import com.secured.cbank.repository.CardRepository;
import com.secured.cbank.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    private final CardRepository cardRepository;
    private final OperationsRepository operationsRepository;

    @Autowired
    public BankService(CardRepository cardRepository,
                       OperationsRepository operationsRepository){
        this.cardRepository = cardRepository;
        this.operationsRepository = operationsRepository;
    }

    public boolean refillCard(Long amount) throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String cardNumber = userDetails.getUsername();

        Card receiverCard = cardRepository.findCardByCardNumber(cardNumber).orElseThrow(
                () -> new Exception("error happened")
        );

        receiverCard.setCardCheck(receiverCard.getCardCheck() + amount);
        cardRepository.save(receiverCard);

        Operations refillCard = new Operations();

        refillCard.setCardId(cardRepository.findCardByCardNumber(cardNumber).orElseThrow(RuntimeException::new).getCardId());
        refillCard.setOperation("refill card");

        operationsRepository.save(refillCard);
        return receiverCard != null ? true : false;
    }

    public Long checkAmountOnCard() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String cardNumber = userDetails.getUsername();

        Card receiverCard = cardRepository.findCardByCardNumber(cardNumber).orElseThrow(
                () -> new Exception("error happened")
        );

        return receiverCard.getCardCheck();
    }

    public boolean transferToCard(Long amount, String friendCard) throws Exception {
        if(friendCard.length() != 16)
            return false;

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String cardNumber = userDetails.getUsername();

            Card senderCard = cardRepository.findCardByCardNumber(cardNumber).orElseThrow(
                    () -> new Exception("error happened")
            );
            Card receiverCard = cardRepository.findCardByCardNumber(friendCard).orElseThrow(
                    () -> new UsernameNotFoundException(String.format("Card with number %s not found", friendCard))
            );

            if(senderCard.getCardCheck() >= amount) {
                senderCard.setCardCheck(senderCard.getCardCheck() - amount);
                receiverCard.setCardCheck(receiverCard.getCardCheck() + amount);

                cardRepository.save(senderCard);
                cardRepository.save(receiverCard);
            }else return false;

        Operations transferToCard = new Operations();

        transferToCard.setCardId(cardRepository.findCardByCardNumber(cardNumber).orElseThrow(RuntimeException::new).getCardId());
        transferToCard.setOperation("transfer to card with id " + receiverCard.getCardId());

        operationsRepository.save(transferToCard);
        return true;
    }

    public String getNumber(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }
}
