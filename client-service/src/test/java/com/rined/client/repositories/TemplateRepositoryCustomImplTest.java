package com.rined.client.repositories;

import com.rined.client.AbstractMongoDBRepositoryTest;
import com.rined.client.dto.response.ResponseTemplateNameDto;
import com.rined.client.model.collections.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static java.util.Objects.nonNull;
import static org.assertj.core.api.Java6Assertions.assertThat;

@DisplayName("Репозиторий шаблонов должен")
class TemplateRepositoryCustomImplTest extends AbstractMongoDBRepositoryTest {

    @Autowired
    private TemplateRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private User user;

    @BeforeEach
    void setUp() {
        user = mongoTemplate.findOne(new Query(), User.class);
    }

    @Test
    @DisplayName("возвращать краткое содержимое активных документов")
    void shouldReturnActiveTemplateBriefList() {
        List<ResponseTemplateNameDto> responseTemplateNameDtoList = repository.userActiveTemplateNameList(user.getId());
        assertThat(responseTemplateNameDtoList).isNotNull()
                .allMatch(templateBrief -> nonNull(templateBrief.getId()))
                .allMatch(templateBrief -> nonNull(templateBrief.getName()));
    }
}