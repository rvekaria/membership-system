package ada.synoptic.project.membershipsystem.rest.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TopUpRequest {

    private final String cardId;
    private final double topUpAmount;

    @JsonCreator
    public TopUpRequest(@JsonProperty("cardId") String cardId, @JsonProperty("topUpAmount") double topUpAmount) {
        this.cardId = cardId;
        this.topUpAmount = topUpAmount;
    }

    public String getCardId() {
        return cardId;
    }

    public double getTopUpAmount() {
        return topUpAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopUpRequest that = (TopUpRequest) o;
        return Double.compare(that.topUpAmount, topUpAmount) == 0 &&
                Objects.equals(cardId, that.cardId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardId, topUpAmount);
    }

    @Override
    public String toString() {
        return "TopUpRequest{" +
                "cardId='" + cardId + '\'' +
                ", topUpAmount=" + topUpAmount +
                '}';
    }

}
