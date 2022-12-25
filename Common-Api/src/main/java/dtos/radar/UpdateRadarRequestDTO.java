package dtos.radar;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRadarRequestDTO {
    private String id;
    private double max_vitesse;
    private double longitude;
    private double latitude;
}
