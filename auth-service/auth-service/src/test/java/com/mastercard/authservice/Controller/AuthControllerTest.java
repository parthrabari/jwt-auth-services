package com.mastercard.authservice.Controller;

import com.mastercard.authservice.auth.TokenInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AuthController.class)
@ContextConfiguration(classes = {TokenInfo.class})
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void test_refresh_token() throws Exception {
        String validJwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXJ0aCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE1ODAyNzAxOTEsImV4cCI6MTY4MDI3MjU5MX0.yHu2lBbVlsZkrNecAqB81AqXrfJQXzT4tM36e7snJaKMz7nkCgwN9OrolJQTYT-vFjKvtpMCIqFFtn2Jc_9JBA";
        String header = "Bearer " + validJwt;
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/refresh").header("Authorization", header))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
