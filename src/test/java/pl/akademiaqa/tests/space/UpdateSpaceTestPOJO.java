package pl.akademiaqa.tests.space;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.akademiaqa.dto.task.request.CreateTaskRequestDto;
import pl.akademiaqa.dto.task.request.UpdateSpaceRequestDto;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;
import pl.akademiaqa.requests.space.UpdateSpaceRequest;

public class UpdateSpaceTestPOJO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateSpaceTest.class);
    private static final String SPACE_NAME = "MY SPACE FROM JAVA";
    private static final String SPACE_NAME_UPDATE = "MY UPDATED SPACE FROM JAVA 123";
    private String spaceId;

    @Test
    void updateSpaceTest() {
        spaceId = createSpaceStep();
        LOGGER.info("Space created with id: {}", spaceId);
        updateSpaceStep();
        deleteSpaceStep();
    }

    private String createSpaceStep() {
        JSONObject json = new JSONObject();
        json.put("name", SPACE_NAME);

        final var response = CreateSpaceRequest.createSpace(json);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(SPACE_NAME);

        return jsonData.getString("id");
    }

    private void updateSpaceStep() {
        UpdateSpaceRequestDto spaceDto = new UpdateSpaceRequestDto();
        spaceDto.setName(SPACE_NAME_UPDATE);

        final var responseUpdate = UpdateSpaceRequest.updateSpace(spaceDto, spaceId);
        Assertions.assertThat(responseUpdate.statusCode()).isEqualTo(200);
        Assertions.assertThat(responseUpdate.jsonPath().getString("name")).isEqualTo(SPACE_NAME_UPDATE);
    }

    private void deleteSpaceStep() {
        final var response = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
    }
}
