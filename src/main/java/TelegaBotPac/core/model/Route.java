package TelegaBotPac.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Route {

    private final String cost;
    private final String currency;
    private final String link;
    private final String nameTrip;
    private final String title;
    private final String aroundCost;
    private final String Description;

    public static class Builder {
        private String cost;
        private String currency;
        private String link;
        private String nameTrip;
        private String title;
        private String aroundCost;
        private String description;

        public Builder setCost(String cost) {
            this.cost = cost;
            return this;
        }

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder setLink(String link) {
            this.link = link;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setNameTrip(String nameTrip) {
            this.nameTrip = nameTrip;
            return this;
        }

        public Builder setAroundCost(String aroundCost) {
            this.aroundCost = aroundCost;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Route build() {
            return new Route(cost, currency, link, nameTrip, title, aroundCost, description);
        }

    }
}
