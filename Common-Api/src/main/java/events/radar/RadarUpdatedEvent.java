package events.radar;

import events.BaseEvent;
import lombok.Getter;

public class RadarUpdatedEvent extends BaseEvent<String> {
    @Getter
    private double max_vitesse;
    @Getter private double longitude;
    @Getter private double latitude;

    public RadarUpdatedEvent(String id, double max_vitesse, double longitude, double latitude) {
        super(id);
        this.max_vitesse = max_vitesse;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
