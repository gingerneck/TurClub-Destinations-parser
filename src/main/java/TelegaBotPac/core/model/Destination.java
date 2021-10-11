package TelegaBotPac.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Destination {

    private final String nameDestination;
    private final String link;
    private final List<Route> routes;

    @Override
    public String toString() {
        return "Destination{" +
                "nameDestination='" + nameDestination + '\'' +
                ", link='" + link + '\'' +
                ", routes=" + routes +
                '}';
    }

    public static class Builder {

        private String nameDestination;
        private String link;
        private List<Route> routes;

        public Builder setNameDestination(String name){
            this.nameDestination = name;
            return this;
        }

        public Builder setLink(String link){
            this.link = link;
            return this;
        }

        public Destination build(){
            return new Destination(nameDestination, link, routes);
        }

    }
}
