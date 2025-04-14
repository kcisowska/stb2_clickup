package pl.akademiaqa.dto.task.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateSpaceRequestDto {
    private String name;
    private String color;
    private String avatar;
    @JsonProperty("admin_can_manage")
    private String adminCanManage;
    private boolean archived;
    private UpdateSpaceStatusRequestDto status;
}
