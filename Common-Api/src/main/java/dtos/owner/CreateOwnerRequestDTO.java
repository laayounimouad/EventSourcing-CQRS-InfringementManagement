package dtos.owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOwnerRequestDTO {
    private String nom;
    private Date date_naissance;
    private String email;
}
