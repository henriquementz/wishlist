package br.com.wishlist.database.repository;

import br.com.wishlist.config.MongoConfig;
import br.com.wishlist.util.MockUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(properties = {
        "spring.data.mongodb.uri=mongodb://localhost:27017/wishlist"
})
@Import(MongoConfig.class)
@ExtendWith(SpringExtension.class)
@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Order(1)
    @Test
    public void given_AValidItemEntity_When_Save_Then_ExpectedItemSaved() {
        var validItemEntity = MockUtil.getValidEntity();
        var itemEntitySaved = itemRepository.save(validItemEntity);
        assertNotNull(itemEntitySaved.getId());
    }

}