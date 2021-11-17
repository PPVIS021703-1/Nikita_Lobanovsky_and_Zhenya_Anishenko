package com.secured.cbank.service;

import com.secured.cbank.entity.Card;
import com.secured.cbank.repository.CardRepository;
import com.secured.cbank.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;

@Service
public class AdministratorService {
    private RoleRepository roleRepository;
    private CardRepository cardRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdministratorService(RoleRepository roleRepository,
                                CardRepository cardRepository,
                                BCryptPasswordEncoder bCryptPasswordEncoder){
        this.roleRepository = roleRepository;
        this.cardRepository = cardRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private class BankUtility{

        public boolean checkForExistingCard(Card card){
            Card existingCard = cardRepository.
                    findCardByCardNumberAndPin(
                            card.getCardNumber(),
                            card.getPin()
                    );
            return existingCard == null ? false : true;
        }

        public boolean checkValidDate(String cardValidationDate){
            String year = Calendar.getInstance().get(Calendar.MONTH) + String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            year = year.replace("20", "");

            cardValidationDate = cardValidationDate.replace("/", "");

            char checker = cardValidationDate.charAt(0);

            if(checker == '0')
                cardValidationDate = cardValidationDate.replace("0", "");

            Integer yearInt = Integer.valueOf(year);
            Integer cardDateInt = Integer.valueOf(cardValidationDate);

            if(cardDateInt.equals(yearInt) || cardDateInt > yearInt)
                return true;
            else return false;
        }
    }

    public boolean addCard(Card card){
        BankUtility bankUtility = new BankUtility();

        if(card.getPin().length() == 4 && card.getCardNumber().length() == 16 &&
                !bankUtility.checkForExistingCard(card)&& bankUtility.checkValidDate(card.getCardValidationDate())){
            Card newCard = new Card();

            newCard.setCardNumber(card.getCardNumber());
            newCard.setCardValidationDate(card.getCardValidationDate());
            newCard.setValid(true);
            newCard.setCardCheck(0L);
            newCard.setRoles(Collections.singletonList(roleRepository.findRoleByRoleId(1L)));
            newCard.setPin(bCryptPasswordEncoder.encode(card.getPin()));

            cardRepository.save(newCard);

            return true;
        }else return false;
    }
}
