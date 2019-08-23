package ada.synoptic.project.membershipsystem.controller.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ChangeBalanceRequest {

    private final String cardId;
    private final double changeAmount;

    @JsonCreator
    public ChangeBalanceRequest(@JsonProperty("cardId") String cardId, @JsonProperty("changeAmount") double changeAmount) {
        this.cardId = cardId;
        this.changeAmount = changeAmount;
    }

    public String getCardId() {
        return cardId;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangeBalanceRequest that = (ChangeBalanceRequest) o;
        return Double.compare(that.changeAmount, changeAmount) == 0 &&
                Objects.equals(cardId, that.cardId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardId, changeAmount);
    }

    @Override
    public String toString() {
        return "ChangeBalanceRequest{" +
                "cardId='" + cardId + '\'' +
                ", changeAmount=" + changeAmount +
                '}';
    }

}
