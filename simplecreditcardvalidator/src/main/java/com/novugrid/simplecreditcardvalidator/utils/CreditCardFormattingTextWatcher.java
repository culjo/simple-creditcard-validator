package com.novugrid.simplecreditcardvalidator.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by appy on 02/12/2017.
 */

public class CreditCardFormattingTextWatcher implements TextWatcher {

    private boolean isDelete;
    private EditText editTextCard;
    private TextView tvCard;
    private ImageView ivType;
    CreditCardType creditCardType;

    public CreditCardFormattingTextWatcher(EditText editTextCard, TextView tvCard) {
        this.editTextCard = editTextCard;
        this.tvCard = tvCard;
    }

    public CreditCardFormattingTextWatcher(EditText etcard, TextView tvcard,CreditCardType creditCardType) {
        this.editTextCard = etcard;
        this.tvCard=tvcard;
        this.creditCardType=creditCardType;
    }

    public CreditCardFormattingTextWatcher(EditText etcard, TextView tvcard, ImageView ivType, CreditCardType creditCardType) {
        this.editTextCard = etcard;
        this.tvCard=tvcard;
        this.ivType=ivType;
        this.creditCardType = creditCardType;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(before==0)
            isDelete=false;
        else
            isDelete=true;
    }

    @Override
    public void afterTextChanged(Editable s) {
        String source = s.toString();
        int length = source.length();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(source);

        if(length > 0 && length % 5 == 0)
        {
            if(isDelete)
                stringBuilder.deleteCharAt(length - 1);
            else
                stringBuilder.insert(length - 1," ");

            editTextCard.setText(stringBuilder);
            editTextCard.setSelection(editTextCard.getText().length());

        }

        // This get the type of card that is being inputted
        if(creditCardType != null) {
            if (length >= 4){ creditCardType.setCardType(CreditCardUtils.getCardType(source.trim()));
            } else creditCardType.setCardType(CreditCardUtils.NONE);
        }

        // used to display the card number on an image card
        if(tvCard != null)
        {
            if(length==0)
                tvCard.setText("XXXX XXXX XXXX XXXX");
            else
                tvCard.setText(stringBuilder);
        }

        if(ivType != null && length == 0)
            ivType.setImageResource(android.R.color.transparent);

    }


    public interface CreditCardType
    {
        public void setCardType(int type);
    }



}
