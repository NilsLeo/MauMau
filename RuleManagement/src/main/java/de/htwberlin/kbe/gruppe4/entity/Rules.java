package de.htwberlin.kbe.gruppe4.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rules")
public class Rules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean drawTwoOnSevenToggled;
    private boolean chooseSuitOnJackToggled;

    private boolean directionClockwise;
    private boolean drawTwoOnSevenEnabled;
    private boolean chooseSuitOnJackEnabled;
    private boolean reverseOnAceEnabled;
    private boolean rememberedToSayMauMau;

    @Enumerated(EnumType.STRING)
    private Suit suit;


    public boolean isDirectionClockwise() {
        return directionClockwise;
    }

    public void setDirectionClockwise(boolean directionClockwise) {
        this.directionClockwise = directionClockwise;
    }

    public Rules() {
        this.directionClockwise = true;
    }

    public boolean isRememberedToSayMauMau() {
        return rememberedToSayMauMau;
    }

    public void setRememberedToSayMauMau(boolean rememberedToSayMauMau) {
        this.rememberedToSayMauMau = rememberedToSayMauMau;
    }


    public boolean isDrawTwoOnSevenEnabled() {
        return drawTwoOnSevenEnabled;
    }

    public void setDrawTwoOnSevenEnabled(boolean drawTwoOnSevenEnabled) {
        this.drawTwoOnSevenEnabled = drawTwoOnSevenEnabled;
    }

    public boolean isChooseSuitOnJackEnabled() {
        return chooseSuitOnJackEnabled;
    }

    public void setChooseSuitOnJackEnabled(boolean chooseSuitOnJackEnabled) {
        this.chooseSuitOnJackEnabled = chooseSuitOnJackEnabled;
    }

    public boolean isReverseOnAceEnabled() {
        return reverseOnAceEnabled;
    }

    public void setReverseOnAceEnabled(boolean reverseOnAceEnabled) {
        this.reverseOnAceEnabled = reverseOnAceEnabled;
    }

    public boolean isDrawTwoOnSevenToggled() {
        return drawTwoOnSevenToggled;
    }

    public void setDrawTwoOnSevenToggled(boolean drawTwoOnSevenToggled) {
        this.drawTwoOnSevenToggled = drawTwoOnSevenToggled;
    }

    public boolean getChooseSuitOnJackToggled() {
        return chooseSuitOnJackToggled;
    }

    public void setChooseSuitOnJackToggled(boolean chooseSuitOnJackToggled) {
        this.chooseSuitOnJackToggled = chooseSuitOnJackToggled;
    }
    

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }
}
