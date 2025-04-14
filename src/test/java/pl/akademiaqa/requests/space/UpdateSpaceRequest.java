package pl.akademiaqa.requests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.akademiaqa.dto.task.request.UpdateSpaceRequestDto;
import pl.akademiaqa.properties.ClickUpProperties;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class UpdateSpaceRequest {

    public static Response updateSpace(JSONObject space, String spaceId) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(space.toString())
                .when()
                .put(ClickUpUrl.getSpaceUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }

    public static Response updateSpace(UpdateSpaceRequestDto space, String spaceId) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(space)
                .when()
                .put(ClickUpUrl.getSpaceUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
