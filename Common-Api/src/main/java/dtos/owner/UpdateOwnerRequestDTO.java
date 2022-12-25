package dtos.owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOwnerRequestDTO {
    private String id;
    private String nom;
    private Date date_naissance;
    private String email;
}
