package com.jassuncao.docmap;

import com.jassuncao.docmap.infra.ProfileApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Profile(ProfileApplication.DEV)
public class IntegrationTests {

}

