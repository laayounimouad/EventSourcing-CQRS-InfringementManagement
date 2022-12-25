package commands.radar;

import commands.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CreateRadarCommand extends BaseCommand<String> {
    @Getter private double max_vitesse;
    @Getter private double longitude;
    @Getter private double latitude;

    public CreateRadarCommand(String id, double max_vitesse, double longitude, double latitude) {
        super(id);
        this.max_vitesse = max_vitesse;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
