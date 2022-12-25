package commands.radar;

import commands.BaseCommand;
import lombok.Getter;

public class UpdateRadarCommand extends BaseCommand<String> {
    @Getter
    private double max_vitesse;
    @Getter private double longitude;
    @Getter private double latitude;

    public UpdateRadarCommand(String id, double max_vitesse, double longitude, double latitude) {
        super(id);
        this.max_vitesse = max_vitesse;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
