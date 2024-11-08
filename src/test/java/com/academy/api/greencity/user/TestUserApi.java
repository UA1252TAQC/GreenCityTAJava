package com.academy.api.greencity.user;

import com.academy.api.clients.user.GreenCityUserClient;
import com.academy.api.models.user.Profile;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class TestUserApi {
    private GreenCityUserClient client;

    @BeforeClass
    public void setUpClass() throws IOException {
//        String token= "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnYWxhbWFnYWw4NkBnbWFpbC5jb20iLCJyb2xlIjpbIlJPTEVfVVNFUiJdLCJleHAiOjE3MzA4OTYwMDksImlhdCI6MTczMDg4ODgwOX0.fpLoLlZPw-hl3-u8PI1quUGItPfG-VmfnYfftRdvb4Q";
        String token= "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnYWxhbWFnYWw4NkBnbWFpbC5jb20iLCJyb2xlIjpbIlJPTEVfVVNFUiJdLCJleHAiOjE3MzA4OTYwMDksImlhdCI6MTczMDg4ODgwOX0.fpLoLlZPw-hl3-u8PI1quUGItPfG-VmfnYfftRdvb4Q";
        client = new GreenCityUserClient(token);

    }


    @Test
    public void profileTest()
    {
        Response response = client.getProfile(2303);
        SoftAssert softAssert = new SoftAssert();
        Profile profile = response.as(Profile.class);
        softAssert.assertEquals(profile.name, "Любомир");
        softAssert.assertEquals(response.statusCode(), 200);
        softAssert.assertAll();
    }
}
