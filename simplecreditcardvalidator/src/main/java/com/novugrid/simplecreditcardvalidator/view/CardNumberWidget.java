package com.novugrid.simplecreditcardvalidator.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.novugrid.simplecreditcardvalidator.R;
import com.novugrid.simplecreditcardvalidator.utils.CreditCardFormattingTextWatcher;

import static com.novugrid.simplecreditcardvalidator.utils.CreditCardUtils.AMEX;
import static com.novugrid.simplecreditcardvalidator.utils.CreditCardUtils.DISCOVER;
import static com.novugrid.simplecreditcardvalidator.utils.CreditCardUtils.MASTERCARD;
import static com.novugrid.simplecreditcardvalidator.utils.CreditCardUtils.NONE;
import static com.novugrid.simplecreditcardvalidator.utils.CreditCardUtils.VISA;

/**
 * Created by appy on 03/12/2017.
 */

public class CardNumberWidget extends LinearLayoutCompat {

    private ImageView cardIconImageView;
    private CreditCardEditText creditCardEditText;

    public CardNumberWidget(Context context) {
        super(context);
        initView(null);
    }

    public CardNumberWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);

    }

    public CardNumberWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    /*public CardNumberWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/


    private void initView(AttributeSet attributeSet) {

        inflate(getContext(), R.layout.card_number_layout, this);

        setOrientation(LinearLayout.HORIZONTAL);

        cardIconImageView = findViewById(R.id.card_icon);
        creditCardEditText = findViewById(R.id.card_number);

        creditCardEditText.addTextChangedListener(new CreditCardFormattingTextWatcher(creditCardEditText, null, null, new CreditCardFormattingTextWatcher.CreditCardType() {
            @Override
            public void setCardType(int type) {
                setCardIconImageView(type);
            }
        }));



    }

    public String getNumber() {
        String cardNumber = creditCardEditText.getText().toString();
        cardNumber = cardNumber.replace(" ", "");
        return cardNumber;
    }


    public void setCardIconImageView(int type)
    {
        switch(type)
        {
            case VISA:
                cardIconImageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_visa96));
                break;
            case MASTERCARD:
                cardIconImageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_mastercard96));
                break;
            case AMEX:
                cardIconImageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_american_express96));
                break;
            case DISCOVER:
                cardIconImageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_discover96));
                break;
            case NONE:
                cardIconImageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_credit_card96));
//                cardIconImageView.setImageResource(android.R.color.transparent);
                break;

        }


    }

}
